package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ServiceRepository;
import domain.Booking;
import domain.Comment;
import domain.FeePayment;
import domain.Gym;
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
	public ServiceEntity create() {
		Assert.isTrue(actorService.checkAuthority("ADMIN"),
				"Only an admin can create services");
		
		ServiceEntity result;
		Collection<Gym> gyms;
		Collection<Comment> comments;
		Collection<String> pictures;
		Collection<Booking> bookings;
		
		gyms = new ArrayList<>();
		pictures = new ArrayList<>();
		comments = new ArrayList<>();
		bookings = new ArrayList<>();
		
		
		result = new ServiceEntity();
		
		result.setGyms(gyms);
		result.setPictures(pictures);
		result.setComments(comments);
		result.setBookings(bookings);		
		
		return result;
	}
	
	public void save(ServiceEntity service) {
		Assert.notNull(service);
		
		serviceRepository.save(service);		
	}
	
	public void saveToEdit(ServiceEntity service) {
		Assert.notNull(service);
		Assert.isTrue(actorService.checkAuthority("ADMIN"),
				"Only an admin can save services");
		
		if(service.getId() != 0) {			
			ServiceEntity servicePreSave;
			Collection<Booking> bookings;
			Collection<Gym> gyms;
			Collection<Comment> comments;
			
			servicePreSave = serviceRepository.findOne(service.getId());
			
			if(servicePreSave.getName().equals("Fitness")) {
				Assert.isTrue(service.getName().equals("Fitness"), "El servicio siempre debe llamarse Fitness");
			}
			
			bookings = servicePreSave.getBookings();
			gyms = servicePreSave.getGyms();
			comments = servicePreSave.getComments();
			
			Assert.isTrue(service.getBookings().containsAll(bookings) && service.getBookings().size() == bookings.size());
			Assert.isTrue(service.getGyms().containsAll(gyms) && service.getGyms().size() == gyms.size());
			Assert.isTrue(service.getComments().containsAll(comments) && service.getComments().size() == comments.size());
		}
		
		serviceRepository.save(service);
	}
	
	public void delete(ServiceEntity service) {
		Assert.notNull(service);
		Assert.isTrue(service.getId() != 0);
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can delete services");
		Assert.isTrue(service.getGyms().isEmpty());
		Assert.isTrue(service.getBookings().isEmpty());
		Assert.isTrue(service.getComments().isEmpty());
		Assert.isTrue(service.getName() != "Fitness", "El servicio de Fitness lo pueden tener todos los gimnasios, luego no se deberá borrar");
		
		serviceRepository.delete(service);
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
	
	public Collection<ArrayList<Integer>> numbersOfCustomersByService(Collection<ServiceEntity> services) {
		Collection<ArrayList<Integer>> result;
		Integer customerNumber;
		ArrayList<Integer> result2;
		
		result = new ArrayList<ArrayList<Integer>>();
		
		for(ServiceEntity service : services) {
			result2 = new ArrayList<Integer>();
			customerNumber = customerService.numbersOfCustomersByService(service.getId());
			result2.add(service.getId());
			result2.add(customerNumber);
			result.add(result2);
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
