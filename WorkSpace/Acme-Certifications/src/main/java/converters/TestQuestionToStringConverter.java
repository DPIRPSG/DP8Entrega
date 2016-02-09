/* TestQuestionToStringConverter.java
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

import domain.TestQuestion;

@Component
@Transactional
public class TestQuestionToStringConverter implements Converter<TestQuestion, String> {

	@Override
	public String convert(TestQuestion testQuestion) {
		String result;

		if (testQuestion == null)
			result = null;
		else
			result = String.valueOf(testQuestion.getId());

		return result;
	}

}
