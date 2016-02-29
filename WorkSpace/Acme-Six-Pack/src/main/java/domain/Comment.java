package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "deleted") })
public class Comment extends DomainEntity{

	// Constructors -----------------------------------------------------------

	// Attributes -------------------------------------------------------------
	private String text;
	private Date moment;
	private int starRating;
	private boolean deleted;
	
	@NotBlank
	@NotNull
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/dd/MM HH:mm")
	@NotNull
	public Date getMoment() {
		return moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	//No debe ser null
	@Range(min = 0, max = 3)
	@Valid
	public int getStarRating() {
		return starRating;
	}
	public void setStarRating(int starRating) {
		this.starRating = starRating;
	}
	
	public boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	// Relationships ----------------------------------------------------------
	private Actor actor;
	private CommentedEntity commentedEntity;
	
	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public CommentedEntity getCommentedEntity() {
		return commentedEntity;
	}
	public void setCommentedEntity(CommentedEntity commentedEntity) {
		this.commentedEntity = commentedEntity;
	}
	
	
}
