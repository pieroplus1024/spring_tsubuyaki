package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {


	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getLogin(ModelAndView mav,
					@ModelAttribute("message") String message) {
		mav.addObject("message", message);
		mav.setViewName("/login/login");
		return mav;
	}

	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView postLogin(ModelAndView mav) {
		mav.setViewName("login/signup");
		return mav;
	}

}
