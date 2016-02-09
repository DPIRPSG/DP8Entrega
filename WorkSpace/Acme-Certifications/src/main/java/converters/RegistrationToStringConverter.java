/* RegistrationToStringConverter.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Registration;

@Component
@Transactional
public class RegistrationToStringConverter implements Converter<Registration, String> {

	@Override
	public String convert(Registration registration) {
		String result;

		if (registration == null)
			result = null;
		else
			result = String.valueOf(registration.getId());

		return result;
	}

}
