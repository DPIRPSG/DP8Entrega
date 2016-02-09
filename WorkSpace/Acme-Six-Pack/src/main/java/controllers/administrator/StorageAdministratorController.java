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

import services.ExchangeRateService;
import services.ItemService;
import services.StorageService;
import services.WareHouseService;
import controllers.AbstractController;
import domain.ExchangeRate;
import domain.Item;
import domain.Storage;
import domain.WareHouse;

@Controller
@RequestMapping(value = "/storage/administrator")
public class StorageAdministratorController extends AbstractController {

	// Services ----------------------------------------------------------
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private WareHouseService wareHouseService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ExchangeRateService exchangeRateService;

	// Constructors ----------------------------------------------------------

	public StorageAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int warehouseId, @RequestParam(required=false) Integer exchangeRateId) {
		ModelAndView result;
		Collection<Storage> storages;
		boolean byWarehouse;
		WareHouse warehouse;
		ExchangeRate exchangeRate;
		Collection<ExchangeRate> moneyList;
        
        exchangeRate = null;
		moneyList = exchangeRateService.findAll();
		
		if(exchangeRateId != null) {
			exchangeRate = exchangeRateService.findOne(exchangeRateId);
		} else {
			exchangeRate = exchangeRateService.findOneByName("Euros");
		}

		byWarehouse = true;
		storages = storageService.findAllByWarehouseId(warehouseId);
		warehouse = wareHouseService.findOne(warehouseId);
		
		result = new ModelAndView("storage/list");
		result.addObject("requestURI", "storage/administrator/list.do");
		result.addObject("byWarehouse", byWarehouse);
		result.addObject("storages", storages);
		result.addObject("warehouseId", warehouseId);
		result.addObject("warehouse", warehouse);
		result.addObject("moneyList", moneyList);
		result.addObject("exchangeRate", exchangeRate);

		return result;
	}

	// Creation ----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int warehouseId){
		ModelAndView result;
		Storage storage;
		WareHouse wareHouse;
		
		wareHouse = wareHouseService.findOne(warehouseId);
		storage = storageService.create();
		storage.setWareHouse(wareHouse);		
		
		result = createEditModelAndView(storage);
		
		return result;
	}

	// Edition ----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int storageId){
		ModelAndView result;
		Storage stora;
		
		stora = storageService.findOne(storageId);
		
		Assert.notNull(stora);
		
		result = createEditModelAndView(stora);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Storage stora, BindingResult binding){
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(stora);
		} else {
			try {
				storageService.updateQuantityByWareHouseAndItem(stora.getWareHouse(), stora.getItem(), stora.getUnits());	
				result = new ModelAndView("redirect:list.do?warehouseId=" + stora.getWareHouse().getId());
			} catch (Throwable oops) {
				result = createEditModelAndView(stora, "storage.commit.error");				
			}
		}		
		
		return result;
	}
	
	
	// Ancillary Methods ----------------------------------------------------------

	protected ModelAndView createEditModelAndView(Storage storage) {
		ModelAndView result;
		
		result = createEditModelAndView(storage, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Storage storage, String message) {
		ModelAndView result;
		Collection<Item> items;
		Collection<WareHouse> warehouses;
		
		items = itemService.findAll();
		warehouses = wareHouseService.findAll();
		
		result = new ModelAndView("storage/edit");
		result.addObject("storage", storage);
		result.addObject("items", items);
		result.addObject("warehouses", warehouses);
		result.addObject("message", message);
		
		return result;
	}	
}
