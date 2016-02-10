package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Gym extends DomainEntity{

	// Constructors -----------------------------------------------------------

	// Attributes -------------------------------------------------------------
	private String name;
	private String description;
	private String postalAddress;
	private double fee;
	private String picture;
	private String phone;
	
	@NotBlank
	@NotNull
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	@NotNull
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@NotBlank
	@NotNull
	public String getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
	
	//@NotNull
	@Min(0)
	@Digits(integer=9, fraction=2)
	@Valid
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	
	@URL
	@Valid
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	@NotBlank
	@NotNull
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}



	// Relationships ----------------------------------------------------------
	private Collection<Comment> comments;
	private Collection<Service> service;
	private Collection<FeePayment> feePayment;
	
	@Valid
	@OneToMany(mappedBy = "gym")
	@NotNull
	public Collection<Comment> getComments() {
		return comments;
	}
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}
	
	@Valid
	@NotNull
	@ManyToMany
	public Collection<Service> getService() {
		return service;
	}
	public void setService(Collection<Service> service) {
		this.service = service;
	}
	
	@Valid
	@OneToMany(mappedBy = "gym")
	@NotNull	
	public Collection<FeePayment> getFeePayment() {
		return feePayment;
	}
	public void setFeePayment(Collection<FeePayment> feePayment) {
		this.feePayment = feePayment;
	}
	
	
	
	
}
