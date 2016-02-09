/* Reviewer.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Reviewer extends Actor {

	// Constructors -----------------------------------------------------------

	public Reviewer() {
		super();
		
		announcements = new HashSet<Announcement>();
	}
	
	// Attributes -------------------------------------------------------------
	
	// Relationships ----------------------------------------------------------
	
	Collection<Announcement> announcements;
	
	@NotNull
	@OneToMany(mappedBy = "reviewer")
	public Collection<Announcement> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(Collection<Announcement> annoucements) {
		this.announcements = annoucements;
	}

	public void addAnnouncement(Announcement announcement) {
		announcements.add(announcement);
		announcement.setReviewer(this);
	}

	public void removeAnnouncement(Announcement announcement) {
		announcements.remove(announcement);
		announcement.setReviewer(null);
	}
	
}
