package com.catalog.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

@Entity
@NamedQuery(name="Order.findAll", query="SELECT order FROM CustomerOrder order WHERE customer.id = :id")
public class CustomerOrder {
	@TableGenerator(name = "orderGen", table="GEN_ID",
			pkColumnName="GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "ORDER_ID",
			allocationSize = 1) 
	@Id 
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "orderGen")
	private int id;
	private Date dateAchat;
	private STATUS status;
	
	@ManyToOne
	@NotNull
	private Customer customer;
	
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="customerOrder")
	@NotNull
	private Collection<Place> places;
	
	public CustomerOrder(Customer customer, Collection<Place> places) {
		this.customer = customer;
		this.places = new ArrayList<Place>();
		this.dateAchat = new Date();
		this.status = STATUS.PENDING;
		if (places != null) this.places.addAll(places);
	}
	
	
	public CustomerOrder () { }

	public int getId() {
		return id;
	}
	

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Collection<Place> getPlaces() {
		return places;
	}

	public void setPlaces(Collection<Place> places) {
		this.places = places;
	}
	
	public void addPlace(Place place) {
		this.places.add(place);
	}
	
	public float getFees () {
		float fees = 0f; // per place
		for (Place p : places)
			fees += 0.25f;
		
		if (fees < 1 && places.size() > 0)
			fees = 1f;
		
		return fees;
	}
	
	public float getTotal () {
		float total = 0f;
		for (Place p : places)
			total += p.getPrice();
		
		total += this.getFees();
		return total;
	}
}
