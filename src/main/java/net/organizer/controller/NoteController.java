package net.organizer.controller;

import java.io.IOException;

import java.util.Calendar;

import net.organizer.form.Note;
import net.organizer.service.LoginService;
import net.organizer.service.NoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.*;

@Controller
// @Scope("session")
@RequestMapping("/note")
public class NoteController {

	@Autowired
	Settings settings;

	@Autowired
	private LoginService loginService;

	@Autowired
	private NoteService noteService;

	@RequestMapping("")
	public ModelAndView list() throws ServletException, IOException {
		ModelAndView modelAndView = new ModelAndView("note/note");
		modelAndView.addObject("noteBean", new Note());
		modelAndView.addObject("noteList", noteService.listNote(
				loginService.getUser(settings.user.login), settings.note.order,
				settings.note.firstResult, settings.note.maxResults));
		return modelAndView;
	}

	@RequestMapping(value = "/new")
	public ModelAndView newNote(ModelAndView mav) {
		mav.setViewName("note/noteOne");
		mav.addObject("oneNote", new Note());

		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String newNote(@ModelAttribute("note") Note note) {
		note.setIdUser(loginService.getUser(settings.user.login));
		Calendar now = Calendar.getInstance();
		note.setYear(now.get(Calendar.YEAR));
		note.setMonth(now.get(Calendar.MONTH) + 1);
		note.setDay(now.get(Calendar.DAY_OF_MONTH));
		note.setHour(now.get(Calendar.HOUR_OF_DAY));
		note.setMinute(now.get(Calendar.MINUTE));
		note.setSecond(now.get(Calendar.SECOND));
		noteService.addNote(note);
		return "redirect:/note";
	}

	@RequestMapping(value = "/one/save", method = RequestMethod.POST)
	public String updateNote(@ModelAttribute("note") Note note) {
		note.setIdUser(loginService.getUser(settings.user.login));
		
		Calendar now = Calendar.getInstance();
		note.setYear(now.get(Calendar.YEAR));
		note.setMonth(now.get(Calendar.MONTH) + 1);
		note.setDay(now.get(Calendar.DAY_OF_MONTH));
		note.setHour(now.get(Calendar.HOUR_OF_DAY));
		note.setMinute(now.get(Calendar.MINUTE));
		note.setSecond(now.get(Calendar.SECOND));
		noteService.updateNote(note);
		
		return "redirect:/note";
	}

	@RequestMapping(value = "/one/{idNote}", method = RequestMethod.GET)
	public ModelAndView noteOne(@PathVariable("idNote") Integer idNote) {

		ModelAndView modelAndView = new ModelAndView("note/noteOne");
		modelAndView.addObject("oneNote", noteService.oneNote(idNote));
		modelAndView.addObject("deleteButton", noteService.oneNote(idNote));

		return modelAndView;
	}

	@RequestMapping(value = "/one/{idNote}/delete", method = RequestMethod.GET)
	public String deleteNote(@PathVariable("idNote") Integer idNote) {
		noteService.removeNote(idNote);
		return "redirect:/note";
	}

}