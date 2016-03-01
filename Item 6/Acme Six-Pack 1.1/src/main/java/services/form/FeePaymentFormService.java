package services.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.FeePaymentService;

import domain.FeePayment;
import domain.form.FeePaymentForm;

@Service
@Transactional
public class FeePaymentFormService {
		// Managed repository -----------------------------------------------------

		
		// Supporting services ----------------------------------------------------

		@Autowired
		private FeePaymentService feePaymentService;		
		
		// Constructors -----------------------------------------------------------
		
		public FeePaymentFormService(){
			super();
		}



		public FeePaymentForm create(int gymId) {
			FeePaymentForm feePaymentForm;
			
			feePaymentForm = new FeePaymentForm();
			feePaymentForm.setGymId(gymId);
			
			return feePaymentForm;
		}



		public FeePayment reconstruct(FeePaymentForm feePaymentForm) {
			Assert.notNull(feePaymentForm);
			
			FeePayment feePayment;
			
			feePayment = feePaymentService.create(feePaymentForm.getGymId());
			feePayment.setActiveMoment(feePaymentForm.getActiveMoment());
			feePayment.setCreditCard(feePaymentForm.getCreditCard());
			//feePaymentService.save(feePayment);
			
			return feePayment;
		}
		
		// Other business methods -------------------------------------------------
		
				

}
