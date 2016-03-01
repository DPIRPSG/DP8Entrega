package validators;


import java.util.Calendar;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.CreditCard;
import domain.FeePayment;

@Component
public class CreditCardValidator implements Validator{



	@Override
	public void validate(Object target, Errors errors) {

		String rot;
		CreditCard creditCard;
		
		if(CreditCard.class.isInstance(target)){
			creditCard = (CreditCard) target;
			rot = "";
		}else if (FeePayment.class.isInstance(target)){
			FeePayment feePayment;
			
			feePayment = (FeePayment) target;
			creditCard = (CreditCard) feePayment.getCreditCard();
			rot = "creditCard.";			
		}else{
			rot = "creditCard.";
			creditCard = null;
		}
		if(!compruebaFecha(creditCard)){
			errors.rejectValue(rot + "expirationYear", "acme.validation.invalidDate");
			errors.rejectValue(rot + "expirationMonth", "acme.validation.invalidDate");
		}
		
		

	}
	
	private boolean compruebaFecha(CreditCard creditCard) {
		boolean result;
		Calendar c;
		int cMonth, cYear;
		
		result = false;
		
		c = Calendar.getInstance();
				
		cMonth = c.get(2) + 1; //Obtenemos numero del mes (Enero es 0)
		cYear = c.get(1); //Obtenemos año
		
		if(creditCard.getExpirationYear() > cYear) {
			result = true;
		} else if(creditCard.getExpirationYear() == cYear) {
			if(creditCard.getExpirationMonth() >= cMonth) {
				result = true;
			}
		}
		return result;		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return CreditCard.class.isAssignableFrom(clazz);
	}


}
