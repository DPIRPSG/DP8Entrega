package controllers.consumer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import domain.Consumer;
import domain.ShoppingCart;

import services.ConsumerService;
import services.ShoppingCartService;

@Controller
@RequestMapping(value = "/shopping-cart/consumer")
public class ShoppingCartConsumerController extends AbstractController{
	
	// Services ----------------------------------------------------------
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private ConsumerService consumerService;
	
	// Constructors ----------------------------------------------------------

	public ShoppingCartConsumerController() {
		super();
	}
	
	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Consumer consumer;
		ShoppingCart shoppingCart;
		
		consumer = consumerService.findByPrincipal();
		shoppingCart = shoppingCartService.findByConsumer(consumer);
		
		result = new ModelAndView("shopping-cart/list");
		result.addObject("requestURI", "shopping-cart/consumer/list.do");
		result.addObject("shoppingCarts", shoppingCart);

		return result;
	}
	
	// Edition ----------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int shoppingCartId){
		ModelAndView result;
		ShoppingCart shoppingCart;
		
		shoppingCart = shoppingCartService.finOneByShoppingCartId(shoppingCartId);
		Assert.notNull(shoppingCart);
		result = createEditModelAndView(shoppingCart);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ShoppingCart shoppingCart, BindingResult binding){
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(shoppingCart);
		} else {
			try {
				Assert.isTrue(shoppingCart.getConsumer().equals(consumerService.findByPrincipal()), "Only the owner of the shopping cart can save it");
				shoppingCartService.save(shoppingCart);
				result = new ModelAndView("redirect:/shopping-cart/consumer/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(shoppingCart, "shoppingCart.commit.error");				
			}
		}
		
		return result;
	}
	
	// Ancilary methods -------------------------------------------------
	protected ModelAndView createEditModelAndView(ShoppingCart shoppingCart) {
		ModelAndView result;
		
		result = createEditModelAndView(shoppingCart, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(ShoppingCart shoppingCart, String message){
		ModelAndView result;
		
		result = new ModelAndView("shopping-cart/edit");
		result.addObject("shopping-cart", shoppingCart);
		result.addObject("message", message);
		
		return result;
	}

}
