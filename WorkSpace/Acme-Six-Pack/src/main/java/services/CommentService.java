package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;
import domain.Gym;
import domain.ServiceEntity;

@Service
@Transactional
public class CommentService {
 	//Managed repository -----------------------------------------------------

	@Autowired
	private CommentRepository commentRepository;
	
	//Supporting services ----------------------------------------------------
	
	@Autowired
	private ActorService actorService;

	//Constructors -----------------------------------------------------------
	
	public CommentService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	/**
	 * Crea un comment
	 */
	
	private Comment create(){
		Comment result;
		
		result = new Comment();
		
		return result;
	}
	
	/**
	 * Guarda un comment creado
	 */
	
	public void save(Comment comment){
		Assert.notNull(comment);

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
	
	
	
	//Other business methods -------------------------------------------------
	
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

	/**
	 * Lista todos los comentarios de un Gym
	 */

	public Collection<Comment> findAllByGym(Gym gym){
		Assert.notNull(gym);
		Assert.isTrue(gym.getId() != 0);
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can see this stats");
		
		Collection<Comment> result;
		
		result = commentRepository.findAllByGym(gym.getId());
		
		return result;
		
	}
	
	/**
	 * Lista todos los comentarios de un Service
	 */
	
	public Collection<Comment> findAllByService(ServiceEntity service){
		Assert.notNull(service);
		Assert.isTrue(service.getId() != 0);
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can see this stats");
		
		Collection<Comment> result;
		
		result = commentRepository.findAllByService(service.getId());
		
		return result;
		
	}
	
	/**
	 * Lista todos los comentarios no eliminados
	 */
	
	public Collection<Comment> findAllNotDeleted(){
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can see this stats");
		
		Collection<Comment> result;
		
		result = commentRepository.findAllNotDeleted();
		
		return result;
		
	}
}
