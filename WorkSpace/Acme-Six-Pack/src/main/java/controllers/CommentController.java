package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import domain.Comment;

@Controller
@RequestMapping("/comment")
public class CommentController {

	// Services ----------------------------------------------------------
	
	@Autowired
	private CommentService commentService;
	
	
	// Constructors --------------------------------------------------------
	
	public CommentController() {
		super();
	}
	
	
	// Listing ------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
//		public ModelAndView list(@RequestParam(required = false) Integer gymId, @RequestParam(required = false) Integer serviceId) {
	public ModelAndView list(@RequestParam Integer entityId) {
		ModelAndView result;
		Collection<Comment> comments;
//			Gym gym;
//			ServiceEntity service;
		String entityName;
		
//			if(gymId != null){
//				gym = gymService.findOne(gymId);
//				comments = commentService.findAllByGym(gym);
//				entityName = gym.getName();
//			}else if(serviceId != null){
//				service = serviceService.findOne(serviceId);
//				comments = commentService.findAllByService(service);
//				entityName = service.getName();
//			}else{
//				comments = null;
//				entityName = "You must select an entity to see their comments";
//			}
		
		comments = commentService.findAllByEntityId(entityId);
		entityName = commentService.getEntityNameById(entityId);
		
		result = new ModelAndView("comment/list");
		result.addObject("comments", comments);
		result.addObject("entityName", entityName);
		result.addObject("entityId", entityId);
		result.addObject("requestURI", "comment/list.do");
		
		return result;
	}
	
}
