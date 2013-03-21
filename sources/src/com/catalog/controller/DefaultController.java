package com.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.catalog.business.Concert;
import com.catalog.business.SessionInfo;
import com.catalog.view.ApplicationSingleton;

@Controller
@RequestMapping(value = "/")
public class DefaultController {

	@Autowired
	private SessionInfo sessionInfo;
		
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm(Model model){
		ModelAndView mav = new ModelAndView();
		mav.addObject("session", sessionInfo);
		
		List<Concert> last = ApplicationSingleton.getApplication().last();
		mav.addObject("last", last);
		
		mav.setViewName("default");
		return mav;
	}
}