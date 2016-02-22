package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "serviceTable")
public class ServiceEntity extends CommentedEntity{

	// Constructors -----------------------------------------------------------

	// Attributes -------------------------------------------------------------
	private Collection<String> pictures;

	@ElementCollection
	@Valid
	public Collection<String> getPictures() {
		return pictures;
	}
	public void setPictures(Collection<String> pictures) {
		this.pictures = pictures;
	}


	// Relationships ----------------------------------------------------------
	private Collection<Gym> gyms;
	private Collection<Booking> bookings;
	

	@NotNull
	@Valid
	@ManyToMany
	public Collection<Gym> getGyms() {
		return gyms;
	}
	public void setGyms(Collection<Gym> gyms) {
		this.gyms = gyms;
	}
	
	public void addGym(Gym gym) {
		this.gyms.add(gym);
	}

	public void removeGym(Gym gym) {
		this.gyms.remove(gym);
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy = "service")
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
