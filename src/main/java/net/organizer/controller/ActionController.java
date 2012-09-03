package net.organizer.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import net.organizer.form.Action;
import net.organizer.service.ActionService;
import net.organizer.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/action")
public class ActionController {

	@Autowired
	Settings settings;

	@Autowired
	private ActionService actionService;

	@Autowired
	private TeamService teamService;

	@RequestMapping("/calendar")
	public ModelAndView diaryMonthCurrent() throws ServletException,
			IOException {
		ModelAndView modelAndView = new ModelAndView("action/actionCalendar");
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;

		modelAndView.addObject("year", year);
		modelAndView.addObject("month", month);

		calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date dateFrom = calendar.getTime();

		calendar.add(Calendar.MONTH, 1);
		Date dateTo = calendar.getTime();

		List<Action> actionList = actionService.listActionByIdUser(
				settings.user.id, null, dateFrom, dateTo);

		modelAndView.addObject("actionList", actionList);

		return modelAndView;
	}

	@RequestMapping(value = "/calendar/{year}/{month}", method = RequestMethod.GET)
	public ModelAndView diaryMonth(@PathVariable("year") int year,
			@PathVariable("month") int month) throws ServletException,
			IOException {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date dateFrom = calendar.getTime();

		calendar.add(Calendar.MONTH, 1);
		Date dateTo = calendar.getTime();

		List<Action> actionList = actionService.listActionByIdUser(
				settings.user.id, null, dateFrom, dateTo);

		ModelAndView modelAndView = new ModelAndView("action/actionCalendar");
		modelAndView.addObject("year", year);
		modelAndView.addObject("month", month);

		modelAndView.addObject("actionList", actionList);

		return modelAndView;
	}

	@RequestMapping(value = "/listOneDay/{year}/{month}/{day}", method = RequestMethod.GET)
	public ModelAndView listOneDay(@PathVariable("year") int year,
			@PathVariable("month") int month, @PathVariable("day") int day,
			ModelAndView mav) throws ServletException, IOException {

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date dateFrom = calendar.getTime();

		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date dateTo = calendar.getTime();

		List<Action> actionList = actionService.listActionByIdUser(
				settings.user.id, null, dateFrom, dateTo);

		mav.setViewName("action/actionList");
		mav.addObject("actionList", actionList);
		mav.addObject("objectDate", Calendar.getInstance().getTime());
		mav.addObject("fromDiary", true);
		mav.addObject("messageType", "cal");
		mav.addObject("messageHeader", "Wpisy na ten dzien");

		return mav;
	}

	@RequestMapping(value = "/list/{typeAction}/{typeNarrow}", method = RequestMethod.GET)
	public ModelAndView list(@PathVariable("typeAction") Short typeAction,
			@PathVariable("typeNarrow") String typeNarrow, ModelAndView mav)
			throws ServletException, IOException {

		List<Action> actionList = null;

		if (typeNarrow.compareTo("all") == 0) {
			actionList = actionService.listActionByIdUser(settings.user.id,
					typeAction, null, null);
			mav.addObject("messageTypeEvent", "wszystkie pozycje");
		} else if (typeNarrow.compareTo("future") == 0) {
			actionList = listFuture(typeAction);
			mav.addObject("messageTypeEvent", "wszystkie przyszle");
		} else if (typeNarrow.compareTo("past") == 0) {
			actionList = listPast(typeAction);
			mav.addObject("messageTypeEvent", "wszystkie zakonczone");
		} else if (typeNarrow.compareTo("today") == 0) {
			actionList = listToday(typeAction);
			mav.addObject("messageTypeEvent", "na dzisiaj");
		} else if (typeNarrow.compareTo("nearestWeek") == 0) {
			actionList = listNearestWeek(typeAction);
			mav.addObject("messageTypeEvent", "najblizszy tydzien");
		} else if (typeNarrow.compareTo("nearestMonth") == 0) {
			actionList = listNearestMonth(typeAction);
			mav.addObject("messageTypeEvent", "najblizszy miesiac");
		} else if (typeNarrow.compareTo("nearestYear") == 0) {
			actionList = listNearestYear(typeAction);
			mav.addObject("messageTypeEvent", "najblizszy rok");
		}

		mav.setViewName("action/actionList");
		mav.addObject("actionList", actionList);

		String messageType = "";
		String messageHeader = "";

		switch (typeAction) {
		case 1:
			messageType = "event";
			messageHeader = "Wydarzenia";
			break;
		case 2:
			messageType = "task";
			messageHeader = "Zadania";
			break;
		case 3:
			messageType = "periodic";
			messageHeader = "Okresowe";
			break;
		default:
			messageType = "event";
			messageHeader = "Wydarzenia i zadania";
			break;
		}
		mav.addObject("messageType", messageType);
		mav.addObject("messageHeader", messageHeader);
		mav.addObject("objectDate", Calendar.getInstance().getTime());

		return mav;
	}

	public List<Action> listFuture(Short typeAction) {
		Calendar calendar = Calendar.getInstance();
		Date dateFrom = calendar.getTime();

		List<Action> actionList = actionService.listActionByIdUser(
				settings.user.id, typeAction, dateFrom, null);

		return actionList;
	}

	public List<Action> listPast(Short typeAction) {
		Calendar calendar = Calendar.getInstance();
		Date dateTo = calendar.getTime();

		List<Action> actionList = actionService.listActionByIdUser(
				settings.user.id, typeAction, null, dateTo);

		return actionList;
	}

	public List<Action> listToday(Short typeAction) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date dateFrom = calendar.getTime();

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date dateTo = calendar.getTime();

		List<Action> actionList = actionService.listActionByIdUser(
				settings.user.id, typeAction, dateFrom, dateTo);

		return actionList;
	}

	public List<Action> listPreviousDay(Short typeAction) {
		Calendar calendar = Calendar.getInstance();
		Date dateFrom = calendar.getTime();

		calendar.add(Calendar.DAY_OF_YEAR, -1);
		Date dateTo = calendar.getTime();

		List<Action> actionList = actionService.listActionByIdUser(
				settings.user.id, typeAction, dateFrom, dateTo);

		return actionList;
	}

	public List<Action> listNearestWeek(Short typeAction) {
		Calendar cal = Calendar.getInstance();
		Date dateFrom = cal.getTime();

		cal.add(Calendar.WEEK_OF_YEAR, 1);
		Date dateTo = cal.getTime();

		List<Action> actionList = actionService.listActionByIdUser(
				settings.user.id, typeAction, dateFrom, dateTo);

		return actionList;
	}

	public List<Action> listNearestMonth(Short typeAction) {
		Calendar calendar = Calendar.getInstance();
		Date dateFrom = calendar.getTime();
		calendar.add(Calendar.MONTH, 1);
		Date dateTo = calendar.getTime();

		List<Action> actionList = actionService.listActionByIdUser(
				settings.user.id, typeAction, dateFrom, dateTo);

		return actionList;
	}

	public List<Action> listNearestYear(Short typeAction) {
		Calendar calendar = Calendar.getInstance();
		Date dateFrom = calendar.getTime();
		calendar.add(Calendar.YEAR, 1);
		Date dateTo = calendar.getTime();

		List<Action> actionList = actionService.listActionByIdUser(
				settings.user.id, typeAction, dateFrom, dateTo);

		return actionList;
	}

	@RequestMapping(value = "/one/{idAction}", method = RequestMethod.GET)
	public ModelAndView actionOne(@PathVariable("idAction") Integer idAction) {

		int idTeam = actionService.oneIdTeamByIdAction(idAction);
		boolean isAdmin = teamService.isAdmin(settings.user.id, idTeam);

		ModelAndView modelAndView = new ModelAndView("action/actionOne");
		modelAndView.addObject("action", actionService.oneAction(idAction));
		modelAndView.addObject("isAdmin", isAdmin);

		return modelAndView;
	}

	@RequestMapping(value = "/one/save", method = RequestMethod.POST)
	public String eventSave(@ModelAttribute("action") Action action) {

		System.out.print("OMG" + action.getPeriodic().length());

		actionService.addAction(action);
		String redirect = "redirect:/action/list/" + action.getTypeAction()
				+ "/nearestMonth";
		return redirect;
	}

	@RequestMapping(value = "/one/{idAction}/delete", method = RequestMethod.GET)
	public String actionDelete(@PathVariable("idAction") int idAction) {
		actionService.deleteAction(idAction);
		return "redirect:/team";
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss"); // SSSS dla milisekund
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

}
