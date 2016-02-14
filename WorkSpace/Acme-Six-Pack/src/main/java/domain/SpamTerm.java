package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class SpamTerm extends DomainEntity{

	// Constructors -----------------------------------------------------------

	// Attributes -------------------------------------------------------------
	private Collection<String> term;

	
	
	@ElementCollection
	public Collection<String> getTerm() {
		return term;
	}
	public void setTerm(Collection<String> term) {
		this.term = term;
	}
	
	// Relationships ----------------------------------------------------------
	
}
