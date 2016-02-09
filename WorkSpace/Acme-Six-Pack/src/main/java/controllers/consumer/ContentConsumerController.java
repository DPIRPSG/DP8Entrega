package controllers.consumer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConsumerService;
import services.ContentService;
import services.ExchangeRateService;

import controllers.AbstractController;
import domain.Consumer;
import domain.Content;
import domain.Item;
import domain.ExchangeRate;

@Controller
@RequestMapping(value = "/content/consumer")
public class ContentConsumerController extends AbstractController{
	
	// Services ----------------------------------------------------------
	@Autowired
	private ContentService contentService;
	@Autowired
	private ConsumerService consumerService;
	
	@Autowired
	private ExchangeRateService exchangeRateService;
	
	// Constructors ----------------------------------------------------------

	public ContentConsumerController() {
		super();
	}
	
	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int shoppingCartId, @RequestParam(required=false) Integer exchangeRateId) {
		ModelAndView result;
		Collection<Content> contents;
		ExchangeRate exchangeRate;
        Collection<ExchangeRate> moneyList;
        
        exchangeRate = null;
		moneyList = exchangeRateService.findAll();
		
		if(exchangeRateId != null) {
			exchangeRate = exchangeRateService.findOne(exchangeRateId);
		} else {
			exchangeRate = exchangeRateService.findOneByName("Euros");
		}
		
		contents = contentService.findByShoppingCart(shoppingCartId);
		result = new ModelAndView("content/list");
		result.addObject("requestURI", "content/consumer/list.do");
		result.addObject("contents", contents);
		result.addObject("moneyList", moneyList);
		result.addObject("exchangeRate", exchangeRate);
		result.addObject("shoppingCartId", shoppingCartId);
		
		return result;
	}
	
	// Edition ----------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int contentId){
		ModelAndView result;
		Content content;
		
		content = contentService.findOneByContentId(contentId);
		Assert.notNull(content);
		result = createEditModelAndView(content);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Content content, BindingResult binding){
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(content);
		} else {
			try {
				contentService.save(content);
				result = new ModelAndView("redirect:list.do?shoppingCartId=" + content.getShoppingCart().getId());
			} catch (Throwable oops) {
				result = createEditModelAndView(content, "content.commit.error");				
			}
		}
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Content content, BindingResult binding){
		ModelAndView result;
		Consumer actualConsumer;
		
		actualConsumer = content.getShoppingCart().getConsumer();
		
		try{
			Assert.isTrue(actualConsumer.equals(consumerService.findByPrincipal()), "Only the owner of the shopping cart can delete its content");
			contentService.deleteComplete(content);
			result = new ModelAndView("redirect:list.do?shoppingCartId=" + content.getShoppingCart().getId());
		}catch(Throwable oops){
			result = createEditModelAndView(content, "content.commit.error");
		}
		
		return result;
	}
	
	// Ancilary methods -------------------------------------------------
	protected ModelAndView createEditModelAndView(Content content) {
		ModelAndView result;
		
		result = createEditModelAndView(content, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Content content, String message){
		ModelAndView result;
		Item item;
		
		item = content.getItem();
		
		result = new ModelAndView("content/edit");
		result.addObject("content", content);
		result.addObject("item", item);
		result.addObject("message", message);
		
		return result;
	}
	

}
