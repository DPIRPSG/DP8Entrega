/* OpenAnswerToStringConverter.java
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

import domain.OpenAnswer;

@Component
@Transactional
public class OpenAnswerToStringConverter implements Converter<OpenAnswer, String> {

	@Override
	public String convert(OpenAnswer openAnswer) {
		String result;

		if (openAnswer == null)
			result = null;
		else
			result = String.valueOf(openAnswer.getId());

		return result;
	}

}
