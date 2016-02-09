/* ExamService.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ExamRepository;
import domain.Exam;

@Service
@Transactional
public class ExamService {
	
	// Managed repository -----------------------------------------------------

	@Autowired
	private ExamRepository examRepository;
	
	// Supporting services ----------------------------------------------------
				
	// Constructors -----------------------------------------------------------

	public ExamService() {
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------
	
	// Other business methods -------------------------------------------------

	public Collection<Exam> getExams(int certificationId) {		
		Collection<Exam> result;		

		result = examRepository.findByCertificationId(certificationId);
		Assert.notNull(result);
		Assert.isTrue(certificationId == 0 || !result.isEmpty());
		Assert.isTrue(certificationId != 0 || result.isEmpty());
		
		return result;
	}

}