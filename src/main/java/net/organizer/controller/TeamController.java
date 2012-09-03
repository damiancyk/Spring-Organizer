package net.organizer.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.organizer.form.Action;
import net.organizer.form.Contact;
import net.organizer.form.Invitation;
import net.organizer.form.Team;
import net.organizer.form.User;
import net.organizer.form.UserTeam;
import net.organizer.service.ContactService;
import net.organizer.service.ActionService;
import net.organizer.service.LoginService;
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

import javax.servlet.*;

@Controller
@RequestMapping("/team")
public class TeamController {

	@Autowired
	Settings settings;

	@Autowired
	private TeamService teamService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private ContactService contactService;

	@Autowired
	private ActionService actionService;

	@RequestMapping(value = "")
	public ModelAndView list() throws ServletException, IOException {
		List<UserTeam> userTeam = teamService.listUserTeamByUser(loginService
				.getUser(settings.user.login));

		ModelAndView modelAndView = new ModelAndView("team/team");
		modelAndView.addObject("teamList", userTeam);

		return modelAndView;
	}

	@RequestMapping(value = "invitedMe")
	public ModelAndView listInvitedMe() throws ServletException, IOException {
		List<UserTeam> userTeam = teamService.listUserTeamByUser(loginService
				.getUser(settings.user.login));

		ModelAndView modelAndView = new ModelAndView("team/teamInvitedMe");
		modelAndView.addObject("teamList", userTeam);
		modelAndView.addObject("invitationList",
				teamService.listInvitation(settings.user.id));

		return modelAndView;
	}

	@RequestMapping(value = "invitedMe/confirm/{idInvitation}/{idTeam}", method = RequestMethod.GET)
	public String listInvitedMeConfirm(
			@PathVariable("idInvitation") int idInvitation,
			@PathVariable("idTeam") int idTeam) throws ServletException,
			IOException {
		Team team = new Team();
		team.setIdTeam(idTeam);
		User user = new User();
		user.setIdUser(settings.user.id);

		Invitation invitation = new Invitation();
		invitation.setIdInvitation(idInvitation);

		invitation.setIdTeam(team);
		invitation.setIdUser(user);

		teamService.acceptInvitation(invitation);
		return "redirect:/team/invitedMe";
	}

	@RequestMapping(value = "invitedMe/reject/{idInvitation}", method = RequestMethod.GET)
	public String listInvitedMeReject(
			@PathVariable("idInvitation") int idInvitation)
			throws ServletException, IOException {
		teamService.rejectInvitation(idInvitation);
		return "redirect:/team/invitedMe";
	}

	@RequestMapping(value = "one/{idTeam}", method = RequestMethod.GET)
	public ModelAndView oneTeam(@PathVariable("idTeam") int idTeam)
			throws ServletException, IOException {
		UserTeam userTeam = teamService.oneUserTeam(idTeam, settings.user.id);

		ModelAndView modelAndView = new ModelAndView("team/teamOne");
		modelAndView.addObject("oneTeam", userTeam);

		return modelAndView;
	}

	@RequestMapping(value = "one/{idTeam}/addAction/{typeAction}", method = RequestMethod.GET)
	public ModelAndView addEvent(@PathVariable("idTeam") int idTeam,
			@PathVariable("typeAction") Short typeAction)
			throws ServletException, IOException {
		Team team = new Team();
		team.setIdTeam(idTeam);

		Calendar calendar = Calendar.getInstance();

		Action action = new Action();
		action.setIdTeam(team);
		action.setPeriodic("no");
		action.setTypeAction(typeAction);
		action.setDateWhen(calendar.getTime());

		ModelAndView modelAndView = new ModelAndView("action/actionOne");
		modelAndView.addObject("action", action);
		modelAndView.addObject("adminAccess", false);

		boolean isAdmin = teamService.isAdmin(settings.user.id, idTeam);
		modelAndView.addObject("isAdmin", isAdmin);
		modelAndView.addObject("noDelete", true);

		return modelAndView;
	}

	@RequestMapping(value = "one/{idTeam}/addAction/save", method = RequestMethod.POST)
	public String addEvent(@ModelAttribute("event") Action action)
			throws ServletException, IOException {
		actionService.addAction(action);
		return "redirect:/team";
	}

	@RequestMapping(value = "one/{idTeam}/save", method = RequestMethod.POST)
	public ModelAndView addEvent(@ModelAttribute("event") Action action,
			ModelAndView mav) throws ServletException, IOException {

		mav.setViewName("redirect:/team");
		actionService.addAction(action);

		return mav;
	}

	@RequestMapping(value = "one/{idTeam}/user", method = RequestMethod.GET)
	public ModelAndView oneTeamMember(@PathVariable("idTeam") int idTeam)
			throws ServletException, IOException {
		List<UserTeam> userTeam = teamService.listUserTeamByIdTeam(idTeam);

		ModelAndView modelAndView = new ModelAndView("team/teamUser");
		modelAndView.addObject("listUserTeam", userTeam);
		modelAndView.addObject("isAdmin", teamService.isAdmin(settings.user.id, idTeam));

		return modelAndView;
	}

	@RequestMapping(value = "one/{idTeam}/user/{idUser}/privilege", method = RequestMethod.GET)
	public ModelAndView oneTeamMemberPrivilege(
			@PathVariable("idTeam") int idTeam,
			@PathVariable("idUser") int idUser) throws ServletException,
			IOException {
		teamService.setPrivileges(idTeam, idUser);

		ModelAndView modelAndView = new ModelAndView("redirect:/team/one/"
				+ idTeam + "/user");

		return modelAndView;
	}

	@RequestMapping(value = "oneUser/{idUserTeam}", method = RequestMethod.GET)
	public ModelAndView oneTeamOneMember(
			@PathVariable("idUserTeam") int idUserTeam)
			throws ServletException, IOException {
		UserTeam userTeam = teamService.oneUserTeamById(idUserTeam);

		ModelAndView modelAndView = new ModelAndView("team/teamOneUser");
		modelAndView.addObject("oneUserTeam", userTeam);

		return modelAndView;
	}

	@RequestMapping(value = "one/{idTeam}/action/{typeAction}", method = RequestMethod.GET)
	public ModelAndView oneTeamEvent(@PathVariable("idTeam") int idTeam,
			@PathVariable("typeAction") Short typeAction)
			throws ServletException, IOException {

		List<Action> actionList = actionService.listActionByIdTeam(idTeam,
				typeAction);

		ModelAndView modelAndView = new ModelAndView("team/teamActionList");
		modelAndView.addObject("actionList", actionList);
		modelAndView.addObject("objectDate", Calendar.getInstance().getTime());
		modelAndView.addObject("messageTypeAction", typeAction);

		return modelAndView;
	}

	@RequestMapping(value = "one/{idTeam}/quit", method = RequestMethod.GET)
	public ModelAndView oneTeamQuit(@PathVariable("idTeam") int idTeam,
			ModelAndView mav) throws ServletException, IOException {
		boolean isAdmin = teamService.isAdmin(settings.user.id, idTeam);

		if (isAdmin) {
			mav.addObject("quitTeamFailed", true);
			mav.setViewName("redirect:/team");
		} else {
			teamService.quitTeam(settings.user.id, idTeam);
			mav.addObject("quitTeamSuccess", true);
			mav.setViewName("redirect:/team");
		}

		return mav;
	}

	@RequestMapping(value = "one/{idTeam}/invitation", method = RequestMethod.GET)
	public ModelAndView oneTeamInvitation(@PathVariable("idTeam") int idTeam)
			throws ServletException, IOException {
		User user = loginService.getUser(settings.user.id);
		List<Contact> contact = contactService.listContact(user);

		ModelAndView modelAndView = new ModelAndView("team/teamInvitation");
		modelAndView.addObject("contactList", contact);

		return modelAndView;
	}

	@RequestMapping(value = "one/{idTeam}/invitation/{idUser}", method = RequestMethod.GET)
	public ModelAndView oneTeamInvitation(@PathVariable("idTeam") int idTeam,
			@PathVariable("idUser") int idUser) throws ServletException,
			IOException {
		User userTo = new User();
		userTo.setIdUser(idUser);
		Team teamFrom = new Team();
		teamFrom.setIdTeam(idTeam);
		Invitation invitation = new Invitation();
		invitation.setIdTeam(teamFrom);
		invitation.setIdUser(userTo);

		ModelAndView modelAndView = new ModelAndView("team/teamInvitation");
		modelAndView.addObject("invitationBean", invitation);

		return modelAndView;
	}

	@RequestMapping(value = "one/{idTeam}/invitation/send", method = RequestMethod.POST)
	public ModelAndView oneTeamInvitation(
			@ModelAttribute("invitationBean") Invitation invitation)
			throws ServletException, IOException {
		teamService.sendInvitation(invitation.getIdUser().getIdUser(),
				invitation.getIdTeam().getIdTeam());

		ModelAndView modelAndView = new ModelAndView("redirect:/team");

		return modelAndView;
	}

	@RequestMapping(value = "one/{idTeam}/deleteTeam", method = RequestMethod.GET)
	public String deleteTeam(@PathVariable("idTeam") int idTeam)
			throws ServletException, IOException {
		boolean isAdmin = teamService.isAdmin(settings.user.id, idTeam);

		if (isAdmin)
			teamService.deleteTeam(idTeam);

		return "redirect:/team";
	}

	@RequestMapping(value = "add")
	public ModelAndView addTeamForm() throws ServletException, IOException {
		ModelAndView modelAndView = new ModelAndView("team/teamAdd");
		modelAndView.addObject("teamBean", new Team());
		return modelAndView;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String addTeam(@ModelAttribute Team team) throws ServletException,
			IOException {
		if (team.getName().trim().length() > 0)
			teamService.addTeam(team, loginService.getUser(settings.user.id),
					true);
		return "redirect:/team";
	}

	@RequestMapping(value = "update/{idTeam}", method = RequestMethod.GET)
	public ModelAndView editTeam(@PathVariable("idTeam") int idTeam)
			throws ServletException, IOException {
		Team team = teamService.oneTeamById(idTeam);

		ModelAndView modelAndView = new ModelAndView("team/teamAdd");
		modelAndView.addObject("teamBean", team);
		return modelAndView;
	}

	@RequestMapping(value = "update/save", method = RequestMethod.POST)
	public String updateTeam(@ModelAttribute("teamBean") Team team)
			throws ServletException, IOException {
		teamService.updateTeam(team);
		return "redirect:/team";
	}

	@InitBinder
	// dla milisekund mamy jeszcze SSSS
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

}