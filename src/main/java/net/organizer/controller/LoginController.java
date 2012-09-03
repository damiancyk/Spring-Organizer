package net.organizer.controller;

import java.security.Principal;

import net.organizer.form.Details;
import net.organizer.form.User;
import net.organizer.service.LoginService;
import net.organizer.service.mail.EmailValidator;
import net.organizer.service.mail.MD5Util;
import net.organizer.service.mail.MailMail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@Autowired
	Settings settings;

	@Autowired
	private LoginService loginService;

	@Autowired
	private MailMail mailMail;

	@Autowired
	EmailValidator emailValidator;

	@Autowired
	private MD5Util md5Util;

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, Principal principal) {
		User user = loginService.getUser(principal.getName());

		if (user != null) {
			settings.user.login = user.getLogin();
			settings.user.id = user.getIdUser();
		}

		Details details = loginService.getDetails(settings.user.id);
		String hashEmail = md5Util.md5Hex(details.getEmail());
		settings.user.hashEmail = hashEmail;

		model.addAttribute("username", settings.user.login);
		model.addAttribute("hashEmail", settings.user.hashEmail);

		return "login/welcome";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login/login";
	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "login/login";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "login/login";
	}

	@RequestMapping(value = "/about")
	public ModelAndView about(ModelAndView mav) {
		mav.addObject("hashEmailAdmin", settings.admin.hashEmail);
		mav.setViewName("login/about");
		return mav;
	}

	@RequestMapping(value = "/account")
	public ModelAndView account(ModelAndView mav) {
		Details details = loginService.getDetails(settings.user.id);
		User user = loginService.getUser(settings.user.id);

		mav.addObject("details", details);
		mav.addObject("user", user);
		mav.addObject("hashEmail", settings.user.hashEmail);
		mav.setViewName("login/account");
		return mav;
	}

	@RequestMapping(value = "/account/update", method = RequestMethod.POST)
	public ModelAndView account(@ModelAttribute("details") Details details,
			ModelAndView mav) {
		if (emailValidator.validate(details.getEmail())) {
			loginService.updateDetails(details);
			mav.addObject("messageFromLogin",
					"pomyslnie zmieniono dane osobowe");
			mav.setViewName("redirect:/welcome");
		} else {
			mav.addObject("messageFromLogin", "niepoprawny login");
			mav.setViewName("redirect:/account");
		}

		return mav;
	}

	@RequestMapping(value = "/register")
	public ModelAndView register(ModelAndView mav) {
		mav.setViewName("login/register");
		mav.addObject("user", new User());
		return mav;
	}

	@RequestMapping(value = "/register/check", method = RequestMethod.POST)
	public ModelAndView registerSave(ModelAndView mav,
			@ModelAttribute("user") User user) {
		String hashPassword = md5Util.md5Hex(user.getPassword());

		boolean success = loginService.registerUser(user.getLogin(),
				hashPassword);

		if (success) {
			mailMail.sendMail(
					settings.admin.email,
					settings.admin.email,
					"organizer(powiadomienie): ",
					"kolejny zarejestrowany u¿ytkownik" + "\nlogin: "
							+ user.getLogin());
			mav.addObject("messageRegisterSuccess", true);
			mav.setViewName("redirect:/login");
		} else {
			mav.addObject("messageRegisterFailed", true);
			mav.setViewName("redirect:/register");
		}

		return mav;
	}

	@RequestMapping(value = "/menuTeam", method = RequestMethod.GET)
	public String menuTeam(ModelMap model) {
		return "login/menuTeam";
	}

	@RequestMapping(value = "/menuLocal", method = RequestMethod.GET)
	public String menuLocal(ModelMap model) {
		return "login/menuLocal";
	}
	
	@RequestMapping(value = "/menuAnother", method = RequestMethod.GET)
	public String menuAnother(ModelMap model) {
		return "login/menuAnother";
	}

}