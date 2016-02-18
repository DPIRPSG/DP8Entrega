package controllers.actor;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CommentService;
import services.GymService;
import services.ServiceService;

import com.lowagie.text.pdf.AcroFields.Item;

import controllers.AbstractController;
import domain.Comment;
import domain.DomainEntity;
import domain.Gym;
import domain.ServiceEntity;

@Controller
@RequestMapping("/comment/actor")
public class CommentActorController extends AbstractController {
	
	// Services ----------------------------------------------------------
	
	@Autowired
	private CommentService commentService;
	
//	@Autowired
//	private GymService gymService;
	
//	@Autowired
//	private ServiceService serviceService;
	
	
	// Constructors --------------------------------------------------------
	
	public CommentActorController() {
		super();
	}
	
	
	// Listing ------------------------------------------------------------
	
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
////	public ModelAndView list(@RequestParam(required = false) Integer gymId, @RequestParam(required = false) Integer serviceId) {
//	public ModelAndView list(@RequestParam Integer entityId) {
//		ModelAndView result;
//		Collection<Comment> comments;
////		Gym gym;
////		ServiceEntity service;
//		String entityName;
//		
////		if(gymId != null){
////			gym = gymService.findOne(gymId);
////			comments = commentService.findAllByGym(gym);
////			entityName = gym.getName();
////		}else if(serviceId != null){
////			service = serviceService.findOne(serviceId);
////			comments = commentService.findAllByService(service);
////			entityName = service.getName();
////		}else{
////			comments = null;
////			entityName = "You must select an entity to see their comments";
////		}
//		
//		comments = commentService.findAllByEntityId(entityId);
//		entityName = commentService.getEntityNameById(entityId);
//		
//		result = new ModelAndView("comment/list");
//		result.addObject("comments", comments);
//		result.addObject("entityName", entityName);
//		result.addObject("requestURI", "comment/list.do");
//		
//		return result;
//	}
	
	
	// Creating --------------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam Integer entityId) {
		ModelAndView result;
		Comment comment;
		String entityName;
		
		comment = commentService.create(entityId);
		
		entityName = commentService.getEntityNameById(entityId);
		
		result = createEditModelAndView(comment, entityName);
		
		return result;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Comment comment, BindingResult binding) {
		ModelAndView result;
		int entityId;
		String entityName;
		
		entityId = commentService.getEntityIdByComment(comment);
		entityName = commentService.getEntityNameById(entityId);
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(comment, entityName);
		} else {
			try {
				commentService.save(comment);
				result = new ModelAndView("redirect:../list.do?entityId=" + entityId);
			} catch (Throwable oops) {
				result = createEditModelAndView(comment, entityName, "comment.commit.error");
			}
		}
		
		return result;
	}
	
	
	// Ancillary methods ---------------------------------------------------
	
	protected ModelAndView createEditModelAndView(Comment comment, String entityName) {
		ModelAndView result;
		
		result = createEditModelAndView(comment, entityName, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Comment comment, String entityName, String message) {
		ModelAndView result;
		
		result = new ModelAndView("comment/create");
		result.addObject("comment", comment);
		result.addObject("entityName", entityName);
		result.addObject("message", message);
		
		return result;
	}
}
