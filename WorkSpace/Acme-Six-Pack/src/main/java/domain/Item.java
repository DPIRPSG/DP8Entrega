package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Item extends DomainEntity{

	// Constructors -----------------------------------------------------------

	// Attributes -------------------------------------------------------------
	private String sku;
	private String name;
	private String description;
	private double price;
	private Collection<String> tags;
	private String picture;
	private boolean deleted;
	
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp="^\\w{2}\\-\\w{4}$")
	@Valid
	@NotNull
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	
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
	
	//@NotNull
	@Min(0)
	@Digits(integer=9, fraction=2)
	@Valid
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@ElementCollection
	public Collection<String> getTags() {
		return tags;
	}
	public void setTags(Collection<String> tags) {
		this.tags = tags;
	}
	public boolean addTags(String e) {
		return tags.add(e);
	}
	public boolean removeTags(Object o) {
		return tags.remove(o);
	}
	
	@URL
	@Valid
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	//Al ser primitivo no permite null
	public boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	// Relationships ----------------------------------------------------------
	private Category category;
	private Collection<Comment> comments;
	private Collection<Storage> storages;
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Valid
	@OneToMany(mappedBy = "item")
	@NotNull
	public Collection<Comment> getComments() {
		return comments;
	}
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}
	
	@Valid
	@OneToMany(mappedBy = "item")
	@NotNull
	public Collection<Storage> getStorages() {
		return storages;
	}
	public void setStorages(Collection<Storage> storages) {
		this.storages = storages;
	}
	
	
}
