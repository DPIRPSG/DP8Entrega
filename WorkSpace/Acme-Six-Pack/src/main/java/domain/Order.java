package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "orderTable")
public class Order extends DomainEntity{

	// Constructors -----------------------------------------------------------

	// Attributes -------------------------------------------------------------
	private String ticker;
	private Date placementMoment;
	private String address;
	private Date deliveryMoment;
	private Date cancelMoment;
	private CreditCard creditCard;
	private double amount;
	
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^\\d{6}\\-\\w{4}$")
	@Valid
	@NotNull
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/dd/MM HH:mm")
	public Date getPlacementMoment() {
		return placementMoment;
	}
	public void setPlacementMoment(Date placementMoment) {
		this.placementMoment = placementMoment;
	}
	
	@NotBlank
	@NotNull
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/dd/MM HH:mm")
	public Date getDeliveryMoment() {
		return deliveryMoment;
	}
	public void setDeliveryMoment(Date deliveryMoment) {
		this.deliveryMoment = deliveryMoment;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/dd/MM HH:mm")
	public Date getCancelMoment() {
		return cancelMoment;
	}
	public void setCancelMoment(Date cancelMoment) {
		this.cancelMoment = cancelMoment;
	}
	
	@Valid
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	@Min(0)
	@Digits(integer = 9, fraction = 2)
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount){
		this.amount = amount;
	}

	// Relationships ----------------------------------------------------------
	private Clerk clerk;
	private Collection<OrderItem> orderItems;
	private Consumer consumer;
	
	@Valid
	@ManyToOne(optional = true)
	public Clerk getClerk() {
		return clerk;
	}
	public void setClerk(Clerk clerk) {
		this.clerk = clerk;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy = "order")
	@NotEmpty
	public Collection<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Collection<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Consumer getConsumer() {
		return consumer;
	}
	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	
}
