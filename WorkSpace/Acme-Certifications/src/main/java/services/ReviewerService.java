/* ReviewerService.java
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

import repositories.ReviewerRepository;
import domain.Reviewer;

@Service
@Transactional
public class ReviewerService {
	
	// Managed repository -----------------------------------------------------

	@Autowired
	private ReviewerRepository reviewerRepository;
	
	// Supporting services ----------------------------------------------------
				
	// Constructors -----------------------------------------------------------

	public ReviewerService() {
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------
	
	public Collection<Reviewer> findAll() {
		Collection<Reviewer> result;
		
		result = reviewerRepository.findAll();
		
		return result;
	}
	
	// Other business methods -------------------------------------------------
	
}