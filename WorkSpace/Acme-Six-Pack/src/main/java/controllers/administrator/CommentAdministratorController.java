package controllers.administrator;

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
@RequestMapping("/comment/administrator")
public class CommentAdministratorController extends AbstractController {
	
	// Services ----------------------------------------------------------
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CommentedEntityService commentedEntityService;
	
	
	// Constructors --------------------------------------------------------
	
	public CommentAdministratorController() {
		super();
	}
	
	// Deleting --------------------------------------------------------------
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam Integer commentId) {
		ModelAndView result;
		Comment comment;
		int commentedEntityId;
		
		comment = commentService.findOne(commentId);
		commentedEntityId = comment.getCommentedEntity().getId();
		
		commentService.delete(comment);
		result = new ModelAndView("redirect:../list.do?commentedEntityId=" + commentedEntityId);
		
		return result;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST, params="delete")
	public ModelAndView save(@Valid Comment comment, BindingResult binding) {
		ModelAndView result;
		CommentedEntity commentedEntity;
		int commentedEntityId;
		
		comment = commentService.findOne(comment.getId());
		commentedEntity = comment.getCommentedEntity();
		commentedEntityId = commentedEntity.getId();
		
		if (binding.hasErrors()) {
			System.out.println("Errores: " + binding.toString());
			result = createEditModelAndView(comment, commentedEntity);
		} else {
			try {
				commentService.delete(comment);
				result = new ModelAndView("redirect:../list.do?commentedEntityId=" + commentedEntityId);
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
		
		result = new ModelAndView("comment/delete");
		result.addObject("comment", comment);
		result.addObject("commentedEntity", commentedEntity);
		result.addObject("message", message);
		
		return result;
	}
	
}
