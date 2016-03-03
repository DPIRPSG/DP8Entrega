package controllers.customer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.FeePaymentService;
import services.form.FeePaymentFormService;
import controllers.AbstractController;
import domain.FeePayment;
import domain.form.FeePaymentForm;

@Controller
@RequestMapping(value = "/feePayment/customer")
public class FeePaymentCustomerController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private FeePaymentService feePaymentService;
	
	@Autowired
	private FeePaymentFormService feePaymentFormService;

	// Constructors ----------------------------------------------------------

	public FeePaymentCustomerController() {
		super();
	}

	// Listing ----------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam (required=false) Integer gymId) {
		ModelAndView result;
		Collection<FeePayment> feePayments;
		
		feePayments = feePaymentService.findAllByCustomer();
		
		if(gymId != null) {
			feePayments = feePaymentService.findAllByCustomerAndGym(gymId);
		}
		
		result = new ModelAndView("feePayment/list");
		result.addObject("feePayments", feePayments);
		result.addObject("requestURI", "feePayment/customer/list.do");
		
		return result;
	}
	
	// Creation ----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int gymId) {
		ModelAndView result;
		FeePaymentForm feePaymentForm;

		feePaymentForm = feePaymentFormService.create(gymId);
		result = createEditModelAndView(feePaymentForm);

		return result;
	}

	// Edition ----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid FeePaymentForm feePaymentForm, BindingResult binding) {
		ModelAndView result;

		FeePayment fee;
	
		if (binding.hasErrors()) {
			result = createEditModelAndView(feePaymentForm);
		} else {
			try {
				fee = feePaymentFormService.reconstruct(feePaymentForm);
				feePaymentService.save(fee);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(feePaymentForm, "feePayment.commit.error");
			}
		}

		return result;
	}

	// Ancillary Methods
	// ----------------------------------------------------------

	protected ModelAndView createEditModelAndView(FeePaymentForm feePaymentForm) {
		ModelAndView result;

		result = createEditModelAndView(feePaymentForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(FeePaymentForm feePaymentForm, String message) {
		ModelAndView result;
		
		result = new ModelAndView("feePaymentForm/create");
		result.addObject("feePaymentForm", feePaymentForm);
		result.addObject("message", message);

		return result;
	}
}
