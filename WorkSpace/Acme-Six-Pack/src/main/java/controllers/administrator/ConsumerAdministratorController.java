package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ConsumerService;

import controllers.AbstractController;
import domain.Consumer;

@Controller
@RequestMapping(value = "/consumer/administrator")
public class ConsumerAdministratorController extends AbstractController{

	//Services ----------------------------------------------------------

	@Autowired
	private ConsumerService consumerService;
	
	//Constructors ----------------------------------------------------------
	
	public ConsumerAdministratorController(){
		super();
	}

	//Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
        ModelAndView result;
        Collection<Consumer> consumers;
        
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
