package com.catalog.application;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.catalog.business.Concert;
import com.catalog.business.Customer;
import com.catalog.business.CustomerOrder;
import com.catalog.business.Band;
import com.catalog.business.Place;
import com.catalog.business.Representation;
import com.catalog.business.Salle;

public class ApplicationImpl implements Application{

	EntityManager em;

	public ApplicationImpl() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory)context.getBean("entityManagerFactory");
		em = entityManagerFactory.createEntityManager();
	}

	@Override
	public Customer login(String username, String password) {
		Query query = em.createNamedQuery("Customer.login");
		query.setParameter("username", username);
		query.setParameter("password", password);
		try {
			return (Customer) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public boolean exists(Customer customer) {
		Query query = em.createNamedQuery("Customer.exists");
		query.setParameter("username", customer.getUsername());
		query.setParameter("email", customer.getEmail());
		query.setParameter("firstname", customer.getFirstName());
		query.setParameter("lastname", customer.getLastName());
		
		try {
			return (query.getSingleResult() != null) ? true : false;
		} catch (NoResultException e) {
			return false;
		}
	}
	
	@Override
	public Customer retrieveCustomer (String email) {
		Query query = em.createNamedQuery("Customer.getByEmail");
		query.setParameter("email", email);
		
		try {
			return (Customer) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public Customer createCustomer (Customer customer) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(customer);
		tx.commit();
		em.refresh(customer);
		return customer;
	}

	@Override
	public List<Concert> findConcerts (String title) {
		Query query = em.createNamedQuery("Concert.find");
		query.setParameter("sTitle", "%" + title + "%");
		try {
			return (List<Concert>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public List<Band> findBands (String name) {
		Query query = em.createNamedQuery("Band.find");
		query.setParameter("sName", "%" + name + "%");
		try {
			return (List<Band>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public List<Concert> last () {
		Query query = em.createNamedQuery("Concert.last");
		try {
			return (List<Concert>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public Concert getConcert(int id) {
		return em.find(Concert.class, id);
	}
	
	@Override
	public List<Salle> findSalles (String name) {
		Query query = em.createNamedQuery("Salle.find");
		query.setParameter("sName", "%" + name + "%");
		try {
			return (List<Salle>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public Band getBand(int id) {
		return em.find(Band.class, id);
	}
	
	
	@Override
	public Representation getRepresentation(int id) {
		return em.find(Representation.class, id);
	}
	
	@Override
	public CustomerOrder persistOrder (CustomerOrder order) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(order);
		tx.commit();
		em.refresh(order);
		return order;
	}
	
	@Override
	public Place persistPlace (Place place) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(place);
		tx.commit();
		em.refresh(place);
		return place;
	}
	
	@Override
	public Place getPlace(int id) {
		return em.find(Place.class, id);
	}
	
	
	@Override
	public Salle getSalle(int id) {
		return em.find(Salle.class, id);
	}
	
	@Override
	public Customer getCustomer(int id) {
		return em.find(Customer.class, id);
	}
	
	@Override
	public List<CustomerOrder> findOrdersByCustomer (int id) {
		Query query = em.createNamedQuery("Order.findAll");
		query.setParameter("id", id);
		try {
			return (List<CustomerOrder>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public int update (Customer customer, int id) {
		Query query = em.createNamedQuery("Customer.update");
		query.setParameter("password", customer.getPassword());
		query.setParameter("firstName", customer.getFirstName());
		query.setParameter("lastName", customer.getLastName());
		query.setParameter("email", customer.getEmail());
		query.setParameter("id", id);
		try {
			return query.executeUpdate();
		} catch (NoResultException e) {
			return 0;
		}
	}
	
}
