package services;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.ExchangeRate;

import repositories.ExchangeRateRepository;


@Service
@Transactional
public class ExchangeRateService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ExchangeRateRepository exchangeRateRepository;
	
	// Supporting services ----------------------------------------------------
	
	@Autowired
	private ActorService actorService;
	
	// Constructors -----------------------------------------------------------

	public ExchangeRateService(){
		super();
	}
	// Simple CRUD methods ----------------------------------------------------
	
	public ExchangeRate create(){
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can create exchangerate");

		ExchangeRate result;
		
		result = new ExchangeRate();
		
		return result;
	}
	
	public void save(ExchangeRate exchangeRate){
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can save exchangerate");
		Assert.notNull(exchangeRate);
		
		exchangeRateRepository.save(exchangeRate);
	}
	
	public void delete(ExchangeRate exchangeRate){
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can delete exchangerate");
		Assert.notNull(exchangeRate);
		
		exchangeRateRepository.delete(exchangeRate.getId());
	}
	
	public Collection<ExchangeRate> findAll(){
		Collection<ExchangeRate> result;
		
		result = exchangeRateRepository.findAll();
		
		return result;		
	}

	
	
	// Other business methods -------------------------------------------------
		
	public ExchangeRate findOne(int exchangeRateId) {
		Assert.notNull(exchangeRateId);
		
		ExchangeRate result;
		
		result = exchangeRateRepository.findOne(exchangeRateId);
		
		return result;
	}

	public ExchangeRate findOneByName(String name) {
		Assert.notNull(name);
		
		ExchangeRate result;
		
		result = exchangeRateRepository.findOneByName(name);
		
		return result;
	}
}
