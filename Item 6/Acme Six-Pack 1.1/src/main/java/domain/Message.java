package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity{

	// Constructors -----------------------------------------------------------

	// Attributes -------------------------------------------------------------
	private String subject;
	private String body;
	private Date sentMoment;
	
	@NotBlank
	@NotNull
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@NotBlank
	@NotNull
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	public Date getSentMoment() {
		return sentMoment;
	}
	public void setSentMoment(Date sentMoment) {
		this.sentMoment = sentMoment;
	}
	
	// Relationships ----------------------------------------------------------	
	private Actor sender;
	private Collection<Actor> recipients;
	private Collection<Folder> folders;
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Actor getSender() {
		return sender;
	}
	public void setSender(Actor sender) {
		this.sender = sender;
	}

	@Valid
	@NotNull
	@ManyToMany
	@NotEmpty
	public Collection<Actor> getRecipients() {
		return recipients;
	}
	public void setRecipients(Collection<Actor> recipients) {
		this.recipients = recipients;
	}
	
	@Valid
	@NotNull
	@ManyToMany(mappedBy = "messages")
	public Collection<Folder> getFolders() {
		return folders;
	}
	public void setFolders(Collection<Folder> folders) {
		this.folders = folders;
	}
	
}
