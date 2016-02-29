package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.CommentedEntityService;
import domain.Comment;
import domain.CommentedEntity;

@Controller
@RequestMapping("/comment")
public class CommentController {

	// Services ----------------------------------------------------------
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CommentedEntityService commentedEntityService;
	
	
	// Constructors --------------------------------------------------------
	
	public CommentController() {
		super();
	}
	
	
	// Listing ------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam Integer commentedEntityId) {
		ModelAndView result;
		Collection<Comment> comments;
		CommentedEntity commentedEntity;
		
		comments = commentService.findAllByCommentedEntityId(commentedEntityId);
		commentedEntity = commentedEntityService.findOne(commentedEntityId);
		
		result = new ModelAndView("comment/list");
		result.addObject("comments", comments);
		result.addObject("commentedEntity", commentedEntity);
		result.addObject("requestURI", "comment/list.do");
		
		return result;
	}
	
}
