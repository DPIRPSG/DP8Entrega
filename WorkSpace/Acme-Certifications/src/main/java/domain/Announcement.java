/* Announcement.java
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
import java.util.Date;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Announcement extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Announcement() {
		super();
		
		registrations = new HashSet<Registration>();
	}

	// Attributes -------------------------------------------------------------

	private String title;
	private String description;
	private Date moment;

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	// Relationships ----------------------------------------------------------

	private Exam exam;
	private Reviewer reviewer;
	private Collection<Registration> registrations;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}
		
	@Valid
	@ManyToOne(optional = true)
	public Reviewer getReviewer() {
		return reviewer;
	}

	public void setReviewer(Reviewer reviewer) {
		this.reviewer = reviewer;
	}

	@NotNull
	@OneToMany(mappedBy = "announcement")
	public Collection<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(Collection<Registration> registrations) {
		this.registrations = registrations;
	}
	
	public void addRegistration(Registration registration) {
		registrations.add(registration);
		registration.setAnnouncement(this);
	}

	public void removeRegistration(Registration registration) {
		registrations.remove(registration);
		registration.setAnnouncement(null);
	}
	
	// Derived relationships --------------------------------------------------
	
	private Certification certification;
	
	@NotNull
	@Valid
	@Transient
	public Certification getCertification() {
		Certification result;

		if (exam != null)
			result = exam.getCertification();
		else
			result = certification;

		return result;
	}

	public void setCertification(Certification certification) {
		this.certification = certification;
	}
	
}
