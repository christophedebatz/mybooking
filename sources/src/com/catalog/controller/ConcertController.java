package com.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.catalog.business.Concert;
import com.catalog.business.SessionInfo;
import com.catalog.view.ApplicationSingleton;

@Controller
@RequestMapping(value = "/concert")
public class ConcertController {
	
	@Autowired
	private SessionInfo sessionInfo;
	
	@RequestMapping(value = "/{cid}", method = RequestMethod.GET)
	public String viewConcert(@PathVariable("cid") int cid, Model model) {
		
		if (!sessionInfo.getConnected())
			return "redirect:/login?next=/concert/" + cid;
		
		Concert concert = ApplicationSingleton.getApplication().getConcert(cid);
		
		model.addAttribute("concert", concert);
		model.addAttribute("session", sessionInfo);
		
		return "concert";
	}

}
