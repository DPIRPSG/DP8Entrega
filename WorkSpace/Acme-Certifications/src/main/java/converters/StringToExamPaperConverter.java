/* StringToExamPaperConverter.java
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

import repositories.ExamPaperRepository;
import domain.ExamPaper;

@Component
@Transactional
public class StringToExamPaperConverter implements Converter<String, ExamPaper> {

	@Autowired
	ExamPaperRepository examPaperRepository;

	@Override
	public ExamPaper convert(String text) {
		ExamPaper result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = examPaperRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
