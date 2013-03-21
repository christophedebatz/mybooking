package com.catalog.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity
@NamedQueries({
	@NamedQuery(name="Customer.login", query="SELECT c FROM Customer c WHERE c.username = :username AND c.password = :password"), 
	@NamedQuery(name="Customer.exists", query="SELECT c FROM Customer c WHERE c.username = :username OR c.email = :email OR (c.firstName = :firstname AND c.lastName = :lastname)"),
	@NamedQuery(name="Customer.getByEmail", query="SELECT c FROM Customer c WHERE c.email = :email"),
	@NamedQuery(name="Order.update", query="UPDATE Customer c SET c.password = :password, c.firstName = :firstName, c.lastName = :lastName, c.email = :email WHERE c.id = :id"),
})
public class Customer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableGenerator(name = "customerGen", table="GEN_ID",
			pkColumnName="GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "CUSTOMER_ID",
			allocationSize = 1) 
	@Id 
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "customerGen")
	private int id;
	private String lastName;
	private String firstName;
	private String username;
	private String password;
	private String email;
	
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="customer")
	private Collection<CustomerOrder> customerOrders;
	
	public Customer(String username, String password, String lastName, String firstName, Collection<CustomerOrder> customerOrders) {
		this.username = username;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.customerOrders = new ArrayList<CustomerOrder>();
		
		if (customerOrders != null) 
			this.customerOrders.addAll(customerOrders);
	}

	public Customer(String username, String password, String lastName, String firstName, String email, Collection<CustomerOrder> customerOrders) {
		this(username, password, lastName, firstName, customerOrders);
		this.setEmail(email);
	}

	public Customer () {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<CustomerOrder> getOrders() {
		return customerOrders;
	}

	public void setOrders(Collection<CustomerOrder> customerOrders) {
		this.customerOrders = customerOrders;
	}
	
	public void addOrder(CustomerOrder customerOrder) {
		this.customerOrders.add(customerOrder);
	}
}
