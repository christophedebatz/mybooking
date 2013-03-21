package com.catalog.form;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class RetrieveForm {
	
	@NotEmpty
	@Size(min=1, max=50)
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
