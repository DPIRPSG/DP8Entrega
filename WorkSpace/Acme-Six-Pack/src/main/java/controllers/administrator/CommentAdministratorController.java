package controllers.administrator;

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
import domain.Item;

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
	public ModelAndView edit(@RequestParam int commentId) {
		ModelAndView result;
		Comment comment;
		
		comment = commentService.findOne(commentId);
		Assert.notNull(comment);
		result = createEditModelAndView(comment, comment.getItem());
		
		return result;
	}
	
	@RequestMapping(
			value = "/delete", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(
			Comment comment, BindingResult binding) {
		ModelAndView result;
		int itemId;
		
		try {
			itemId = comment.getItem().getId();
			commentService.delete(comment);
			result = new ModelAndView("redirect:/comment/list.do?itemId=" + itemId);
		} catch (Throwable oops) {
			result = createEditModelAndView(comment, comment.getItem(), "comment.commit.error");
		}
		return result;
	}
	
	// Ancillary methods ---------------------------------------------------
	
	protected ModelAndView createEditModelAndView(Comment comment, Item item) {
		ModelAndView result;
		
		result = createEditModelAndView(comment, item, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Comment comment, Item item, String message) {
		ModelAndView result;
		
		result = new ModelAndView("comment/delete");
		result.addObject("comment", comment);
		result.addObject("item", item);
		result.addObject("message", message);
		
		return result;
	}
	
}
