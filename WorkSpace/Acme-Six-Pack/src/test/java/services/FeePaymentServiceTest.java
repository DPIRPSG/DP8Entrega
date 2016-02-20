package services;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Comment;
import domain.CreditCard;
import domain.FeePayment;
import domain.Gym;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml",
	"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class FeePaymentServiceTest extends AbstractTest{

	// Service under test -------------------------
	@Autowired
	private GymService gymService;
	
	@Autowired
	private FeePaymentService feePaymentService;
	
	// Test ---------------------------------------
	
	@Test
	public void testCreate1(){		
		Collection<Gym> gymsNotPaid;
		Collection<Gym> gymsPaid;
		FeePayment fee;
		int gymId;
		Date activeMoment;
		CreditCard creditCard;
		
		authenticate("customer1");
		
		gymsNotPaid = gymService.findAllWithoutFeePaymentActive();
		gymsPaid = gymService.findAllWithFeePaymentActive();
		System.out.println("Gyms Pagados: "+ gymsPaid);
		
		System.out.println("Empezamos a crear el Pago");
		
		gymId = gymsNotPaid.iterator().next().getId();
		activeMoment = new Date("2016/02/18 00:00");
		creditCard = new CreditCard();
		
		creditCard.setHolderName("Miguel");
		creditCard.setBrandName("Miguel");
		creditCard.setNumber("4682775285197418");
		creditCard.setExpirationMonth(8);
		creditCard.setExpirationYear(2016);
		creditCard.setCvvCode(158);
		
		fee = feePaymentService.create(gymId);
		
		fee.setActiveMoment(activeMoment);
		fee.setCreditCard(creditCard);
		
		feePaymentService.save(fee);
		
		System.out.println("Ya se ha creado?");
		gymsPaid = gymService.findAllWithFeePaymentActive();
		System.out.println(gymsPaid);
		
		authenticate(null);
	}
}