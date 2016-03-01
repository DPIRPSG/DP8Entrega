package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		int commentedEntityId;
		
		comment = commentService.findOne(commentId);
		commentedEntityId = comment.getCommentedEntity().getId();
		try {
			commentService.delete(comment);
			result = new ModelAndView("redirect:../list.do?commentedEntityId=" + commentedEntityId);
			result.addObject("messageStatus", "comment.delete.ok");
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:../list.do?commentedEntityId=" + commentedEntityId);
			result.addObject("messageStatus", "comment.commit.error");
		}
		
		return result;
	}
		
	// Ancillary methods ---------------------------------------------------
	
}
