package controllers.clerk;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.WareHouseService;

import controllers.AbstractController;
import domain.WareHouse;

@Controller
@RequestMapping(value = "/warehouse/clerk")
public class WarehouseClerkController extends AbstractController {

	// Services ----------------------------------------------------------
	@Autowired
	private WareHouseService warehouseService;

	// Constructors ----------------------------------------------------------

	public WarehouseClerkController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<WareHouse> warehouses;

		warehouses = warehouseService.findAll();
		result = new ModelAndView("warehouse/list");
		result.addObject("requestURI", "warehouse/clerk/list.do");
		result.addObject("warehouses", warehouses);

		return result;
	}

	// Creation ----------------------------------------------------------

	// Edition ----------------------------------------------------------

	// Ancillary Methods ----------------------------------------------------------

}
