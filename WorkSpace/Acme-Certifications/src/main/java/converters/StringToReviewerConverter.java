/* StringToReviewerConverter.java
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

import repositories.ReviewerRepository;
import domain.Reviewer;

@Component
@Transactional
public class StringToReviewerConverter implements Converter<String, Reviewer> {

	@Autowired
	ReviewerRepository reviewerRepository;

	@Override
	public Reviewer convert(String text) {
		Reviewer result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = reviewerRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
