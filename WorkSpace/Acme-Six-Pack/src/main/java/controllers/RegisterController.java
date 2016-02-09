package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Consumer;

import services.ConsumerService;

@Controller
@RequestMapping(value = "/consumer")
public class RegisterController extends AbstractController{

	//Services ----------------------------------------------------------
	
	@Autowired
	private ConsumerService consumerService;
	
	//Constructors ----------------------------------------------------------
	
	public RegisterController(){
		super();
	}

	//Listing ----------------------------------------------------------

	//Creation ----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Consumer consu;
		
		consu = consumerService.create();
		result = createEditModelAndView(consu);
		
		return result;
	}
	
	//Edition ----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Consumer consu, BindingResult binding){
		ModelAndView result;
		boolean bindingError;
		
		if(binding.hasFieldErrors("folders")){
			bindingError = binding.getErrorCount() > 1;
		}else{
			bindingError = binding.getErrorCount() > 0;
		}
		
		if(bindingError){
			result = createEditModelAndView(consu);
		} else {
			try {
				consumerService.save(consu);
				result = new ModelAndView("redirect:../security/login.do");
				result.addObject("messageStatus", "consumer.commit.ok");
								
			} catch (Throwable oops){
				result = createEditModelAndView(consu, "consumer.commit.error");
			}
		}
		
		return result;
	}
	//Ancillary Methods ----------------------------------------------------------

	protected ModelAndView createEditModelAndView(Consumer consumer){
		ModelAndView result;
		
		result = createEditModelAndView(consumer, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Consumer consumer, String message){
		ModelAndView result;
		
		result = new ModelAndView("consumer/create");
		result.addObject("consumer", consumer);
		result.addObject("message", message);
		
		return result;
	}
}
