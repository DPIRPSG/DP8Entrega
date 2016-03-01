package validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.ServiceEntity;

@Component
public class ServiceEntityValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ServiceEntity.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ServiceEntity serv = (ServiceEntity) target;
		
		
		for(String s: serv.getPictures()){
			s = s.trim();
			s.startsWith("https://");
			if(!s.startsWith("https://")){
				errors.rejectValue("pictures", "acme.validation.invalidUrlHttps");
				break;
			}
		}
		
	}

}
