package com.catalog.business;

import java.io.Serializable;

public class SessionInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int userId;
	private boolean connected = false;
	private String userName;
	private CustomerOrder order;

	public SessionInfo () { }
	
	public void setWithCustomer (Customer customer) {
		this.order = new CustomerOrder(customer, null);
		this.userName = customer.getUsername();
		this.userId = customer.getId();
		this.connected = true;
	}
	
	public void setUpdatedCustomer (Customer customer) {
		this.order.setCustomer(customer);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean getConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public CustomerOrder getOrder() {
		return order;
	}

	public void setOrder(CustomerOrder order) {
		this.order = order;
	}
	
	public void addPlaceToOrder (Place place) {
		this.order.addPlace(place);
	}
}
