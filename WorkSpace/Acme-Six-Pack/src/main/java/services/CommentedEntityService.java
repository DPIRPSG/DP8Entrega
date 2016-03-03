package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.CommentedEntityRepository;
import domain.CommentedEntity;

@Service
@Transactional
public class CommentedEntityService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CommentedEntityRepository commentedEntityRepository;
	
	// Supporting services ----------------------------------------------------

	
	
	// Constructors -----------------------------------------------------------
	
	public CommentedEntityService(){
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------

	public CommentedEntity findOne(int commentedEntityId){
		CommentedEntity result;
		
		result = commentedEntityRepository.findOneById(commentedEntityId);
		
		return result;
	}
	
	// Other business methods -------------------------------------------------
	
}
