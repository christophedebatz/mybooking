package com.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.catalog.business.Salle;
import com.catalog.business.SessionInfo;
import com.catalog.view.ApplicationSingleton;

@Controller
@RequestMapping(value = "/hall")
public class HallController {
	
	@Autowired
	private SessionInfo sessionInfo;
	
	@RequestMapping(value = "/{hid}", method = RequestMethod.GET)
	public String viewConcert(@PathVariable("hid") int hid, Model model) {
		
		if (!sessionInfo.getConnected())
			return "redirect:/login?next=/hall/" + hid;
		
		Salle hall = ApplicationSingleton.getApplication().getSalle(hid);
		model.addAttribute("session", sessionInfo);
		model.addAttribute("salle", hall);
		
		return "hall";
	}
}