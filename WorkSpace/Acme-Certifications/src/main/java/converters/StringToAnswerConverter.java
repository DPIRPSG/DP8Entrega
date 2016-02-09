/* StringToAnswerConverter.java
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

import repositories.AnswerRepository;
import domain.Answer;

@Component
@Transactional
public class StringToAnswerConverter implements Converter<String, Answer> {

	@Autowired
	AnswerRepository answerRepository;

	@Override
	public Answer convert(String text) {
		Answer result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = answerRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
