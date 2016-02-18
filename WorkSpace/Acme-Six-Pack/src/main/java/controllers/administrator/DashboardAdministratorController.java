package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.GymService;
import services.ServiceService;
import controllers.AbstractController;
import domain.Gym;
import domain.ServiceEntity;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {
	
	// Services ----------------------------------------------------------
	
//	@Autowired
//	private CustomerService consumerService;
	
//	@Autowired
//	private CommentService commentService;
	
	@Autowired
	private GymService gymService;
	
	@Autowired
	private ServiceService serviceService;
	
	// Constructors --------------------------------------------------------
	
	public DashboardAdministratorController() {
		super();
	}
	
	
	// Listing ------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
//		Collection<Gym> mostPopularGyms;
		Collection<ServiceEntity> moreCommentedServices;
		
//		mostPopularGyms = gymService.findMostPopular();
		moreCommentedServices = serviceService.findMostCommented();
		
		result = new ModelAndView("administrator/list");
//		result.addObject("mostPopularGyms", mostPopularGyms);
		result.addObject("moreCommentedServices", moreCommentedServices);
		result.addObject("requestURI", "administrator/list.do");
		
		return result;
	}
	
}
