package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ClerkService;
import services.ConsumerService;
import services.ItemService;
import services.OrderService;
import controllers.AbstractController;
import domain.Clerk;
import domain.Consumer;
import domain.Item;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {
	
	// Services ----------------------------------------------------------
	
	@Autowired
	private ConsumerService consumerService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ClerkService clerkService;
	
	@Autowired
	private OrderService orderService;
	
	
	// Constructors --------------------------------------------------------
	
	public DashboardAdministratorController() {
		super();
	}
	
	
	// Listing ------------------------------------------------------------
	
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {
			ModelAndView result;
			Collection<Consumer> consumerMoreOrders;
			Collection<Consumer> consumerSpentMoreMoney;
			Collection<Item> bestSellingItem;
			Collection<Item> worstSellingItem;
			Collection<Clerk> clerkMoreOrders;
			Collection<Clerk> clerkLessOrders;
			Collection<Consumer> consumerCancelledMoreOrders;
			Collection<Consumer> consumerCancelledLessOrders;
			double ratioCancelledCurrentMonth;
			Collection<Item> itemMoreComment;
			
			consumerMoreOrders = consumerService.findConsumerMoreOrders();
			consumerSpentMoreMoney = consumerService.findConsumerSpentMoreMoney();
			bestSellingItem = itemService.findItemBestSelling();
			worstSellingItem = itemService.findItemWorstSelling();
			clerkMoreOrders = clerkService.findClerkServedMoreOrders();
			clerkLessOrders = clerkService.findClerkServedLessOrders();
			consumerCancelledMoreOrders = consumerService.findConsumerMoreOrdersCancelled();
			consumerCancelledLessOrders = consumerService.findConsumerLessOrdersCancelled();
			ratioCancelledCurrentMonth = orderService.rateOrderCancelled();
			itemMoreComment = itemService.findItemMoreComments();
			
			result = new ModelAndView("administrator/list");
			result.addObject("consumerMoreOrders", consumerMoreOrders);
			result.addObject("consumerSpentMoreMoney", consumerSpentMoreMoney);
			result.addObject("bestSellingItem", bestSellingItem);
			result.addObject("worstSellingItem", worstSellingItem);
			result.addObject("clerkMoreOrders", clerkMoreOrders);
			result.addObject("clerkLessOrders", clerkLessOrders);
			result.addObject("consumerCancelledMoreOrders", consumerCancelledMoreOrders);
			result.addObject("consumerCancelledLessOrders", consumerCancelledLessOrders);
			result.addObject("ratioCancelledCurrentMonth", ratioCancelledCurrentMonth);
			result.addObject("itemMoreComment", itemMoreComment);
			result.addObject("requestURI", "administrator/list.do");
			
			return result;
		}
	
}
