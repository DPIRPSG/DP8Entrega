/* CertificationService.java
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
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CertificationRepository;
import domain.Certification;

@Service
@Transactional
public class CertificationService {
	
	// Managed repository -----------------------------------------------------

	@Autowired
	private CertificationRepository certificationRepository;
	
	// Supporting services ----------------------------------------------------
	
	// Constructors -----------------------------------------------------------

	public CertificationService() {
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------
	
	public Collection<Certification> findAll() {
		Collection<Certification> result;

		result = certificationRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	// Other business methods -------------------------------------------------

	public Collection<Certification> findAllActive() {
		Collection<Certification> result;
		Date currentMoment;

		currentMoment = new Date();
		result = certificationRepository.findAllActive(currentMoment);		
		Assert.notNull(result);
		
		return result;
	}

}