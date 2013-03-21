package com.catalog.business;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity
@NamedQuery(name="Band.find", query="SELECT b FROM Band b WHERE LCASE(b.name) LIKE LCASE(:sName)")
public class Band {
	
	@TableGenerator(name = "bandGen", table="GEN_ID",
			pkColumnName="GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "BAND_ID",
			allocationSize = 1) 
	@Id 
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "bandGen")
	private int id;
	
	private String name;
	private String description;
	private String style;
	
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="band")
	private Collection<Concert> concerts;
	
	public Band(String name, String description, String style, Collection<Concert> concerts) {
		this.name = name;
		this.description = description;
		this.style = style;
		this.concerts = new ArrayList<Concert>();
		if (concerts != null) this.concerts.addAll(concerts);
	}

	public Band () { }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
	public Collection<Concert> getConcerts() {
		return concerts;
	}

	public void setConcerts(Collection<Concert> concerts) {
		this.concerts = concerts;
	}
	
	public void addConcert(Concert concert) {
		concerts.add(concert);
	}
}
