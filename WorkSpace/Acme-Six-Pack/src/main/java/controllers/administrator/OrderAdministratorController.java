package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ExchangeRateService;
import services.OrderService;

import controllers.AbstractController;
import domain.ExchangeRate;
import domain.Order;

@Controller
@RequestMapping(value = "/order/administrator")
public class OrderAdministratorController extends AbstractController{

	//Services ----------------------------------------------------------

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ExchangeRateService exchangeRateService;
	
	//Constructors ----------------------------------------------------------
	
	public OrderAdministratorController(){
		super();
	}

	//Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required=false) Integer exchangeRateId){
        ModelAndView result;
        Collection<Order> orders;
        ExchangeRate exchangeRate;
        Collection<ExchangeRate> moneyList;
        
        exchangeRate = null;
		moneyList = exchangeRateService.findAll();
		
		if(exchangeRateId != null) {
			exchangeRate = exchangeRateService.findOne(exchangeRateId);
		} else {
			exchangeRate = exchangeRateService.findOneByName("Euros");
		}
        
        orders = orderService.findAll();
        
        result = new ModelAndView("order/list");
        result.addObject("orders", orders);
        result.addObject("requestURI", "order/administrator/list.do");
        result.addObject("moneyList", moneyList);
		result.addObject("exchangeRate", exchangeRate);
        
        return result;
	}
	
	
	//Creation ----------------------------------------------------------

	
	//Edition ----------------------------------------------------------

	
	//Ancillary Methods ----------------------------------------------------------

}
