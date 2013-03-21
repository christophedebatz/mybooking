package com.catalog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.catalog.business.Band;
import com.catalog.business.Concert;
import com.catalog.business.Salle;
import com.catalog.business.SessionInfo;
import com.catalog.form.SearchForm;
import com.catalog.view.ApplicationSingleton;


@Controller
@RequestMapping(value = "/search")
public class SearchController {

	@Autowired
	private SessionInfo sessionInfo;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showSearchForm() {
		ModelAndView mav = new ModelAndView();
		SearchForm searchForm = new SearchForm();
		
		mav.setViewName("searchForm");
		mav.addObject("session", sessionInfo);
		mav.addObject("searchForm", searchForm);
		mav.addObject("result", false);
		return mav;
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView showSearchResults(@Valid SearchForm searchForm, BindingResult result, Model model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("searchForm");
		
		List<Concert> concerts = ApplicationSingleton.getApplication().findConcerts(searchForm.getSearch());
		List<Band> bands = ApplicationSingleton.getApplication().findBands(searchForm.getSearch());
		List<Salle> halls = ApplicationSingleton.getApplication().findSalles(searchForm.getSearch());
		
		mav.addObject("session", sessionInfo);
		mav.addObject("search", searchForm.getSearch());
		mav.addObject("concerts", concerts);
		mav.addObject("bands", bands);
		mav.addObject("halls", halls);
		mav.addObject("result", true);
		
		return mav;
	}

}
