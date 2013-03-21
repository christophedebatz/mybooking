package com.catalog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.catalog.business.CustomerOrder;
import com.catalog.business.Place;
import com.catalog.business.SessionInfo;
import com.catalog.view.ApplicationSingleton;

@Controller
@RequestMapping(value = "/checkout")
public class CheckoutController {
	
	@Autowired
	private SessionInfo sessionInfo;

	@RequestMapping(value = "/{pid}", method = RequestMethod.GET)
	public String addPlace(@PathVariable(value="pid") int pid, Model model) {
		
		if (!sessionInfo.getConnected())
			return (pid >= 0) ? "redirect:/login?next=/checkout/" + pid : "redirect:/login?next=/checkout/";
		
		if (pid >= 0) {
			Place place = ApplicationSingleton.getApplication().getPlace(pid);
			
			if (place.getCustomerOrder() == null) {
				if (Place.isInPlacesList((List<Place>) sessionInfo.getOrder().getPlaces(), pid))
					model.addAttribute("error", "1");
				else
					sessionInfo.addPlaceToOrder(place);
			}
		}
		
		// else just view basket
		if (sessionInfo.getOrder() != null) {
			model.addAttribute("fees", sessionInfo.getOrder().getFees());
			model.addAttribute("total", sessionInfo.getOrder().getTotal());
		}
		model.addAttribute("order", sessionInfo.getOrder());
		model.addAttribute("session", sessionInfo);
		return "basket";
		
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String addPlace(Model model) {
		return this.addPlace(-1, model);
	}
	
	
	@RequestMapping(value = "/ordered", method = RequestMethod.GET)
	public String displayOrders (Model model) {
		
		if (!sessionInfo.getConnected())
			return "redirect:/login?next=/orders";
	
		List<CustomerOrder> orders = ApplicationSingleton.getApplication().findOrdersByCustomer(sessionInfo.getUserId());
		List<Float> ordersFees = new ArrayList<Float>();
		List<Float> ordersTotal = new ArrayList<Float>();
		
		for (CustomerOrder order : orders) {
			ordersFees.add(order.getFees());
			ordersTotal.add(order.getTotal());
			System.out.println("fees = " + order.getFees());
			System.out.println("total = " + order.getTotal());
			System.out.println();
		}
		
		model.addAttribute("orders", orders);
		model.addAttribute("ordersFees", ordersFees);
		model.addAttribute("ordersTotal", ordersTotal);
		model.addAttribute("session", sessionInfo);
		
		return "orders";
	}
	
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String storeBasket (Model model) {
		
		if (!sessionInfo.getConnected())
			return "redirect:/login?next=/orders";
		
		ApplicationSingleton.getApplication().persistOrder(sessionInfo.getOrder()); // persist order

		
		return "redirect:/checkout/ordered";
	}
}
	