package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.GymRepository;
import domain.Booking;
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
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ActorService actorService;
	
	// Constructors -----------------------------------------------------------

	public GymService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Gym create() {
		Assert.isTrue(actorService.checkAuthority("ADMIN"),
				"Only an admin can create gyms");
		
		Gym result;
		ServiceEntity service;
		Collection<ServiceEntity> services;
		Collection<Comment> comments;
		Collection<FeePayment> feePayments;
		Collection<Booking> bookings;
		
		services = new ArrayList<>();
		feePayments = new ArrayList<>();
		comments = new ArrayList<>();
		bookings = new ArrayList<>();
		
		service = serviceService.findOneByName("Fitness");
		
		result = new Gym();
		
		result.setService(services);
		result.setFeePayment(feePayments);
		result.setComments(comments);
		result.setBookings(bookings);
						
		//result.addService(service);
		//service.addGym(result);
		
		
		return result;
	}

	public void save(Gym gym) {
		Assert.notNull(gym);
		Assert.isTrue(actorService.checkAuthority("ADMIN"),
				"Only an admin can save gyms");
		
		gymRepository.save(gym);
	}
	
	public void delete(Gym gym) {
		Assert.notNull(gym);
		Assert.isTrue(gym.getId() != 0);
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can delete gyms");
		Assert.isTrue(gym.getFeePayment().isEmpty());
		Assert.isTrue(gym.getBookings().isEmpty());
		Assert.isTrue(gym.getComments().isEmpty());
		
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
	
	public Collection<String> numbersOfCustomersByGym(Collection<Gym> gyms) {
		Collection<String> result;
		Integer customerNumber;
		String numberOfCustomerInGym;
		
		result = new ArrayList<>();
		customerNumber = 0;
		numberOfCustomerInGym = null;
		
		for(Gym gym : gyms) {
			customerNumber = customerService.numbersOfCustomersByGym(gym.getId());
			numberOfCustomerInGym = gym.getName() + ": " + customerNumber;
			result.add(numberOfCustomerInGym);
		}
		
		return result;
	}

	public Collection<Gym> findBySingleKeyword(String keyword){
		Assert.notNull(keyword);
		Assert.isTrue(!keyword.isEmpty());
		
		Collection<Gym> result;

		result = gymRepository.findBySingleKeyword(keyword);
		
		return result;
	}

	public Collection<Gym> findAllByService(Integer serviceId) {
		Collection<Gym> result;

		result = gymRepository.findAllByService(serviceId);

		return result;
	}

	
	
}
