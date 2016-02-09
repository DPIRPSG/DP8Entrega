package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Tax extends DomainEntity{

	// Constructors -----------------------------------------------------------

	// Attributes -------------------------------------------------------------
	private String name;
	private double value;
	
	@NotBlank
	@NotNull
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//Al ser primitivo no necesita ser @NotNull
	@Min(0)
	@Digits(integer=3, fraction=2)
	@Valid
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	// Relationships ----------------------------------------------------------

}
