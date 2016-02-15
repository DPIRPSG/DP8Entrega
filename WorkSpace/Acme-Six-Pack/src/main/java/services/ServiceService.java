package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

	public ServiceEntity findOne(int serviceId) {
		ServiceEntity result;
		
		result = serviceRepository.findOne(serviceId);
		
		return result;
	}
	
	
	/**
	 * Lista los gyms
	 */
	// req: 12.5
	public Collection<ServiceEntity> findAll() {
		Collection<ServiceEntity> result;

		result = serviceRepository.findAll();

		return result;
	}

	

	
	
}
