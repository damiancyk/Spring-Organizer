package net.organizer.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.organizer.form.Action;
import net.organizer.service.ActionService;
import net.organizer.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.*;

@Controller
@RequestMapping("/mobile/action")
public class ActionControllerMobile {
	@Autowired
	Settings settings;

	@Autowired
	private LoginService loginService;

	@Autowired
	private ActionService actionService;

	@RequestMapping(value = "")
	public @ResponseBody
	List<Action> list(Principal principal) throws ServletException, IOException {
		return null;
	}

	@RequestMapping(value = "/list/{typeNarrow}", method = RequestMethod.GET)
	public @ResponseBody
	List<Action> listAction(Principal principal,
			@PathVariable("typeNarrow") String typeNarrow, ModelAndView mav)
			throws ServletException, IOException {
		List<Action> actionList = listNearestMonth(
				loginService.getUser(principal.getName()).getIdUser(), null);
		return actionList;
	}

	public List<Action> listNearestMonth(Integer idUser, Short typeAction) {
		Calendar calendar = Calendar.getInstance();
		Date dateFrom = calendar.getTime();
		calendar.add(Calendar.MONTH, 1);
		Date dateTo = calendar.getTime();

		List<Action> actionList = actionService.listActionByIdUser(idUser,
				typeAction, dateFrom, dateTo);

		return actionList;
	}

}