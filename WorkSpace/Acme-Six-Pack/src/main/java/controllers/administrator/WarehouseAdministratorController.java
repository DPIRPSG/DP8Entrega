package controllers.administrator;

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

import services.WareHouseService;

import controllers.AbstractController;
import domain.WareHouse;

@Controller
@RequestMapping(value = "/warehouse/administrator")
public class WarehouseAdministratorController extends AbstractController {

	// Services ----------------------------------------------------------
	@Autowired
	private WareHouseService wareHouseService;

	// Constructors ----------------------------------------------------------

	public WarehouseAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<WareHouse> warehouses;

		warehouses = wareHouseService.findAll();
		result = new ModelAndView("warehouse/list");
		result.addObject("requestURI", "warehouse/administrator/list.do");
		result.addObject("warehouses", warehouses);

		return result;
	}

	// Creation ----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		WareHouse warehouse;
		
		warehouse = wareHouseService.create();
		
		result = createEditModelAndView(warehouse);
		
		return result;
	}

	// Edition ----------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int warehouseId) {
	
		ModelAndView result;
		WareHouse wareHouse;
		
		wareHouse = wareHouseService.findOne(warehouseId);
		Assert.notNull(wareHouse);
		result = createEditModelAndView(wareHouse);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid WareHouse wareHouse, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(wareHouse);
		} else {
			try {
				wareHouseService.save(wareHouse);		
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(wareHouse, "warehouse.commit.error");				
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid WareHouse wareHouse, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(wareHouse);
		} else {
			try {
				wareHouseService.delete(wareHouse);		
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				if(oops.getMessage().equals("The warehouse isn't empty")){
					result = createEditModelAndView(wareHouse, "warehouse.commit.error.notempty");
				}else{
					result = createEditModelAndView(wareHouse, "warehouse.commit.error");
				}
								
			}
		}

		return result;
	}

	// Ancillary Methods ----------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(WareHouse warehouse) {
		ModelAndView result;
		
		result = createEditModelAndView(warehouse, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(WareHouse warehouse, String message){
		ModelAndView result;
		
		result = new ModelAndView("warehouse/edit");
		result.addObject("warehouse", warehouse);
		result.addObject("message", message);
		
		return result;
	}	

}
