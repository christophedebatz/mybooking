package com.catalog.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.catalog.business.Customer;
import com.catalog.business.SessionInfo;
import com.catalog.form.RegisterForm;
import com.catalog.view.ApplicationSingleton;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {
	
	@Autowired
	private SessionInfo sessionInfo;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm() {
		ModelAndView mav = new ModelAndView();
		RegisterForm registerForm = new RegisterForm();
		
		mav.addObject("session", sessionInfo);
		mav.addObject("registerForm", registerForm);
		mav.setViewName("registerForm");
		
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String processForm(@Valid RegisterForm registerForm, BindingResult result, Model model) {
		
		model.addAttribute("session", sessionInfo);
		
		if (result.hasErrors()) {
			model.addAttribute("error", true);
			model.addAttribute("errorNo", 0);
			return "registerForm";
		}
		
		if (registerForm.getUserName().equals("") || registerForm.getEmail().equals("") || registerForm.getPassword().equals("")) {
			model.addAttribute("error", true);
			model.addAttribute("errorNo", 2);
			return "registerForm";
		}
	
		Customer adding = new Customer (
				registerForm.getUserName(),
				registerForm.getPassword(),
				registerForm.getFirstName(),
				registerForm.getLastName(),
				registerForm.getEmail(),
				null 
			);
		
		// checking if new user doesn't exist
		if (ApplicationSingleton.getApplication().exists(adding)) {
			model.addAttribute("error", true);
			model.addAttribute("errorNo", 1);
			return "registerForm";
		}
		
		model.addAttribute(
				"customer",
				ApplicationSingleton.getApplication().createCustomer(adding)
			);
		return "registerForm";
	}
}
