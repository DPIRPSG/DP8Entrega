package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.FeePayment;
import repositories.FeePaymentRepository;

@Service
@Transactional
public class FeePaymentService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private FeePaymentRepository feePaymentRepository;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public FeePaymentService() {
		super();
	}


	// Simple CRUD methods ----------------------------------------------------
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
		
		result = feePaymentRepository.findAllActive();
		
		return result;
	}
}
