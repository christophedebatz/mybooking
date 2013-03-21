package com.catalog.controller;



import javax.validation.Valid;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.catalog.business.Customer;
import com.catalog.business.SessionInfo;
import com.catalog.form.LoginForm;
import com.catalog.form.RetrieveForm;
import com.catalog.view.ApplicationSingleton;


@Controller
@RequestMapping(value = {"/login", "/logout"})
public class LoginController {

	@Autowired
	private SessionInfo sessionInfo;
	
	// retrieve password form
	@RequestMapping(value = "/retrieve", method = RequestMethod.GET)
	public ModelAndView showRetrieveForm(@CookieValue(required=false, value="memorize") String memorize) {
		ModelAndView mav = new ModelAndView();
		RetrieveForm retrieveForm = new RetrieveForm();

		mav.addObject("retrieveForm", retrieveForm);
		mav.addObject("session", sessionInfo);
		mav.setViewName("retrieveForm");
		
		return mav;
	}
	
	// login form
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLoginForm(@CookieValue(required=false, value="memorize") String memorize, @RequestParam(required=false) String next) {
		ModelAndView mav = new ModelAndView();
		
		LoginForm loginForm = new LoginForm();
		loginForm.setNext(next);
		
		// if cookie memorize me
		if (memorize != null) {
			String[] memorized = memorize.split("-");
			loginForm.setUserName(memorized[0]);
			loginForm.setPassword(memorized[1]);
		}
		
		mav.addObject("loginForm", loginForm);
		mav.addObject("session", sessionInfo);
		mav.setViewName("loginForm");
		
		return mav;
	}
	
	
	@RequestMapping(value = "/retrieve", method = RequestMethod.POST)
	public String processRetrieveForm(@Valid RetrieveForm retrieveForm, BindingResult result, Model model) {
		
		model.addAttribute("session", sessionInfo);
		
		if (result.hasErrors()) {
			model.addAttribute("error", true);
			return "retrieveForm";
		}
		//Customer customer = ApplicationSingleton.getApplication().retrieveCustomer(retrieveForm.getEmail());

		/*SimpleMailMessage mailMessage = null;
		SimpleMailMessage message = new SimpleMailMessage(mailMessage);
		message.setSentDate(new Date());
		message.setText("Blah blah blah...");
		//mailSender.send(message);*/
		
		return "retrieveForm";
	}
	
	// login treatment
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public String processLoginForm(HttpServletResponse response, @Valid LoginForm loginForm, BindingResult result, Model model) {

		model.addAttribute("session", sessionInfo);
		
		if (result.hasErrors()) {
			model.addAttribute("error", true);
			return "loginForm";
		}
		Customer customer = ApplicationSingleton.getApplication().login(
				loginForm.getUserName(), 
				loginForm.getPassword()
		);
		
		if (customer == null) {
			model.addAttribute("error", true);
			return "loginForm";
		}
		
		// if cookie needed
		if (loginForm.isMemorize()) {
			response.addCookie(new Cookie("memorize", loginForm.getUserName() + "-" + loginForm.getPassword()));
		}
		
		sessionInfo.setWithCustomer(customer);
		return "redirect:" + ((loginForm.getNext() == "") ? "/" : loginForm.getNext());
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		if (sessionInfo.getConnected())
			sessionInfo.setConnected(false);
		
		return "redirect:/";
	}
	
}
