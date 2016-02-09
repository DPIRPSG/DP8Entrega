package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ClerkService;

import controllers.AbstractController;
import domain.Clerk;

@Controller
@RequestMapping(value = "/clerk/administrator")
public class ClerkAdministratorController extends AbstractController{

	//Services ----------------------------------------------------------

	@Autowired
	private ClerkService clerkService;
	
	//Constructors ----------------------------------------------------------
	
	public ClerkAdministratorController(){
		super();
	}

	//Listing ----------------------------------------------------------

	
	//Creation ----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Clerk clerk;
		
		clerk = clerkService.create();
		result = createEditModelAndView(clerk);
		
		return result;
	}
	
	//Edition ----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Clerk clerk, BindingResult binding){
		ModelAndView result;
		boolean bindingError;
		
		if(binding.hasFieldErrors("folders")){
			bindingError = binding.getErrorCount() > 1;
		}else{
			bindingError = binding.getErrorCount() > 0;
		}
		
		if(bindingError){
			result = createEditModelAndView(clerk);
		} else {
			try {
				clerkService.save(clerk);
				result = new ModelAndView("redirect:../..");
				result.addObject("messageStatus", "clerk.commit.ok");
			} catch (Throwable oops){
				System.out.println("Error oops: "+ oops);
				result = createEditModelAndView(clerk, "clerk.commit.error");
			}
		}
		
		return result;
	}
	
	//Ancillary Methods ----------------------------------------------------------

	protected ModelAndView createEditModelAndView(Clerk clerk) {
		ModelAndView result;
		
		result = createEditModelAndView(clerk, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Clerk clerk, String message){
		ModelAndView result;
		
		result = new ModelAndView("clerk/create");
		result.addObject("clerk", clerk);
		result.addObject("message", message);
		
		return result;
	}
}
