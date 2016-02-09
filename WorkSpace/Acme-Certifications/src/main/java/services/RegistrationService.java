/* RegistrationService.java
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

import repositories.RegistrationRepository;
import domain.Announcement;
import domain.Customer;
import domain.Registration;

@Service
@Transactional
public class RegistrationService {
	
	// Managed repository -----------------------------------------------------

	@Autowired
	private RegistrationRepository registrationRepository;
	
	// Supporting services ----------------------------------------------------

	@Autowired
	private CustomerService customerService;
	
	// Constructors -----------------------------------------------------------

	public RegistrationService() {
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------
	
	public Registration createRegistration(Announcement announcement) {
		Assert.notNull(announcement);
		
		Registration result;
		Date moment;
		Customer customer;
		
		moment = new Date(System.currentTimeMillis() - 1);
		customer = customerService.findByPrincipal();
		
		result = new Registration();
		result.setAnnouncement(announcement);		
		result.setMoment(moment);
		result.setOwner(customer);
		
		return result;
	}

	public void save(Registration registration) {
		Assert.notNull(registration);

		registrationRepository.save(registration);
	}
	
	public void delete(Registration registration) {
		Assert.notNull(registration);
		Assert.isTrue(registration.getId() != 0);
		
		registrationRepository.delete(registration);		
	}
	
	// Business methods -------------------------------------------------------

	public Collection<Registration> findByPrincipal() {
		Collection<Registration> result;
		Customer customer;

		customer = customerService.findByPrincipal();
		result = customer.getRegistrations();

		return result;
	}
	
	public boolean existsRegistrationForAnnouncement(Announcement announcement) {
		boolean result;
		Collection<Registration> registrations;
		
		registrations = registrationRepository.findByAnnouncementId(announcement.getId()); 
		result = !registrations.isEmpty();
		
		return result;
	}

	public Registration findByCustomerAndAnnouncement(Customer customer, Announcement announcement) {
		Assert.notNull(customer);
		Assert.notNull(announcement);
		
		Registration result;
		
		result = registrationRepository.findByCustomerIdAndAnnouncementId(customer.getId(), announcement.getId());
				
		return result;
	}

}
