package com.catalog.form;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class RegisterForm {
	
	@NotEmpty
	@Size(min=1, max=50)
	private String userName;
	
	@NotEmpty
	@Size(min=1, max=20)
	private String password;
	
	@NotEmpty
	@Size(min=1, max=50)
	private String firstName;
	
	@NotEmpty
	@Size(min=1, max=50)
	private String lastName;
	
	@NotEmpty
	@Size(min=1, max=50)
	private String email;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
