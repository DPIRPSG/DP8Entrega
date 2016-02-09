/* StringToExamConverter.java
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

import repositories.ExamRepository;
import domain.Exam;

@Component
@Transactional
public class StringToExamConverter implements Converter<String, Exam> {

	@Autowired
	ExamRepository examRepository;

	@Override
	public Exam convert(String text) {
		Exam result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = examRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
