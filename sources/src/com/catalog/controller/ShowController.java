package com.catalog.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.catalog.business.Place;
import com.catalog.business.Representation;
import com.catalog.business.SessionInfo;
import com.catalog.view.ApplicationSingleton;

@Controller
@RequestMapping(value = "/show")
public class ShowController {
	
	@Autowired
	private SessionInfo sessionInfo;
	
	@RequestMapping(value = "/{sid}", method = RequestMethod.GET)
	public String viewShow(@PathVariable("sid") int sid, @RequestParam(required=false) final String oType, @RequestParam(required=false) final String oPrice, final Model model) {
		
		if (!sessionInfo.getConnected())
			return "redirect:/login?next=/show/" + sid;

		Representation repr = ApplicationSingleton.getApplication().getRepresentation(sid);
		List<Place> places = (List<Place>) repr.getPlaces();
		
		// sorting system by de Bootz ;)
		if (oPrice != null) {
			Collections.sort(places, new Comparator<Place>() {
				public int compare(Place p1, Place p2) {
					if (oPrice.equals("1")) {
						model.addAttribute("oPrice", "0");
						return String.valueOf(p1.getPrice()).compareTo(String.valueOf(p2.getPrice()));
					}
					else {
						model.addAttribute("oPrice", "1");
						return String.valueOf(p2.getPrice()).compareTo(String.valueOf(p1.getPrice()));
					}
				}
			});
		} else
			model.addAttribute("oPrice", "0");

		if (oType != null) {
			Collections.sort(places, new Comparator<Place>() {
				public int compare(Place p1, Place p2) {
					if (oType.equals("1")) {
						model.addAttribute("oType", "0");
						return p1.getType().compareTo(p2.getType());
					}
					else {
						model.addAttribute("oType", "1");
						return p2.getType().compareTo(p1.getType());
					}
				}
			});
		} else
			model.addAttribute("oType", "1");

		
		model.addAttribute("repr", repr);
		model.addAttribute("places", places);
		model.addAttribute("session", sessionInfo);
		
		return "show";
	}
}