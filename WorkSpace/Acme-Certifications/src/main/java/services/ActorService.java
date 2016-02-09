/* ActorService.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.UserAccount;
import security.UserAccountService;
import domain.Actor;

@Service
@Transactional
public class ActorService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ActorRepository actorRepository;	
	
	// Supporting services ----------------------------------------------------
	
	@Autowired
	private UserAccountService userAccountService;
	
	// Constructors -----------------------------------------------------------

	public ActorService() {
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------
	
	public Collection<Actor> findAll() {
		Collection<Actor> result;
		
		result = actorRepository.findAll();
		Assert.notNull(result);
		
		return result;
	}

	public Actor findOne(int actorId) {
		Assert.isTrue(actorId != 0);
		
		Actor result;

		result = actorRepository.findOne(actorId);
		Assert.notNull(result);

		return result;
	}
	
	public void save(Actor actor) {
		Assert.notNull(actor);

		actorRepository.save(actor);
	}	
	
	public void delete(Actor actor) {
		Assert.notNull(actor);
		Assert.isTrue(actor.getId() != 0);
		Assert.isTrue(actorRepository.exists(actor.getId()));		
		
		actorRepository.delete(actor);
	}

	// Other business methods -------------------------------------------------


	public UserAccount findUserAccount(Actor actor) {
		Assert.notNull(actor);
		
		UserAccount result;
		
		result = userAccountService.findByActor(actor);
		
		return result;
	}
}