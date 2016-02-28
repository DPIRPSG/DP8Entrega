package controllers.customer;

import java.util.ArrayList;
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

import services.BookingService;
import services.GymService;

import controllers.AbstractController;
import domain.Booking;
import domain.Gym;

@Controller
@RequestMapping(value = "/booking/customer")
public class BookingCustomerController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private GymService gymService;

	// Constructors ----------------------------------------------------------

	public BookingCustomerController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Booking> bookings;

		bookings = bookingService.findAllByCustomer();

		result = new ModelAndView("booking/list");
		result.addObject("requestURI", "booking/customer/list.do?");
		result.addObject("bookings", bookings);

		return result;
	}

	// Creation ----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam (required=false) Integer gymId, @RequestParam int serviceId) {
		ModelAndView result;
		Booking booking;

		if(gymId != null) {
			booking = bookingService.createWithGym(gymId, serviceId);
		} else {
			booking = bookingService.createWithoutGym(serviceId);
		}
		result = createEditModelAndView(booking);

		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Booking booking, BindingResult binding){
		
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(booking);
		} else {
			try {
				bookingService.save(booking);
				result = new ModelAndView("redirect:list.do?");
			} catch (Throwable oops) {
				result = createEditModelAndView(booking, "booking.commit.error");
			}
		}

		return result;
	}

	// Edition ----------------------------------------------------------

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam int bookingId) {
		
		ModelAndView result;
		Booking booking;
		
		booking = bookingService.findOne(bookingId);
		Assert.notNull(booking);		

		try {
			bookingService.cancel(booking);
			result = new ModelAndView("redirect:list.do");
			result.addObject("messageStatus", "booking.cancel.ok");
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
			result = createEditModelAndView(booking, "booking.cancel.error");
		}

		return result;
	}
	

	// Ancillary Methods
	// ----------------------------------------------------------

	protected ModelAndView createEditModelAndView(Booking booking) {
		ModelAndView result;

		result = createEditModelAndView(booking, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Booking booking, String message) {
		
		ModelAndView result;
		Collection<Gym> gymsWithPay;
		Collection<Gym> gymsOfService;
		Collection<Gym> gyms;
		Boolean muestraGyms;
		
		muestraGyms = false;
		
		gyms = new ArrayList<Gym>();
		
		gymsWithPay = gymService.findAllWithFeePaymentActive();
		gymsOfService = booking.getService().getGyms();
		
		for(Gym gym : gymsWithPay) {
			if(gymsOfService.contains(gym)) {
				gyms.add(gym);
			}
		}
		
		if(booking.getGym() == null){
			muestraGyms = true;
		}
		
		result = new ModelAndView("booking/create");
		result.addObject("booking", booking);
		result.addObject("message", message);
		result.addObject("gyms", gyms);
		result.addObject("muestraGyms", muestraGyms);

		return result;
	}
}
