package com.catalog.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class SearchForm {
		
		@NotEmpty
		@Size(min=1, max=100)
		private String search;

		public String getSearch() {
			return search;
		}

		public void setSearch(String search) {
			this.search = search;
		}
}
