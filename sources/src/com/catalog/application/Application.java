package com.catalog.application;

import java.util.List;

import javax.persistence.EntityManager;
import com.catalog.business.*;

public interface Application {
	public EntityManager entityManager = null;

	Customer login(String username, String password);
	
	List<Band> findBands (String name);
	
	Customer createCustomer(Customer customer);
	
	boolean exists(Customer customer);
	
	Customer retrieveCustomer (String email);
	
	List<Concert> findConcerts (String title);

	List<Concert> last ();

	Concert getConcert(int id);

	List<Salle> findSalles(String name);

	Band getBand(int id);

	Representation getRepresentation(int id);

	Place getPlace(int id);

	Salle getSalle(int id);

	CustomerOrder persistOrder(CustomerOrder order);

	Place persistPlace(Place place);

	List<CustomerOrder> findOrdersByCustomer(int id);

	Customer getCustomer(int id);

	int update(Customer customer, int id);
		
}
