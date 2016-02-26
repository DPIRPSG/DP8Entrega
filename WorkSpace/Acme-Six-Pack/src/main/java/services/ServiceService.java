package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ServiceRepository;
import domain.Booking;
import domain.FeePayment;
import domain.ServiceEntity;

@Service
@Transactional
public class ServiceService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ServiceRepository serviceRepository;

	// Supporting services ----------------------------------------------------
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private FeePaymentService feePaymentService;
	
	@Autowired
	private BookingService bookingService;
	// Constructors -----------------------------------------------------------

	public ServiceService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public void save(ServiceEntity service) {
		Assert.notNull(service);
		
		serviceRepository.save(service);		
	}
	
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
	
	public Collection<ServiceEntity> findMostCommented() {
		Assert.isTrue(actorService.checkAuthority("ADMIN"));
		
		Collection<ServiceEntity> result;

		result = serviceRepository.findMostCommented();

		return result;
	}
	
	public Collection<String> numbersOfCustomersByService(Collection<ServiceEntity> services) {
		Collection<String> result;
		Integer customerNumber;
		String numberOfCustomerInService;
		
		result = new ArrayList<>();
		customerNumber = 0;
		numberOfCustomerInService = null;
		
		for(ServiceEntity service : services) {
			customerNumber = customerService.numbersOfCustomersByService(service.getId());
			numberOfCustomerInService = service.getName() + ": " + customerNumber;
			result.add(numberOfCustomerInService);
		}
		
		return result;
	}

	public ServiceEntity findOneByName(String name) {
		Assert.notNull(name);
		
		ServiceEntity result;
		
		result = serviceRepository.findOneByName(name);
		
		return result;
	}
	
	public Collection<ServiceEntity> findAllWithoutFitness() {
		Collection<ServiceEntity> result;
		
		result = serviceRepository.findAllWithoutFitness();
		
		return result;
	}
	
	/*public Collection<ServiceEntity> findAllPaidAndNotBookedByCustomerId(int customerId) {
		Collection<ServiceEntity> result;
		
		result = serviceRepository.findAllPaidAndNotBookedByCustomerId(customerId);
		
		return result;
	}*/
	
	public Collection<ServiceEntity> findAllPaidAndNotBookedByCustomerId(
			int customerId) {
		Collection<ServiceEntity> result;
		Collection<ServiceEntity> services;
		Collection<FeePayment> fees;
		Collection<Booking> bookings;

		result = new ArrayList<ServiceEntity>();
		fees = feePaymentService.findAllActiveByCustomer();
		for(FeePayment fee : fees) {
			services = fee.getGym().getServices();
			for(ServiceEntity service : services) {
				if(!result.contains(service)) {
					result.add(service);
				}
			}
		}

		bookings = bookingService.findAllByCustomer();
		for(Booking booking : bookings) {
			if(result.contains(booking.getService())) {
				result.remove(booking.getService());
			}
		}
		
		return result;
	}
	
	/* Query 3 */
	public Collection<ServiceEntity> findMostPopularService(){
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can open the dashboard");
		
		Collection<ServiceEntity> result;
		
		result = serviceRepository.findMostPopularService();
		
		return result;
	}
	
	/* Query 4 */
	public Collection<ServiceEntity> findLeastPopularService(){
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can open the dashboard");
		
		Collection<ServiceEntity> result;
		
		result = serviceRepository.findLeastPopularService();
		
		return result;
	}
	
	/* Query 13 */
	public Double findAverageNumberOfCommentsPerService(){
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can open the dashboard");
		
		Double result;
		
		result = serviceRepository.findAverageNumberOfCommentsPerService();
		
		return result;
	}
}
