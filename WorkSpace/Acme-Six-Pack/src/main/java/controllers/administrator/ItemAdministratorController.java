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

import services.CategoryService;
import services.ExchangeRateService;
import services.ItemService;


import controllers.AbstractController;
import domain.Category;
import domain.ExchangeRate;
import domain.Item;

@Controller
@RequestMapping(value = "/item/administrator")
public class ItemAdministratorController extends AbstractController {

	//Services ----------------------------------------------------------
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ExchangeRateService exchangeRateService;
	//Constructors ----------------------------------------------------------

	public ItemAdministratorController() {
		super();
	}
	
	//Listing ----------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required=false, defaultValue="") String keyword, @RequestParam(required=false) Integer exchangeRateId) {
		ModelAndView result;
		Collection<Item> items;
		Collection<ExchangeRate> moneyList;
		String keywordToFind;
		ExchangeRate exchangeRate;

		exchangeRate = null;
		moneyList = exchangeRateService.findAll();
				
		items = itemService.findAll();
		
		if (!keyword.equals("")) {
			String[] keywordComoArray = keyword.split(" ");
			for (int i = 0; i < keywordComoArray.length; i++) {
				if (!keywordComoArray[i].equals("")) {
					keywordToFind = keywordComoArray[i];
					items = itemService.findBySingleKeyword(keywordToFind);
					break;
				}
			}
		}
		
		if(exchangeRateId != null) {
			exchangeRate = exchangeRateService.findOne(exchangeRateId);
		} else {
			exchangeRate = exchangeRateService.findOneByName("Euros");
		}

		result = new ModelAndView("item/list");
		result.addObject("requestURI", "item/administrator/list.do?");
		result.addObject("items", items);
		result.addObject("moneyList", moneyList);
		result.addObject("exchangeRate", exchangeRate);
		result.addObject("keyword", keyword);

		return result;
	}
	
	//Creation ----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Item item;
		
		item = itemService.create();		
		result = createEditModelAndView(item);
		
		return result;
	}
	
	//Edition ----------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int itemId) {
		ModelAndView result;
		Item item;
		
		item = itemService.findOne(itemId);
		Assert.notNull(item);
		result = createEditModelAndView(item);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Item item, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(item);
		} else {
			try {
				itemService.save(item);		
				result = new ModelAndView("redirect:list.do?keyword=");
			} catch (Throwable oops) {
				result = createEditModelAndView(item, "item.commit.error");				
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Item item, BindingResult binding) {
		ModelAndView result;

		try {
			itemService.delete(item);
			result = new ModelAndView("redirect:list.do?keyword=");
		} catch (Throwable oops) {
			result = createEditModelAndView(item,
					"item.commit.error");
		}

		return result;
	}
	
	//Ancillary Methods ----------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(Item item) {
		ModelAndView result;
		
		result = createEditModelAndView(item, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Item item, String message) {
		ModelAndView result;
		Collection<Category> categories;
		
		categories = categoryService.findAll();
	
		result = new ModelAndView("item/edit");
		result.addObject("item", item);
		result.addObject("categories", categories);
		result.addObject("message", message);
		
		return result;
	}	
}
