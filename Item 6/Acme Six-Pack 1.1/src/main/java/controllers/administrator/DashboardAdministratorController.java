package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CustomerService;
import services.GymService;
import services.ServiceService;
import controllers.AbstractController;
import domain.Actor;
import domain.Customer;
import domain.Gym;
import domain.ServiceEntity;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {
	
	// Services ----------------------------------------------------------

	@Autowired
	private GymService gymService;
	
	@Autowired
	private ServiceService serviceService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ActorService actorService;
	
	// Constructors --------------------------------------------------------
	
	public DashboardAdministratorController() {
		super();
	}
	
	
	// Listing ------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		
		Collection<Gym> mostPopularGyms;
		Collection<Gym> leastPopularGyms;
		Collection<ServiceEntity> mostPopularService;
		Collection<ServiceEntity> leastPopularService;
		Collection<Customer> paidMoreFees;
		Collection<Customer> paidLessFees;
		
		Collection<Actor> sendMoreSpam;
		Double averageNumberOfMessages;
		
		Collection<Gym> moreCommentedGyms;
		Collection<ServiceEntity> moreCommentedServices;
		Double averageNumberOfComments;
		Double standardDeviationNumberOfComments;
		Double averageNumberOfCommentsPerGym;
		Double averageNumberOfCommentsPerService;
		Collection<Customer> removedMoreComments;
		
		
		
		mostPopularGyms = gymService.findMostPopularGyms();
		leastPopularGyms = gymService.findLeastPopularGym();
		mostPopularService = serviceService.findMostPopularService();
		leastPopularService = serviceService.findLeastPopularService();
		paidMoreFees = customerService.findCustomerWhoHasPaidMoreFees();
		paidLessFees = customerService.findCustomerWhoHasPaidLessFees();
		
		sendMoreSpam = actorService.findActorWhoSendMoreSpam();
		averageNumberOfMessages = actorService.findAverageNumberOfMessagesInActorMessageBox();
		
		moreCommentedGyms = gymService.findMostCommented();
		moreCommentedServices = serviceService.findMostCommented();
		averageNumberOfComments = actorService.findAverageNumberOfCommentWrittenByAnActor();
		standardDeviationNumberOfComments = actorService.findStandardDeviationNumberOfCommentWrittenByAnActor();
		averageNumberOfCommentsPerGym = gymService.findAverageNumberOfCommentsPerGym();
		averageNumberOfCommentsPerService = serviceService.findAverageNumberOfCommentsPerService();
		removedMoreComments = customerService.findCustomerWhoHaveBeenRemovedMoreComments();
		
		
		
		result = new ModelAndView("administrator/list");
		result.addObject("mostPopularGyms", mostPopularGyms);
		result.addObject("leastPopularGyms", leastPopularGyms);
		result.addObject("mostPopularService", mostPopularService);
		result.addObject("leastPopularService", leastPopularService);
		result.addObject("paidMoreFees", paidMoreFees);
		result.addObject("paidLessFees", paidLessFees);
		
		result.addObject("sendMoreSpam", sendMoreSpam);
		result.addObject("averageNumberOfMessages", averageNumberOfMessages);
		
		result.addObject("moreCommentedGyms", moreCommentedGyms);
		result.addObject("moreCommentedServices", moreCommentedServices);
		result.addObject("averageNumberOfComments", averageNumberOfComments);
		result.addObject("standardDeviationNumberOfComments", standardDeviationNumberOfComments);
		result.addObject("averageNumberOfCommentsPerGym", averageNumberOfCommentsPerGym);
		result.addObject("averageNumberOfCommentsPerService", averageNumberOfCommentsPerService);
		result.addObject("removedMoreComments", removedMoreComments);
		
		
		
		result.addObject("requestURI", "administrator/list.do");
		
		return result;
	}	
}
