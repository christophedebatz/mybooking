package com.catalog.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm {
	
	@NotEmpty
	@Size(min=1, max=50)
	private String userName;
	
	@NotEmpty
	@Size(min=1, max=20)
	private String password;
	
	private String next = null;
	
	private boolean memorize = false;

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

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

	public boolean isMemorize() {
		return memorize;
	}

	public void setMemorize(boolean memorize) {
		this.memorize = memorize;
	}
	
}
