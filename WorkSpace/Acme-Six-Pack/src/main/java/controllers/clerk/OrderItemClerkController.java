package controllers.clerk;

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
import services.OrderItemService;
import services.OrderService;
import services.WareHouseService;
import controllers.AbstractController;
import domain.ExchangeRate;
import domain.Item;
import domain.Order;
import domain.OrderItem;
import domain.WareHouse;

@Controller
@RequestMapping(value = "/order-item/clerk")
public class OrderItemClerkController extends AbstractController {


	//Services ----------------------------------------------------------
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private WareHouseService warehouseService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ExchangeRateService exchangeRateService;

	//Constructors ----------------------------------------------------------

	public OrderItemClerkController() {
		super();
	}
	
	//Listing ----------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int orderId, @RequestParam(required=false) Integer exchangeRateId) {
		ModelAndView result;
		Collection<OrderItem> ordersItem;
		Order order;
		ExchangeRate exchangeRate;
        Collection<ExchangeRate> moneyList;
        
        exchangeRate = null;
		moneyList = exchangeRateService.findAll();
		
		if(exchangeRateId != null) {
			exchangeRate = exchangeRateService.findOne(exchangeRateId);
		} else {
			exchangeRate = exchangeRateService.findOneByName("Euros");
		}

		order = orderService.findOne(orderId);
		ordersItem = orderItemService.findAllByOrderId(orderId);
		
		result = new ModelAndView("order-item/list");
		result.addObject("requestURI", "order-item/clerk/list.do");
		result.addObject("orders-item", ordersItem);
		result.addObject("order", order);
		result.addObject("moneyList", moneyList);
		result.addObject("exchangeRate", exchangeRate);
		result.addObject("orderId", orderId);

		return result;
	}
	
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int orderId, @RequestParam int warehouseId) {
		ModelAndView result;
		String warehouse;
		
		warehouse = warehouseService.findOne(warehouseId).getName();

		
		result = new ModelAndView("order-item/confirm");
		result.addObject("requestURI", "order-item/clerk/confirm.do");
		result.addObject("warehouse", warehouse);
		result.addObject("orderId", orderId);

		return result;
	}
	
	//Creation ----------------------------------------------------------
	
	@RequestMapping(value = "/serve", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int orderItemId) {
		ModelAndView result;
		OrderItem orderItem;
		
		orderItem = orderItemService.findOne(orderItemId);
		Assert.notNull(orderItem);
		
		result = createEditModelAndView(orderItem);
		
		return result;
	}
	
	//Edition ----------------------------------------------------------
	
	@RequestMapping(value = "/serve", method = RequestMethod.POST, params = "serve")
	public ModelAndView save(@Valid OrderItem orderItem, @RequestParam String unitsToServe, BindingResult binding) {
		ModelAndView result;
		Order order;
		Item item;
		String sku;
		int orderId;
		int warehouseId;
		WareHouse warehouse;
		int units;
		
		
		
		warehouse = null;
		
		orderId = orderItem.getOrder().getId();
		sku = orderItem.getSku();

		order = orderItem.getOrder();
		item = itemService.findOneBySKU(sku);

		if (binding.hasErrors()) {
			result = createEditModelAndView(orderItem);
		} else {
			try {
				units = Integer.parseInt(unitsToServe);

				warehouse = warehouseService.addItemToOrderItem(item, units,
						order);
				warehouseId = warehouse.getId();
				result = new ModelAndView("redirect:confirm.do?orderId="
						+ orderId + "&warehouseId=" + warehouseId);
			} catch (NumberFormatException oops) {
				result = createEditModelAndView(orderItem,
						"orderItem.commit.error.quantity");
			} catch (Throwable oops) {
				System.out.println(oops);
				if (oops.getMessage().equals(
						"No hay suficientes unidades en los almacenes")) {
					result = createEditModelAndView(orderItem,
							"orderItem.commit.error.warehouse");

				} else {
					result = createEditModelAndView(orderItem,
							"orderItem.commit.error");
				}
			}
		}
		return result;
	}

	//Ancillary Methods ----------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(OrderItem orderItem) {
		ModelAndView result;
		
		result = createEditModelAndView(orderItem, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(OrderItem orderItem, String message) {
		ModelAndView result;
		int units;
		int unitsServed;
		int orderId;

		Order order;
		Item item;
		
		units = orderItem.getUnits();
		unitsServed = orderItem.getUnitsServed();
		orderId = orderItem.getOrder().getId();
		
		order = orderService.findOne(orderItem.getOrder().getId());
		item = itemService.findOneBySKU(orderItem.getSku());
		
		result = new ModelAndView("order-item/serve");
		result.addObject("orderItem", orderItem);
		result.addObject("message", message);
		result.addObject("unitsNum", units);
		result.addObject("unitsServedNum", unitsServed);
		result.addObject("orderId", orderId);
		
		result.addObject("order", order);
		result.addObject("item", item);

		
		return result;
	}
}

