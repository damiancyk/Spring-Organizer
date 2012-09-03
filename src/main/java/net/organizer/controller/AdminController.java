package net.organizer.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.organizer.form.Details;
import net.organizer.form.User;
import net.organizer.service.AdminService;
import net.organizer.service.mail.MailMail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	Settings settings;

	@Autowired
	private MailMail mailMail;

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "")
	public ModelAndView hello(ModelAndView mav, Principal principal) {
		if (adminService.isAdmin(settings.user.id))
			mav.setViewName("admin/welcome");
		else
			mav.setViewName("welcome");

		return mav;
	}

	@RequestMapping(value = "/sendNotification")
	public ModelAndView sendNotificationPage(ModelAndView mav) {
		mav.setViewName("admin/sendNotification");
		return mav;
	}

	@RequestMapping(value = "/sendNotification/confirm/{kindOfNotification}", method = RequestMethod.GET)
	public ModelAndView sendNotification(
			@PathVariable("kindOfNotification") String kindOfNotification,
			ModelAndView mav) {

		Calendar calendar = Calendar.getInstance();
		Date dateFrom = calendar.getTime();
		String whenToSend = "";

		if (kindOfNotification.compareTo("nearestHour") == 0) {
			calendar.add(Calendar.HOUR_OF_DAY, 1);
			whenToSend = "najbli¿szej godziny";
		} else if (kindOfNotification.compareTo("nearestTwelveHours") == 0) {
			calendar.add(Calendar.HOUR_OF_DAY, 12);
			whenToSend = "najbli¿szych 12 godzin";
		} else if (kindOfNotification.compareTo("nearestDay") == 0) {
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			whenToSend = "najbli¿szego dnia";
		} else if (kindOfNotification.compareTo("nearestWeek") == 0) {
			calendar.add(Calendar.WEEK_OF_YEAR, 1);
			whenToSend = "najbli¿szego tygodnia";
		} else if (kindOfNotification.compareTo("nearestMonth") == 0) {
			calendar.add(Calendar.MONTH, 1);
			whenToSend = "najbli¿szego miesi¹ca";
		} else if (kindOfNotification.compareTo("nearestYear") == 0) {
			calendar.add(Calendar.YEAR, 1);
			whenToSend = "najbli¿szego roku";
		}

		Date dateTo = calendar.getTime();

		List<Details> sendToThisDetails = adminService.listUserToSend(dateFrom,
				dateTo);

		for (int i = 0; i < sendToThisDetails.size(); i++) {
			Details details = sendToThisDetails.get(i);

			if (details.getEmail() != null) {
				if (details.getEmail().length() > 0) {
					System.out.println("login: "
							+ sendToThisDetails.get(i).getEmail());

					String title = "Organizer(przypomnienie)! ";
					String message = "Witaj, " + details.getFirstname() + " "
							+ details.getLastname()
							+ "\nPragniemy Ciê poinformowaæ, ¿e w ci¹gu "
							+ whenToSend
							+ "\nposiadasz zapisane wydarzenia/zadania!"
							+ "\n---" + "\nPozdrawia admin organizera : )";

					mailMail.sendMail(settings.admin.email, details.getEmail(),
							title, message);
				}
			}
		}

		mav.addObject("messageFromFunction", "Pomyslnie wyslano przypomnienia!");
		mav.setViewName("redirect:/admin");
		return mav;
	}

	@RequestMapping(value = "/deleteUser")
	public ModelAndView deleteUser(ModelAndView mav) {
		List<User> userList = adminService.listUser(null, null);

		mav.addObject("userList", userList);
		mav.setViewName("admin/deleteUser");
		return mav;
	}

	@RequestMapping(value = "/deleteUser/{idUser}", method = RequestMethod.GET)
	public ModelAndView deleteUserConfirm(ModelAndView mav,
			@PathVariable("idUser") int idUser) {
		if (adminService.deleteUser(idUser)) {
			mav.addObject("messageFromFunction",
					"Pomyslnie usunieto uzytkownia!");
		} else {
			mav.addObject("messageFromFunction",
					"Blad przy usuwaniu uzytkownia!");
		}

		mav.setViewName("redirect:/admin");
		return mav;
	}
}
