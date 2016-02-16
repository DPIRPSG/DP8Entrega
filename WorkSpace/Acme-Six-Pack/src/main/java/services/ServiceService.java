package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ServiceRepository;
import domain.ServiceEntity;

@Service
@Transactional
public class ServiceService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ServiceRepository serviceRepository;

	// Supporting services ----------------------------------------------------
	
	// Constructors -----------------------------------------------------------

	public ServiceService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	// Other business methods -------------------------------------------------
	
	/**
	 * Lista un Service concreto
	 */

	public ServiceEntity findOne(int serviceId) {
		ServiceEntity result;
		
		result = serviceRepository.findOne(serviceId);
		Assert.notNull(result, "There is no service with the id: " + serviceId);
		
		return result;
		
	}
	
	/**
	 * Lista todos los Services
	 */
	
	public Collection<ServiceEntity> findAll() {
		Collection<ServiceEntity> result;

		result = serviceRepository.findAll();

		return result;
	}
	
	/**
	 * Lista los Services de un Gym dado
	 */
	
	public Collection<ServiceEntity> findAllByGym(int gymId) {
		Collection<ServiceEntity> result;

		result = serviceRepository.findAllByGym(gymId);

		return result;
	}
	
}
