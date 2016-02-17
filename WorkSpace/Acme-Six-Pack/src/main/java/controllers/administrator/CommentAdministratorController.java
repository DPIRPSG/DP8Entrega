package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import controllers.AbstractController;
import domain.Comment;

@Controller
@RequestMapping("/comment/administrator")
public class CommentAdministratorController extends AbstractController {
	
	// Services ----------------------------------------------------------
	
	@Autowired
	private CommentService commentService;
	
	
	// Constructors --------------------------------------------------------
	
	public CommentAdministratorController() {
		super();
	}
	
	// Deleting --------------------------------------------------------------
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam Integer commentId) {
		ModelAndView result;
		Comment comment;
		int entityId;
		String entityName;
		
		comment = commentService.findOne(commentId);
		entityId = commentService.getEntityIdByComment(comment);
		entityName = commentService.getEntityNameById(entityId);
		
		result = createEditModelAndView(comment, entityName);
		
		return result;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST, params="delete")
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
				commentService.delete(comment);
				result = new ModelAndView("redirect:list.do?entityId=" + entityId);
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
		
		result = new ModelAndView("comment/delete");
		result.addObject("comment", comment);
		result.addObject("entityName", entityName);
		result.addObject("message", message);
		
		return result;
	}
	
}
