/* CustomerService.java
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

import repositories.CustomerRepository;
import security.LoginService;
import security.UserAccount;
import domain.Announcement;
import domain.Customer;
import domain.Registration;

@Service
@Transactional
public class CustomerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CustomerRepository customerRepository;
	
	// Supporting services ----------------------------------------------------
	
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private AnnouncementService announcementService;
		
	// Constructors -----------------------------------------------------------

	public CustomerService() {
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------

	public Customer create() {
		Customer result;

		result = new Customer();

		return result;
	}

	public Collection<Customer> findAll() {
		Collection<Customer> result;

		result = customerRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Customer findOne(int customerId) {
		Customer result;

		result = customerRepository.findOne(customerId);
		Assert.notNull(result);

		return result;
	}

	public void save(Customer customer) {
		Assert.notNull(customer);

		customerRepository.save(customer);
	}

	public void delete(Customer customer) {
		Assert.notNull(customer);
		Assert.isTrue(customer.getId() != 0);

		customerRepository.delete(customer);
	}

	// Other business methods -------------------------------------------------
	
	public Customer findByPrincipal() {
		Customer result;
		UserAccount userAccount;
	
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}
	
	public Customer findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		Customer result;

		result = customerRepository.findByUserAccountId(userAccount.getId());		

		return result;
	}
	
	public void registerPrincipal(int announcementId) {
		Assert.isTrue(announcementId != 0);
		
		Customer customer;
		Announcement announcement;
		Registration registration;
		Date currentMoment;
		
		customer = findByPrincipal();		
		Assert.notNull(customer);
		announcement = announcementService.findOne(announcementId);
		Assert.notNull(announcement);
		currentMoment = new Date();
		Assert.isTrue(announcement.getMoment().after(currentMoment));
		registration = registrationService.findByCustomerAndAnnouncement(customer, announcement);
		Assert.isNull(registration);
				
		registration = registrationService.createRegistration(announcement);
		customer.addRegistration(registration);
		announcement.addRegistration(registration);
		
		customerRepository.save(customer);		
		announcementService.save(announcement);
		registrationService.save(registration);
	}
	
	public void unregisterPrincipal(int announcementId) {
		Assert.isTrue(announcementId != 0);
		
		Customer customer;
		Announcement announcement;
		Registration registration;
		Date currentDate;
		
		customer = findByPrincipal();		
		Assert.notNull(customer);
		announcement = announcementService.findOne(announcementId);
		Assert.notNull(announcement);
		registration = registrationService.findByCustomerAndAnnouncement(customer, announcement);
		Assert.notNull(registration);
		
		currentDate = new Date();		
		Assert.isTrue(currentDate.before(announcement.getMoment()));
				
		customer.removeRegistration(registration);
		announcement.removeRegistration(registration);
		
		customerRepository.save(customer);		
		announcementService.save(announcement);
		registrationService.delete(registration);
	}

}