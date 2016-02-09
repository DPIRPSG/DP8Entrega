/* StringToOpenAnswerConverter.java
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

import repositories.OpenAnswerRepository;
import domain.OpenAnswer;

@Component
@Transactional
public class StringToOpenAnswerConverter implements Converter<String, OpenAnswer> {

	@Autowired
	OpenAnswerRepository openAnswerRepository;

	@Override
	public OpenAnswer convert(String text) {
		OpenAnswer result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = openAnswerRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
