package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Customer;
import domain.FeePayment;
import repositories.FeePaymentRepository;

@Service
@Transactional
public class FeePaymentService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private FeePaymentRepository feePaymentRepository;
	
	@Autowired
	private CustomerService customerService;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public FeePaymentService() {
		super();
	}


	// Simple CRUD methods ----------------------------------------------------
	public FeePayment create() {
		FeePayment result;
		Customer customer;
		Date moment;
		
		customer = customerService.findByPrincipal();
		moment = new Date();
		
		result = new FeePayment();
		
		result.setCustomer(customer);
		result.setPaymentMoment(moment);
		
		return result;
	}
	
	public void save(FeePayment feePayment) {
		Assert.notNull(feePayment);
		
		feePaymentRepository.save(feePayment);
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


	
}
