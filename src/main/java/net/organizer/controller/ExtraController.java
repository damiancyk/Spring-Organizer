package net.organizer.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.*;

@Controller
@RequestMapping("")
public class ExtraController {

	@Autowired
	Settings settings;

	@RequestMapping("/calculator")
	public String calculator() throws ServletException, IOException {
		return "extra/calculator";
	}

	@RequestMapping("/calendar")
	public String calendar() throws ServletException, IOException {
		return "extra/calendar";
	}

	@RequestMapping("/currency")
	public String exchange() throws ServletException, IOException {
		return "extra/currency";
	}

	@RequestMapping("/weather")
	public String weather() throws ServletException, IOException {
		return "extra/weather";
	}

}