package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Clerk extends Actor {

	// Constructors -----------------------------------------------------------

	// Attributes -------------------------------------------------------------

	// Relationships ----------------------------------------------------------
	private Collection<Order> orders;

	@OneToMany(mappedBy = "clerk")
	@Valid
	@NotNull
	public Collection<Order> getOrders() {
		return orders;
	}
	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}


}
