package controllers.actor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.CommentedEntityService;
import controllers.AbstractController;
import domain.Comment;
import domain.CommentedEntity;

@Controller
@RequestMapping("/comment/actor")
public class CommentActorController extends AbstractController {
	
	// Services ----------------------------------------------------------
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CommentedEntityService commentedEntityService;
	
//	@Autowired
//	private GymService gymService;
	
//	@Autowired
//	private ServiceService serviceService;
	
	
	// Constructors --------------------------------------------------------
	
	public CommentActorController() {
		super();
	}
	
	
	// Creating --------------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam Integer commentedEntityId) {
		ModelAndView result;
		Comment comment;
		CommentedEntity commentedEntity;
		
		comment = commentService.create(commentedEntityId);
		commentedEntity = commentedEntityService.findOne(commentedEntityId);
		
		result = createEditModelAndView(comment, commentedEntity);
		
		return result;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Comment comment, BindingResult binding) {
		ModelAndView result;
		CommentedEntity commentedEntity;
		int commentedEntityId;
		
		commentedEntityId = comment.getCommentedEntity().getId();
		commentedEntity = commentedEntityService.findOne(commentedEntityId);
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(comment, commentedEntity);
		} else {
			try {
				commentService.save(comment);
				result = new ModelAndView("redirect:../list.do?commentedEntityId=" + commentedEntity);
			} catch (Throwable oops) {
				result = createEditModelAndView(comment, commentedEntity, "comment.commit.error");
			}
		}
		
		return result;
	}
	
	
	// Ancillary methods ---------------------------------------------------
	
	protected ModelAndView createEditModelAndView(Comment comment, CommentedEntity commentedEntity) {
		ModelAndView result;
		
		result = createEditModelAndView(comment, commentedEntity, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Comment comment, CommentedEntity commentedEntity, String message) {
		ModelAndView result;
		
		result = new ModelAndView("comment/create");
		result.addObject("comment", comment);
		result.addObject("commentedEntity", commentedEntity);
		result.addObject("message", message);
		
		return result;
	}
}
