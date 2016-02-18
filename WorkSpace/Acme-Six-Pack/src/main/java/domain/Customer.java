package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor {

	// Constructors -----------------------------------------------------------

	// Attributes -------------------------------------------------------------
	
	private CreditCard creditCard;
	
	@Valid
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	// Relationships ----------------------------------------------------------
	private SocialIdentity socialIdentity;
	private Collection<FeePayment> feePayment;
	private Collection<Booking> booking;
	
	@Valid
	@OneToOne(optional = true)
	public SocialIdentity getSocialIdentity() {
		return socialIdentity;
	}
	public void setSocialIdentity(SocialIdentity socialIdentity) {
		this.socialIdentity = socialIdentity;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy = "customer")
	public Collection<FeePayment> getFeePayment() {
		return feePayment;
	}
	public void setFeePayment(Collection<FeePayment> feePayment) {
		this.feePayment = feePayment;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy = "customer")	
	public Collection<Booking> getBooking() {
		return booking;
	}
	public void setBooking(Collection<Booking> booking) {
		this.booking = booking;
	}

}
