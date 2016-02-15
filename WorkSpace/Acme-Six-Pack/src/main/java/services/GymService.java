package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.GymRepository;
import domain.Comment;
import domain.FeePayment;
import domain.Gym;
import domain.ServiceEntity;

@Service
@Transactional
public class GymService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private GymRepository gymRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ServiceService serviceService;
	
	// Constructors -----------------------------------------------------------

	public GymService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Gym create() {
		Gym result;
		ServiceEntity service;
		Collection<ServiceEntity> services;
		Collection<Comment> comments;
		Collection<FeePayment> feePayments;
		
		services = new ArrayList<>();
		feePayments = new ArrayList<>();
		comments = new ArrayList<>();

		result = new Gym();
		
		result.setService(services);
		result.setFeePayment(feePayments);
		result.setComments(comments);
		
		//result.addService(service);

		return result;
	}
	
	public void save(Gym gym) {
		//Assert.notNull(gym);
		
		gymRepository.save(gym);
		
	}
	
	public void delete(Gym gym) {
		//assert gym != null;
		//assert gym.getId() != 0;
		//Faltan mas Assert
		
		Collection<ServiceEntity> services;
		
		services = serviceService.findAll();
		
		for(ServiceEntity service : services) {
			service.removeGym(gym);
		}
		
		gymRepository.delete(gym);
		
	}
	
	
	

	// Other business methods -------------------------------------------------

	public Gym findOne(int gymId) {
		Gym result;
		
		result = gymRepository.findOne(gymId);
		
		return result;
	}
	
	
	/**
	 * Lista los gyms
	 */
	// req: 12.5
	public Collection<Gym> findAll() {
		Collection<Gym> result;

		result = gymRepository.findAll();

		return result;
	}

	

	
	
}
