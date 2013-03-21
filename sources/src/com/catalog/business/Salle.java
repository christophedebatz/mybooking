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
import javax.validation.constraints.NotNull;

@Entity
@NamedQuery(name="Salle.find", query="SELECT s FROM Salle s WHERE LCASE(s.name) LIKE LCASE(:sName)")
public class Salle {
	
	@TableGenerator(name = "salleGen", table="GEN_ID",
			pkColumnName="GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "SALLE_ID",
			allocationSize = 1) 
	@Id 
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "salleGen")
	private int id;
	
	@NotNull
	private String name;
	
	private int maxCapacity;
	private String road;
	private int zipCode;
	private String city;
	private String country;
	
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="salle")
	private Collection<Representation> representations;
	
	public Salle(String name, int maxCapacity, String road, int zipCode, String city, 
			String country, Collection<Representation> representations) {
		this.name = name;
		this.maxCapacity = maxCapacity;
		this.road = road;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
		this.representations = new ArrayList<Representation>();
		if (representations != null) this.representations.addAll(representations);
	}
	
	
	public Salle () { }
	
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

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public void addRepresentation(Representation representation) {
		this.representations.add(representation);
	}


	public Collection<Representation> getRepresentations() {
		return representations;
	}


	public void setRepresentations(Collection<Representation> representations) {
		this.representations = representations;
	}
}
