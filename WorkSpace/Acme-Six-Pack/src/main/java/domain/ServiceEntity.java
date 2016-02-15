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

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "serviceTable")
public class ServiceEntity extends DomainEntity{

	// Constructors -----------------------------------------------------------

	// Attributes -------------------------------------------------------------
	private String name;
	private String description;
	private Collection<String> pictures;

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
	private Collection<Comment> comments;
	

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
	
	@Valid
	@NotNull
	@OneToMany(mappedBy = "service")
	public Collection<Comment> getComments() {
		return comments;
	}
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}	
}
