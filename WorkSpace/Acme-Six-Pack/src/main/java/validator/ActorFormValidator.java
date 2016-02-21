package validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import security.UserAccount;
import security.UserAccountService;
import services.ActorService;

import domain.Actor;
import domain.form.ActorForm;

@Component
public class ActorFormValidator implements Validator{

	@Autowired
	private UserAccountService uAccountService;
	
	@Autowired
	private ActorService actorService;
	
	
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ActorForm actor = (ActorForm) target;
		Actor actActor;
		UserAccount uAccountUsername;
		
		actActor = actorService.findByPrincipal();
		
		if(actor.getPassword() != null && actor.getRepeatedPassword() != null){
			if(! actor.getPassword().equals(actor.getRepeatedPassword())){
				errors.rejectValue("password", "acme.validation.notMatch");
				errors.rejectValue("repeatedPassword", "acme.validation.notMatch");
			}
		}
		
		uAccountUsername = uAccountService.findByUsername(actor.getUsername());
		
		if(uAccountUsername != null){
			errors.rejectValue("username", "acme.validation.usernameInUse");
		}

		/*errors.rejectValue("phone", "undefined.error");
		errors.rejectValue("phone", "jssjerror", "Este es un error indeseado");*/
		
		//return errors;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}


}
