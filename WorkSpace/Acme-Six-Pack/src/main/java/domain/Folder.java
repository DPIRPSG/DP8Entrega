package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "name"), @Index(columnList = "isSystem") })
public class Folder extends DomainEntity {

	// Constructors -----------------------------------------------------------

	// Attributes -------------------------------------------------------------
	private String name;
	private boolean isSystem;

	@NotBlank
	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Al ser primitivo no permite ser nulo
	public boolean getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(boolean isSystem) {
		this.isSystem = isSystem;
	}

	// Relationships ----------------------------------------------------------
	private Actor actor;
	private Collection<Message> messages;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	@Valid
	@ManyToMany
	@NotNull
	public Collection<Message> getMessages() {
		return messages;
	}

	public void setMessages(Collection<Message> messages) {
		this.messages = messages;
	}

	public void addMessage(Message message) {
		this.messages.add(message);
	}

	public void removeMessage(Message message) {
		this.messages.remove(message);
	}

}
