package com.catalog.business.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.catalog.business.*;

public class BusinessTest {
	
	EntityManager entityManager;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory)context.getBean("entityManagerFactory");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Test
	public void test() {
		EntityTransaction tx = entityManager.getTransaction();
		
		Concert concert = new Concert("Vitalic en Live @La Cigale", 120, null, null);
		
		Customer customer = new Customer("chris", "chris", "chris@lol.net", "VERCLYTTE", "Christophe", null);
		
		Band band = new Band("Vitalic", "Artiste électro notamment connu pour son single 'Second Lives'", "Electro", null);
		band.addConcert(concert);
		concert.setBand(band);
		
		Salle salle = new Salle("La cigale", 1000, "120, Boulevard de Rochechouart", 75018, "Paris", "France", null);
		
		// Création place et représentation
		Place place = new Place("Fosse", 15, null, null);
		Representation representation = new Representation(new Date(), concert, salle, null);
		representation.addPlaces(place);
		place.setRepresentation(representation);
		salle.addRepresentation(representation);
		concert.addRepresentation(representation);
		
		// Création d'une commande
		CustomerOrder customerOrder = new CustomerOrder(customer, null);
		place.setOrder(customerOrder);
		customerOrder.addPlace(place);
		customer.addOrder(customerOrder);
		
		tx.begin();
		entityManager.persist(concert);
		entityManager.persist(representation);
		entityManager.persist(customer);
		entityManager.persist(band);
		entityManager.persist(salle);
		entityManager.persist(place);
		entityManager.persist(customerOrder);
		tx.commit();
		
	}	
}
