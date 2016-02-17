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
	
	/*@Autowired
	private CommentService commentService;
	
	@Autowired
	private GymService gymService;
	
	@Autowired
	private ServiceService serviceService;
	
	@Autowired
	private ActorService actorService;
	
	
	// Constructors --------------------------------------------------------
	
	public CommentActorController() {
		super();
	}
	
	
	// Listing ------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public ModelAndView list(@RequestParam(required = false) Integer gymId, @RequestParam(required = false) Integer serviceId) {
	public ModelAndView list(@RequestParam Integer entityId) {
		ModelAndView result;
		Collection<Comment> comments;
		
		if(gymId != null){
			gym = gymService.findOne(gymId);
		}else if(serviceId != null){
			service = serviceService.findOne(serviceId);
		}
		
		comments = commentService.findAllByItem(item);
		
		result = new ModelAndView("comment/list");
		result.addObject("comments", comments);
		result.addObject("item", item);
		result.addObject("requestURI", "comment/list.do");
		
		return result;
	}
	
	
	// Creating --------------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int itemId) {
		ModelAndView result;
		Comment comment;
		Item item;
		
		item = itemService.findOne(itemId);
		comment = commentService.createByItem(item);		
		result = createEditModelAndView(comment, item);
		
		return result;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Comment comment, BindingResult binding) {
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(comment, comment.getItem());
		} else {
			try {
				commentService.save(comment);
				result = new ModelAndView("redirect:list.do?itemId=" + comment.getItem().getId());
			} catch (Throwable oops) {
				result = createEditModelAndView(comment, comment.getItem(), "comment.commit.error");
			}
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
		if(actorService.checkAuthority("ADMIN") || actorService.checkAuthority("CONSUMER") || actorService.checkAuthority("CLERK")){
			comment.setUserName(actorService.findByPrincipal().getName());
		}else{
			comment.setUserName("Anonymous");
		}
		
		result = new ModelAndView("comment/create");
		result.addObject("comment", comment);
		result.addObject("item", item);
		result.addObject("message", message);
		
		return result;
	}*/
}
