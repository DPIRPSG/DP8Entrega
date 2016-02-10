package domain;


import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Booking extends DomainEntity{
	
	// Constructors -----------------------------------------------------------

	// Attributes -------------------------------------------------------------
	private Date creationMoment;
	private Date requestMoment;
	private double duration;
	private boolean approved;
	private boolean denied;
	private boolean canceled;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/dd/MM HH:mm")
	public Date getCreationMoment() {
		return creationMoment;
	}
	public void setCreationMoment(Date creationMoment) {
		this.creationMoment = creationMoment;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/dd/MM HH:mm")
	public Date getRequestMoment() {
		return requestMoment;
	}
	public void setRequestMoment(Date requestMoment) {
		this.requestMoment = requestMoment;
	}
	
	@Min(0)
	@Range(min = (long) 0.5, max = 4)
	@Digits(integer = 1, fraction = 1)	
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	
	public boolean getApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	public boolean getDenied() {
		return denied;
	}
	public void setDenied(boolean denied) {
		this.denied = denied;
	}
	
	public boolean getCanceled() {
		return canceled;
	}
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
	
	// Relationships ----------------------------------------------------------
	private Administrator administrator;
	private Customer customer;
	private Service service;

	@Valid
	@ManyToOne(optional = true)
	public Administrator getAdministrator() {
		return administrator;
	}
	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	

}
