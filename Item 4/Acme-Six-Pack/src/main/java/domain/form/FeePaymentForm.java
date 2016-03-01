package domain.form;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import domain.CreditCard;

public class FeePaymentForm {
	
	private CreditCard creditCard;
	private Date activeMoment;
	private int gymId;
	
	
	@NotNull
	@Valid
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/dd/MM HH:mm")
	public Date getActiveMoment() {
		return activeMoment;
	}

	public void setActiveMoment(Date activeMoment) {
		this.activeMoment = activeMoment;
	}
	
	@Valid
	@NotNull
	public int getGymId() {
		return gymId;
	}

	public void setGymId(int gymId) {
		this.gymId = gymId;
	}
	
	// Validator ----------

}
