package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.SpamTerm;

import repositories.SpamTermRepository;

@Service
@Transactional 
public class SpamTermService {
 	//Managed repository -----------------------------------------------------
	
	@Autowired
	private SpamTermRepository spamTermRepository;
	
	//Supporting services ----------------------------------------------------
	
	@Autowired
	private ActorService actorService;
	
	//Constructors -----------------------------------------------------------
	
	public SpamTermService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------

	/** 
	 * Devuelve SpamTerm preparado para ser modificado. Necesita usar save para que persista en la base de datos
	 */	
	//req: 24.2
	public SpamTerm create(){
		SpamTerm result;
		
		result = new SpamTerm();
		
		return result;
	}
	
	/**
	 * Guarda un folder creado o modificado
	 */
	//req: 24.2
	public SpamTerm save(SpamTerm spamTerm){
		Assert.notNull(spamTerm);
		Assert.isTrue(actorService.checkAuthority("ADMIN"));
		
		SpamTerm result;
		
		result = spamTermRepository.save(spamTerm);
		
		return result;
	}
	

	/**
	 * Elimina un folder. No elimina carpetas del sistema
	 */
	//req: 24.2	
	public void delete(SpamTerm spamTerm){
		Assert.notNull(spamTerm);
		Assert.isTrue(spamTerm.getId() != 0);
		Assert.isTrue(actorService.checkAuthority("ADMIN"));


		spamTermRepository.delete(spamTerm);
	}
	
	public SpamTerm findOne(int spamTermId){
		SpamTerm result;
		Assert.isTrue(actorService.checkAuthority("ADMIN"));

		result = spamTermRepository.findOne(spamTermId);
		
		Assert.notNull(result);
				
		return result;
	}
	
	public Collection<SpamTerm> findAll(){
		Collection<SpamTerm> result;
		
		result = spamTermRepository.findAll();
		
		return result;
	}
	
	//Other business methods -------------------------------------------------
	

	/**
	 * Dice si el texto pasado contiene Spam
	 */
	public boolean checkSpamTerm(String text){
		boolean result;
		
		result = spamTermRepository.checkSpamTerm(text);
		
		return result;
	}
}
