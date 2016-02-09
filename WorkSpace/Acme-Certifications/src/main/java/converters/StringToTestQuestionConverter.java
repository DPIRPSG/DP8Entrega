/* StringToTestQuestionConverter.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.TestQuestionRepository;
import domain.TestQuestion;

@Component
@Transactional
public class StringToTestQuestionConverter implements Converter<String, TestQuestion> {

	@Autowired
	TestQuestionRepository testQuestionRepository;

	@Override
	public TestQuestion convert(String text) {
		TestQuestion result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = testQuestionRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
