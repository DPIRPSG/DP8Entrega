package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class ShoppingCart extends DomainEntity{

	// Constructors -----------------------------------------------------------

	// Attributes -------------------------------------------------------------
	private Collection<String> comments;
	
	@ElementCollection
	public Collection<String> getComments() {
		return comments;
	}
	public void setComments(Collection<String> comments) {
		this.comments = comments;
	}
	public boolean addComment(String e) {
		return comments.add(e);
	}
	public boolean removeComment(Object o) {
		return comments.remove(o);
	}
	public void emptyComments(){
		comments.clear();
	}
	
	// Relationships ----------------------------------------------------------
	private Consumer consumer;
	private Collection<Content> contents;

	@Valid
	@NotNull
	@OneToOne(optional = false)
	public Consumer getConsumer() {
		return consumer;
	}
	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}
	
	@Valid
	@OneToMany(mappedBy = "shoppingCart")
	@NotNull
	public Collection<Content> getContents() {
		return contents;
	}
	public void setContents(Collection<Content> contents) {
		this.contents = contents;
	}
	
	
}
