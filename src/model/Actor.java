package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Actor implements Serializable{
	
	private static final long serialVersionUID = 8914263305755673304L;
	
	@Id
	@GeneratedValue
	private Integer actor_id;
	
	private String first_name;
	
	private String last_name;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "dvd_release_actor", joinColumns = { 
	@JoinColumn(name = "actor_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "dvd_release_id", 
	nullable = false, updatable = false) })	
	private List<Dvd_release> dvdReleases = new ArrayList<Dvd_release>(0);

	public Integer getActor_id() {
		return actor_id;
	}


	public void setActor_id(Integer actor_id) {
		this.actor_id = actor_id;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String lastName) {
		this.last_name = lastName;
	}


	public List<Dvd_release> getDvdReleases() {
		return dvdReleases;
	}


	public void setDvdReleases(List<Dvd_release> dvdReleases) {
		this.dvdReleases = dvdReleases;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
