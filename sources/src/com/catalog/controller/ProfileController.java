package com.catalog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.catalog.business.Customer;
import com.catalog.business.SessionInfo;
import com.catalog.form.RegisterForm;
import com.catalog.view.ApplicationSingleton;

@Controller
@RequestMapping(value = "/profile")
public class ProfileController {
	
	@Autowired
	private SessionInfo sessionInfo;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showForm(Model model) {
		
		if (!sessionInfo.getConnected())
			return "redirect:/login?next=/profile";
		
		RegisterForm registerForm = new RegisterForm();
		
		model.addAttribute("customer", ApplicationSingleton.getApplication().getCustomer(sessionInfo.getUserId()));
		model.addAttribute("session", sessionInfo);
		model.addAttribute("registerForm", registerForm);
		
		return "profileForm";
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public String processForm(@Valid RegisterForm registerForm, BindingResult result, Model model) {
		
		if (!sessionInfo.getConnected())
			return "redirect:/login?next=/profile";
		
		model.addAttribute("session", sessionInfo);
		
		if (result.hasErrors()) {
			model.addAttribute("error", true);
			return "profileForm";
		}
		
		Customer edited = new Customer (
				sessionInfo.getUserName(),
				registerForm.getPassword(),
				registerForm.getFirstName(),
				registerForm.getLastName(),
				registerForm.getEmail(),
				null
		);
		
		ApplicationSingleton.getApplication().update(
				edited,
				sessionInfo.getUserId()
		);
		sessionInfo.setUpdatedCustomer(edited);
		
		return "profileForm";
		
	}
}