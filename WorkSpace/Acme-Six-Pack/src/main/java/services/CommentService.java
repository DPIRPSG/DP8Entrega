package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Actor;
import domain.Comment;
import domain.CommentedEntity;

@Service
@Transactional
public class CommentService {
 	//Managed repository -----------------------------------------------------

	@Autowired
	private CommentRepository commentRepository;
	
	//Supporting services ----------------------------------------------------
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private CommentedEntityService commentedEntityService;

	//Constructors -----------------------------------------------------------
	
	public CommentService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	/**
	 * Crea un comment
	 */
	
	public Comment create(int entityId){
		Assert.isTrue(actorService.checkAuthority("ADMIN") || actorService.checkAuthority("CUSTOMER"), "Only an admin or a customer can create comments");
		
		Comment result;
		CommentedEntity commentedEntity;
		Actor actor;
		
		result = new Comment();
		
		commentedEntity = commentedEntityService.findOne(entityId);
		Assert.notNull(commentedEntity, "Cannot create a Comment without a Entity asociated.");
		actor = actorService.findByPrincipal();
		Assert.notNull(actor, "Cannot create a Comment without an Actor asociated.");
		
		result.setDeleted(false);
		result.setCommentedEntity(commentedEntity);
		result.setActor(actor);
		result.setMoment(new Date()); // Se crea una fecha en este momento porque no puede ser null, pero la fecha real se fijará en el método "save"
		
		return result;
	}
	
	/**
	 * Guarda un comment creado
	 */
	
	public void save(Comment comment){
		Assert.isTrue(actorService.checkAuthority("ADMIN") || actorService.checkAuthority("CUSTOMER"), "Only an admin or a customer can save comments");

		Assert.notNull(comment);
		
		/* Añadido por Guillermo */
		Assert.isTrue(comment.getActor().getId() == actorService.findByPrincipal().getId(), "The actor must be the one logged");
		
		comment.setMoment(new Date());
		
		commentRepository.save(comment);
	}

	
	/**
	 * "Elimina" un comment
	 */
	
	public void delete(Comment comment){
		Assert.notNull(comment);
		Assert.isTrue(comment.getId() != 0);
		Assert.isTrue(comment.getDeleted() == false);
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can delete comments");

		comment.setDeleted(true);
	}
	
	/**
	 * Lista un comment concreto
	 */
	
	public Comment findOne(int commentId) {
		Comment result;
		
		result = commentRepository.findOne(commentId);
		Assert.notNull(result, "Comment " + commentId + " don't exist");
		
		return result;
	}
	
	/**
	 * Lista todos los comentarios del sistema
	 */
	
	public Collection<Comment> findAll() {
		Collection<Comment> result;
		
		result = commentRepository.findAll();
		
		return result;
	}
	
	//Other business methods -------------------------------------------------
	
	/**
	 * Lista todos los comentarios de una entidad comentada concreta
	 */
	
	public Collection<Comment> findAllByCommentedEntityId(int commentedEntityId){
		Collection<Comment> result;
		
		result = commentRepository.findAllByCommentedEntityId(commentedEntityId);
		
		return result;
	}
	
}
