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
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity
public class Representation {
	
	@TableGenerator(name = "representationGen", table="GEN_ID",
			pkColumnName="GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "REPRESENTATION_ID",
			allocationSize = 1) 
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "representationGen")
	private int id;
	private Date date;
	@ManyToOne
	private Concert concert;
	@ManyToOne
	private Salle salle;
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="representation")
	private Collection<Place> places;
	
	public Representation(Date date, Concert concert, Salle salle, Collection<Place> places) {
		this.date = date;
		this.concert = concert;
		this.salle = salle;
		this.places = new ArrayList<Place>();
		if (places != null) this.places.addAll(places);
	}
	
	public Representation () { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Concert getConcert() {
		return concert;
	}

	public void setConcert(Concert concert) {
		this.concert = concert;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Collection<Place> getPlaces() {
		return places;
	}

	public void setPlaces(Collection<Place> places) {
		this.places = places;
	}
	
	public void addPlaces(Place place) {
		this.places.add(place);
	}
}
