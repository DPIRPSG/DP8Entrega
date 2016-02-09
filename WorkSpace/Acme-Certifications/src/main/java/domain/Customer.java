/* Customer.java
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
public class Customer extends Actor {

	// Constructors -----------------------------------------------------------

	public Customer() {
		super();

		registrations = new HashSet<Registration>();
	}
	
	// Attributes -------------------------------------------------------------

	// Relationships ----------------------------------------------------------

	private Collection<Registration> registrations;

	@NotNull	
	@OneToMany(mappedBy = "owner")
	public Collection<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(Collection<Registration> registrations) {
		this.registrations = registrations;
	}

	public void addRegistration(Registration registration) {		
		registrations.add(registration);
		registration.setOwner(this);
	}

	public void removeRegistration(Registration registration) {		
		registrations.remove(registration);
		registration.setOwner(null);
	}

}
