package net.organizer.controller;

import java.io.IOException;
import java.util.List;

import net.organizer.form.Contact;
import net.organizer.form.User;
import net.organizer.service.ContactService;
import net.organizer.service.LoginService;
import net.organizer.service.mail.MD5Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.*;

@Controller
@RequestMapping("/contact")
public class ContactController {
	@Autowired
	Settings settings;

	@Autowired
	private ContactService contactService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private MD5Util md5Util;

	@RequestMapping("")
	public ModelAndView list() throws ServletException, IOException {
		ModelAndView modelAndView = new ModelAndView("contact/contact");
		modelAndView.addObject("contactList", contactService
				.listContact(loginService.getUser(settings.user.login)));
		return modelAndView;
	}

	@RequestMapping(value = "/add/{idUser}", method = RequestMethod.GET)
	public ModelAndView addContact(@PathVariable("idUser") int idUser) {
		ModelAndView mav = new ModelAndView("redirect:/contact");

		User idOwner = loginService.getUser(settings.user.login);
		User idPosition = new User();
		idPosition.setIdUser(idUser);

		if ((!contactService.existContact(idOwner, idPosition))
				&& (idOwner.getIdUser() != idUser)) {
			Contact contact = new Contact();
			contact.setIdOwner(idOwner);
			contact.setIdPosition(idPosition);
			contactService.addContact(contact);
		}
		return mav;
	}

	@RequestMapping(value = "/delete/{idContact}", method = RequestMethod.GET)
	public String deleteContact(@PathVariable("idContact") int idContact) {
		contactService.removeContact(idContact);
		return "redirect:/contact";
	}

	@RequestMapping(value = "/find")
	public ModelAndView searchContact() {
		ModelAndView modelAndView = new ModelAndView("contact/contactFind");
		modelAndView.addObject("userBean", new User());
		return modelAndView;
	}

	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public ModelAndView findContact(@ModelAttribute("user") User user) {
		ModelAndView modelAndView = new ModelAndView("contact/contactFind");
		modelAndView.addObject("userBean", new User());
		List<User> foundUsers = contactService.findUser(user.getLogin());
		if (foundUsers.size() == 0)
			modelAndView.addObject("messageNotFound", "true");

		modelAndView.addObject("userFound", foundUsers);

		return modelAndView;
	}

	@RequestMapping(value = "/one/{idContact}", method = RequestMethod.GET)
	public ModelAndView oneContact(@PathVariable("idContact") int idContact) {
		Contact oneContact = contactService.oneContact(settings.user.id,
				idContact);

		ModelAndView mav = new ModelAndView("contact/contactOne");

		if (oneContact != null)
			mav.addObject("hashEmail",
					md5Util.md5Hex(oneContact.getIdPosition().getLogin()));
		mav.addObject("oneContact", oneContact);
		return mav;
	}

}