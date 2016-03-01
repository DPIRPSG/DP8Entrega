package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor {

	// Constructors -----------------------------------------------------------

	// Attributes -------------------------------------------------------------
	
	private Collection<CreditCard> creditCards;
	
	@ElementCollection
	@Valid
	@Size(max = 1)
	public Collection<CreditCard> getCreditCards() {
		return creditCards;
	}
	public void setCreditCards(Collection<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}
	public CreditCard showCreditCard() {
		CreditCard result;
		Iterator<CreditCard> creditCards;
		
		creditCards = this.getCreditCards().iterator();
		
		if(creditCards.hasNext()){
			result = creditCards.next();
		}else{
			result = null;
		}
		return result;
	}
	public void modifyCreditCard(CreditCard creditCard) {
		Collection<CreditCard> newCredits;
		newCredits = new ArrayList<CreditCard>(creditCards);
		newCredits.clear();
		newCredits.add(creditCard);
		this.setCreditCards(newCredits);
		
	}
	// Relationships ----------------------------------------------------------
	private SocialIdentity socialIdentity;
	private Collection<FeePayment> feePayments;
	private Collection<Booking> bookings;
	
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
	@NotNull
	@OneToMany(mappedBy = "customer")	
	public Collection<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(Collection<Booking> booking) {
		this.bookings = booking;
	}
	
	public void addBooking(Booking booking) {
		this.bookings.add(booking);
	}

	public void removeBooking(Booking booking) {
		this.bookings.remove(booking);
	}

}
