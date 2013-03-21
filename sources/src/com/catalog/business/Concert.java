package com.catalog.business;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries ({
	@NamedQuery(name="Concert.find", query="SELECT c FROM Concert c WHERE LCASE(c.title) LIKE LCASE(:sTitle)"),
	@NamedQuery(name="Concert.last", query="SELECT c FROM Concert c ORDER BY id DESC LIMIT 4"),
})
public class Concert {
	
	@TableGenerator(name = "concertGen", table="GEN_ID",
			pkColumnName="GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "CONCERT_ID",
			allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "concertGen")
	private int id;
	@NotNull
	private String title;
	private int duration;
	
	@ManyToOne
	private Band band;
	
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="concert")
	private Collection<Representation> representations;
	
	public Concert(String title, int duration, Band band, Collection<Representation> representations) {
		this.title = title;
		this.duration = duration;
		this.band = band;
		this.representations = new ArrayList<Representation>();
		if (representations != null)
			this.representations.addAll(representations);
	}
	
	public Concert () { }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Band getBand() {
		return band;
	}

	public void setBand(Band band) {
		this.band = band;
	}

	public Collection<Representation> getRepresentations() {
		return representations;
	}

	public void setRepresentations(Collection<Representation> representations) {
		this.representations = representations;
	}
	
	public void addRepresentation(Representation representation) {
		representations.add(representation);
	}
}
