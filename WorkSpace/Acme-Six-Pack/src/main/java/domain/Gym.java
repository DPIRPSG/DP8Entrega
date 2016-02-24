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
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Gym extends CommentedEntity{

	// Constructors -----------------------------------------------------------

	// Attributes -------------------------------------------------------------
	private String postalAddress;
	private double fee;
	private String picture;
	private String phone;
	
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
	private Collection<ServiceEntity> services;
	private Collection<FeePayment> feePayments;
	private Collection<Booking> bookings;
	
	@Valid
	@NotNull
	@ManyToMany(mappedBy = "gyms")
	@NotEmpty
	public Collection<ServiceEntity> getServices() {
		return services;
	}
	public void setServices(Collection<ServiceEntity> service) {
		this.services = service;
	}
	
	public void addService(ServiceEntity service) {
		this.services.add(service);
	}

	public void removeService(ServiceEntity service) {
		this.services.remove(service);
	}
	
	@Valid
	@OneToMany(mappedBy = "gym")
	@NotNull
	public Collection<FeePayment> getFeePayments() {
		return feePayments;
	}
	public void setFeePayments(Collection<FeePayment> feePayment) {
		this.feePayments = feePayment;
	}
	
	public void addFeePayment(FeePayment feePayment) {
		this.feePayments.add(feePayment);
	}

	public void removeFeePayment(FeePayment feePayment) {
		this.feePayments.remove(feePayment);
	}
	
	@Valid
	@OneToMany(mappedBy = "gym")
	@NotNull
	public Collection<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(Collection<Booking> bookings) {
		this.bookings = bookings;
	}
	
	public void addBooking(Booking booking) {
		this.bookings.add(booking);
	}

	public void removeBooking(Booking booking) {
		this.bookings.remove(booking);
	}
}
