/* TestAnswerToStringConverter.java
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

import domain.TestAnswer;

@Component
@Transactional
public class TestAnswerToStringConverter implements Converter<TestAnswer, String> {

	@Override
	public String convert(TestAnswer testAnswer) {
		String result;

		if (testAnswer == null)
			result = null;
		else
			result = String.valueOf(testAnswer.getId());

		return result;
	}

}
