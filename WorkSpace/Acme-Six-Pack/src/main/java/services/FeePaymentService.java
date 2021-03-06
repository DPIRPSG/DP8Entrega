package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.CreditCard;
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
	
	// Supporting services ----------------------------------------------------

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private GymService gymService;
	
	@Autowired
	private ActorService actorService;

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
		result.setCreditCard(customer.showCreditCard());
		
		return result;
	}
	
	public void save(FeePayment feePayment) {
		Assert.notNull(feePayment);
		//Los checks de quien puede realizar modificaciones est�n dentro del if/else
		if (feePayment.getId() == 0) {
			Assert.isTrue(compruebaFecha(feePayment.getCreditCard()), "La tarjeta de credito no puede estar caducada");
			
			Date paymentMoment;
			Date moment;
			Date inactiveMoment;
			Gym gym;
			Customer customer, actCustomer;
			FeePayment fee;
			Date activeMomentLimit;
			
			actCustomer = customerService.findByPrincipal();
			Assert.isTrue(actCustomer.getId() == feePayment.getCustomer().getId(), "feePayment.checkAuthority.create.notOwner");
			
			activeMomentLimit = new Date();
			
			Calendar c = Calendar.getInstance();
			c.setTime(feePayment.getPaymentMoment());
			c.add(Calendar.MONTH, +6);
			activeMomentLimit.setTime(c.getTimeInMillis());

			
			Assert.isTrue(feePayment.getActiveMoment().compareTo(feePayment.getPaymentMoment()) > 0, "La fecha de activaci�n del pago debe ser mayor que el momento actual");
			Assert.isTrue(feePayment.getActiveMoment().compareTo(activeMomentLimit) < 0, "La fecha de activaci�n del pago no debe sobrepasar 6 meses el momento actual");

			paymentMoment = new Date();

			moment = feePayment.getActiveMoment();
			Calendar ca = Calendar.getInstance();
			ca.setTime(moment);
			ca.add(Calendar.DAY_OF_MONTH, +30);

			inactiveMoment = new Date();
			inactiveMoment.setTime(ca.getTimeInMillis());

			feePayment.setPaymentMoment(paymentMoment);
			feePayment.setInactiveMoment(inactiveMoment);
			
			Assert.isTrue(compruebaPago(feePayment), "No puede coincidir con alg�n pago activo");
			
			fee = feePaymentRepository.save(feePayment);
			
			gym = fee.getGym();
			customer = fee.getCustomer();
			
			gym.addFeePayment(fee);
			customer.addFeePayment(fee);
			
			gymService.save(gym);
			customerService.save(customer);
		} else {
			Assert.isTrue(actorService.checkAuthority("ADMIN"), "feePayment.checkAuthority.edit.notAdmin");
			
			FeePayment feePreSave;
			
			feePreSave = this.findOne(feePayment.getId());
			
			//Los datos de los Hidden son iguales, para evitar el POST Hacking
			Assert.isTrue(feePayment.getCustomer().getId() == feePreSave.getCustomer().getId());
			Assert.isTrue(feePayment.getGym().getId() == feePreSave.getGym().getId());
			Assert.isTrue(feePayment.getPaymentMoment().compareTo(feePreSave.getPaymentMoment()) == 0);
			Assert.isTrue(compruebaCreditCard(feePayment.getCreditCard(),feePreSave.getCreditCard()));
			Assert.isTrue(feePayment.getActiveMoment().compareTo(feePreSave.getActiveMoment()) == 0);
			Assert.isTrue(feePayment.getAmount() == feePreSave.getAmount());
			
			Assert.isTrue(feePayment.getInactiveMoment().after(feePreSave.getInactiveMoment()), "the new inactivation moment must be after the current inactivation moment.");
			
			feePaymentRepository.save(feePayment);
		}
	}


	public FeePayment findOne(int feePaymentId) {
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Solo puede hacer esto un admin");
		
		FeePayment result;
		
		result = feePaymentRepository.findOne(feePaymentId);
		
		return result;
	}


	

	// Other business methods -------------------------------------------------
	public Collection<FeePayment> findAll() {
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Solo puede hacer esto un admin");
		
		Collection<FeePayment> result;
		
		result = feePaymentRepository.findAll();
		
		return result;
	}


	public Collection<FeePayment> findAllByCustomer() {
		Collection<FeePayment> result;
		Customer customer;
		
		customer = customerService.findByPrincipal();
		
		result = feePaymentRepository.findAllByCustomer(customer.getId());
		
		return result;
	}
	
	public Collection<FeePayment> findAllActiveByCustomer() {
		Collection<FeePayment> result;
		Date moment;
		Customer customer;
		
		moment = new Date();
		customer = customerService.findByPrincipal();
		
		result = feePaymentRepository.findAllActiveByCustomer(moment, customer.getId());
		
		return result;
	}


	public Collection<FeePayment> findAllByCustomerAndGym(int gymId) {
		Collection<FeePayment> result;
		Customer customer;
		
		customer = customerService.findByPrincipal();
		
		result = feePaymentRepository.findAllByCustomerAndGym(customer.getId(), gymId);
		
		return result;
	}

	private boolean compruebaFecha(CreditCard creditCard) {
		boolean result;
		Calendar c;
		int cMonth, cYear;
		
		result = false;
		
		c = Calendar.getInstance();
				
		cMonth = c.get(2) + 1; //Obtenemos numero del mes (Enero es 0)
		cYear = c.get(1); //Obtenemos a�o
		
		if(creditCard.getExpirationYear() > cYear) {
			result = true;
		} else if(creditCard.getExpirationYear() == cYear) {
			if(creditCard.getExpirationMonth() >= cMonth) {
				result = true;
			}
		}
		return result;		
	}
	/**
	 * Comprueba que al hacer un pago de un gym ya pagado no se pisen los tiempos de activacion
	 * @param activeMoment
	 * @return
	 */
	private boolean compruebaPago(FeePayment feePayment) {
		boolean result;
		Collection<FeePayment> fees;
		Date activeMoment;
		Date inactiveMoment;

		result = true;
		fees = this.findAllByCustomer();
		if (!fees.isEmpty()) {
			for (FeePayment fee : fees) {
				if (feePayment.getGym() == fee.getGym()) {
					activeMoment = fee.getActiveMoment();
					inactiveMoment = fee.getInactiveMoment();

					if (feePayment.getActiveMoment().compareTo(activeMoment) >= 0
							&& feePayment.getActiveMoment().compareTo(
									inactiveMoment) <= 0) {
						result = false;
					} else if (feePayment.getInactiveMoment().compareTo(
							activeMoment) >= 0
							&& feePayment.getInactiveMoment().compareTo(
									inactiveMoment) <= 0) {
						result = false;
					}
				}
			}
		}

		return result;
	}
	
	/**
	 * Comprueba que sea la misma creditCard
	 * @param creditCard
	 * @param creditCard2
	 * @return
	 */
	private boolean compruebaCreditCard(CreditCard creditCard1,
			CreditCard creditCard2) {
		boolean result;		
		result = true;
		
		if(!creditCard1.getBrandName().equals(creditCard2.getBrandName())) {
			result = false;
		}
		if(!creditCard1.getHolderName().equals(creditCard2.getHolderName())) {
			result = false;
		}
		if(!creditCard1.getNumber().equals(creditCard2.getNumber())) {
			result = false;
		}
		if(creditCard1.getExpirationMonth() != creditCard2.getExpirationMonth()) {
			result = false;
		}
		if(creditCard1.getExpirationYear() != creditCard2.getExpirationYear()) {
			result = false;
		}
		if(creditCard1.getCvvCode() != creditCard2.getCvvCode()) {
			result = false;
		}
		
		return result;
	}
	
}
