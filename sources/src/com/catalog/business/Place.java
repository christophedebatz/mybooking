package com.catalog.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

@Entity
public class Place {
	
	@TableGenerator(name = "placeGen", table="GEN_ID",
			pkColumnName="GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "PLACE_ID",
			allocationSize = 1) 
	@Id 
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "placeGen")
	private int id;
	
	@NotNull
	private String type;
	private float price;
	
	@ManyToOne
	@NotNull
	private Representation representation;
	
	@ManyToOne
	private CustomerOrder customerOrder;
	
	public Place(String type, float price, Representation representation, CustomerOrder customerOrder) {
		this.type = type;
		this.price = price;
		this.representation = representation;
		this.customerOrder = customerOrder;
	}
	
	
	public Place () { }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Representation getRepresentation() {
		return representation;
	}

	public void setRepresentation(Representation representation) {
		this.representation = representation;
	}

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}
	
	public static boolean isInPlacesList (List<Place> places, int pid) {
		for (Place p : places) {
			if (p.getId() == pid)
				return true;
		}
		return false;
	}
}
