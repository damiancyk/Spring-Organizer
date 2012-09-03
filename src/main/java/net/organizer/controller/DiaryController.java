package net.organizer.controller;

import java.io.IOException;

import java.util.Calendar;

import javax.servlet.ServletException;

import net.organizer.form.Diary;
import net.organizer.service.DiaryService;
import net.organizer.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/diary")
public class DiaryController {
	@Autowired
	Settings settings;

	@Autowired
	private LoginService loginService;

	@Autowired
	private DiaryService diaryService;

	@RequestMapping("")
	public ModelAndView diaryMonthCurrent() throws ServletException,
			IOException {
		ModelAndView modelAndView = new ModelAndView("diary/diary");
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;

		modelAndView.addObject("year", year);
		modelAndView.addObject("month", month);
		modelAndView
				.addObject("diaryList", diaryService.listDiaryMonth(
						loginService.getUser(settings.user.login), year, month));

		return modelAndView;
	}

	@RequestMapping(value = "/{year}/{month}", method = RequestMethod.GET)
	public ModelAndView diaryMonth(@PathVariable("year") int year,
			@PathVariable("month") int month) throws ServletException,
			IOException {
		ModelAndView modelAndView = new ModelAndView("diary/diary");
		modelAndView.addObject("year", year);
		modelAndView.addObject("month", month);
		modelAndView
				.addObject("diaryList", diaryService.listDiaryMonth(
						loginService.getUser(settings.user.login), year, month));
		return modelAndView;
	}

	@RequestMapping(value = "/{year}/{month}/{day}", method = RequestMethod.GET)
	public ModelAndView diaryOneDay(@PathVariable("year") int year,
			@PathVariable("month") int month, @PathVariable("day") int day)
			throws ServletException, IOException {
		ModelAndView modelAndView = new ModelAndView("diary/diaryOneDay");

		Diary diary = diaryService.oneDiary(
				loginService.getUser(settings.user.login), year, month, day);
		if (diary == null)
			diary = new Diary();

		modelAndView.addObject("diary", diary);
		modelAndView.addObject("year", year);
		modelAndView.addObject("month", month);
		modelAndView.addObject("day", day);
		modelAndView.addObject("idDiary", diary.getIdDiary());
		
		return modelAndView;
	}

	@RequestMapping(value = "/{year}/{month}/update", method = RequestMethod.POST)
	public String updateOneDay(@ModelAttribute("diary") Diary diary) {
		diary.setIdUser(loginService.getUser(settings.user.login));
		if (diary.getContents().length() > 0)
			diaryService.updateDiary(diary);
		else
			diaryService.removeDiary(diary.getIdDiary());
		return "redirect:/diary";
	}

}