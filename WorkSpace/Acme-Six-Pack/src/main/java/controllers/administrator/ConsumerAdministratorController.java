package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;

import controllers.AbstractController;
import domain.Customer;

@Controller
@RequestMapping(value = "/consumer/administrator")
public class ConsumerAdministratorController extends AbstractController{

	//Services ----------------------------------------------------------

	@Autowired
	private CustomerService consumerService;
	
	//Constructors ----------------------------------------------------------
	
	public ConsumerAdministratorController(){
		super();
	}

	//Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
        ModelAndView result;
        Collection<Customer> consumers;
        
        consumers = consumerService.findAll();
        
        result = new ModelAndView("consumer/list");
        result.addObject("consumers", consumers);
        result.addObject("requestURI", "consumer/administrator/list.do");
        
        return result;
	}
	
	
	//Creation ----------------------------------------------------------

	
	//Edition ----------------------------------------------------------

	
	//Ancillary Methods ----------------------------------------------------------

}
