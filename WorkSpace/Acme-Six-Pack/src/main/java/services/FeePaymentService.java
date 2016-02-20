package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Customer;
import domain.FeePayment;
import domain.Gym;
import repositories.FeePaymentRepository;

@Service
@Transactional
public class FeePaymentService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private FeePaymentRepository feePaymentRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private GymService gymService;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public FeePaymentService() {
		super();
	}


	// Simple CRUD methods ----------------------------------------------------
	public FeePayment create(int gymId) {
		FeePayment result;
		Customer customer;
		Date moment;
		Gym gym;
		
		customer = customerService.findByPrincipal();
		moment = new Date();
		gym = gymService.findOne(gymId);
		
		result = new FeePayment();
		
		result.setCustomer(customer);
		result.setPaymentMoment(moment);
		result.setGym(gym);
		result.setAmount(gym.getFee());
		
		return result;
	}
	
	public void save(FeePayment feePayment) {
		Assert.notNull(feePayment);
		Date activeMomentLimit;
		
		activeMomentLimit = new Date();
		
		Calendar c = Calendar.getInstance();
		c.setTime(feePayment.getPaymentMoment());
		c.add(Calendar.MONTH, +6);
		activeMomentLimit.setTime(c.getTimeInMillis());

		
		Assert.isTrue(feePayment.getActiveMoment().compareTo(feePayment.getPaymentMoment()) > 0, "La fecha de activación del pago debe ser mayor que el momento actual");
		Assert.isTrue(feePayment.getActiveMoment().compareTo(activeMomentLimit) < 0, "La fecha de activación del pago no debe sobrepasar 6 meses el momento actual");
		
		if (feePayment.getId() == 0) {
			Date paymentMoment;
			Date moment;
			Date inactiveMoment;
			Gym gym;
			Customer customer;
			FeePayment fee;

			paymentMoment = new Date();

			moment = feePayment.getActiveMoment();
			Calendar ca = Calendar.getInstance();
			ca.setTime(moment);
			ca.add(Calendar.DAY_OF_MONTH, +30);

			inactiveMoment = new Date();
			inactiveMoment.setTime(ca.getTimeInMillis());

			feePayment.setPaymentMoment(paymentMoment);
			feePayment.setInactiveMoment(inactiveMoment);
			
			fee = feePaymentRepository.save(feePayment);
			
			gym = fee.getGym();
			customer = fee.getCustomer();
			
			gym.addFeePayment(fee);
			customer.addFeePayment(fee);
			
			gymService.save(gym);
			customerService.save(customer);

		} else {	
			feePaymentRepository.save(feePayment);
		}
	}
	
	public FeePayment findOne(int feePaymentId) {
		FeePayment result;
		
		result = feePaymentRepository.findOne(feePaymentId);
		
		return result;
	}


	

	// Other business methods -------------------------------------------------
	public Collection<FeePayment> findAllActive() {
		Collection<FeePayment> result;
		Date moment;
		
		moment = new Date();
		
		result = feePaymentRepository.findAllActive(moment);
		
		return result;
	}


	public Collection<FeePayment> findAllByCustomer() {
		Collection<FeePayment> result;
		Customer customer;
		
		customer = customerService.findByPrincipal();
		
		result = feePaymentRepository.findAllByCustomer(customer.getId());
		
		return result;
	}


	public Collection<FeePayment> findAllByCustomerAndGym(int gymId) {
		Collection<FeePayment> result;
		Customer customer;
		
		customer = customerService.findByPrincipal();
		
		result = feePaymentRepository.findAllByCustomerAndGym(customer.getId(), gymId);
		
		return result;
	}


	
}
