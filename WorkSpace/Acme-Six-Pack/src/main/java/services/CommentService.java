package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;
import domain.CommentedEntity;
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
	
	@Autowired
	private GymService gymService;
	
	@Autowired
	private ServiceService serviceService;

	//Constructors -----------------------------------------------------------
	
	public CommentService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	/**
	 * Crea un comment
	 */
	
	public Comment create(int entityId){
		Comment result;
		CommentedEntity commentedEntity;
		
		result = new Comment();
		
//		commentedEntity = commented
		
//		result.setMoment(new Date());
		result.setDeleted(false);
		result.setCommentedEntity(commentedEntity);
//		setEntityByIdAndComment(entityId, result);
		
		Assert.notNull(result.getCommentedEntity(), "Cannot create a Comment without a Entity asociated.");
		
		return result;
	}
	
	/**
	 * Guarda un comment creado
	 */
	
	public void save(Comment comment){
		Assert.notNull(comment);
		Assert.isTrue((comment.getGym() != null) ^ (comment.getService() != null), "You can only comment on a Gym OR a Service.");
		
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

//	public Collection<Comment> findAllByGym(Gym gym){
//		Assert.notNull(gym);
//		Assert.isTrue(gym.getId() != 0);
//		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can see this stats");
//		
//		Collection<Comment> result;
//		
//		result = commentRepository.findAllByGym(gym.getId());
//		
//		return result;
//		
//	}
//	
//	/**
//	 * Lista todos los comentarios de un Service
//	 */
//	
//	public Collection<Comment> findAllByService(ServiceEntity service){
//		Assert.notNull(service);
//		Assert.isTrue(service.getId() != 0);
//		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can see this stats");
//		
//		Collection<Comment> result;
//		
//		result = commentRepository.findAllByService(service.getId());
//		
//		return result;
//		
//	}
	
	/**
	 * Lista todos los comentarios no eliminados
	 */
	
//	public Collection<Comment> findAllNotDeleted(){
//		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can see this stats");
//		
//		Collection<Comment> result;
//		
//		result = commentRepository.findAllNotDeleted();
//		
//		return result;
//		
//	}
	
	public Collection<Comment> findAllByEntityId(int entityId){
		Collection<Comment> result;
		
		result = commentRepository.findAllByEntityId(entityId);
		
		return result;
	}
	
	// Here are the methods that have to modify in order to implement a new Entity that need to have Comments.
	
	public String getEntityNameById(int entityId) {
		String result;
		Gym gym;
		ServiceEntity service;
		
		result = null;
		
		if(gymService.findOne(entityId) != null){
			gym = gymService.findOne(entityId);
			result = gym.getName();
		}else if(serviceService.findOne(entityId) != null){
			service = serviceService.findOne(entityId);
			result = service.getName();
		}
		
		Assert.notNull(result);
		
		return result;
	}
	
//	public Collection<Comment> getCommentsByEntityId(int entityId) {
//		Collection<Comment> result;
//		Gym gym;
//		ServiceEntity service;
//		
//		result = null;
//		
//		if(gymService.findOne(entityId) != null){
//			gym = gymService.findOne(entityId);
//			result = gym.getComments();
//		}else if(serviceService.findOne(entityId) != null){
//			service = serviceService.findOne(entityId);
//			result = service.getComments();
//		}
//		
//		Assert.notNull(result);
//		
//		return result;
//	}
	
//	public void setEntityByIdAndComment(int entityId, Comment comment) {
//		Gym gym;
//		ServiceEntity service;
//		
//		if(gymService.findOne(entityId) != null){
//			gym = gymService.findOne(entityId);
//			comment.setGym(gym);
//		}else if(serviceService.findOne(entityId) != null){
//			service = serviceService.findOne(entityId);
//			comment.setService(service);
//		}
//	}
	
	public Integer getEntityIdByComment(Comment comment) {
		Integer result;
		
		result = null;
		
		if(comment.getGym() != null){
			result = comment.getGym().getId();
		}else if(comment.getService() != null){
			result = comment.getService().getId();
		}
		
		return result;
	}
}
