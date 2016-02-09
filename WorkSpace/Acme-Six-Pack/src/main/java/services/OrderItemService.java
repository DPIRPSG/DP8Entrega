package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Category;
import domain.Item;
import domain.Order;
import domain.OrderItem;
import domain.ShoppingCart;
import domain.Tax;

import repositories.OrderItemRepository;

@Service
@Transactional
public class OrderItemService {
 	//Managed repository -----------------------------------------------------

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	//Supporting services ----------------------------------------------------
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private TaxService taxService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private OrderService orderService;

	//Constructors -----------------------------------------------------------

	public OrderItemService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	/** Devuelve OrderItem preparado para ser modificado. Necesita usar save para que persista en la base de datos
	 * 
	 */	
	//req: 11.7
	public OrderItem create(){
		OrderItem result;
		
		result = new OrderItem();
		
		return result;
	}
	
	public void save(OrderItem orderItem){
		Assert.notNull(orderItem);
		Order order;
		boolean orderComplete = true;
		
		if(orderItem.getUnitsServed() == orderItem.getUnits()) {
			order = orderItem.getOrder();
			for(OrderItem o : order.getOrderItems()) {
				orderComplete = orderComplete && (o.getUnitsServed() == o.getUnits());
			}
			if(orderComplete) {
				orderService.completedOrder(order);
			}
		}
		
		orderItemRepository.save(orderItem);
	}
	
	/**
	 * Guarda o actualiza muchos orderItems
	 */
	//req: 11.7
	public void save(Collection<OrderItem> orderItems){
		Assert.notNull(orderItems);

		orderItemRepository.save(orderItems);
	}

	//Other business methods -------------------------------------------------
	
	/**
	 * NO USAR. Usar desde ShoppingCartService.createCheckOut. Devuelve los orders items creados pero no alamacenados.
	 */
	// req: 11.7
	public Collection<OrderItem> createByShoppingCart(ShoppingCart shoppingCart, Order order){
		Assert.notNull(shoppingCart);
		// Assert.isTrue(shoppingCart.getId() != 0);
		Assert.notNull(order);
		// La order no está creada por lo que no se puede chequear el id
		
		Collection<OrderItem> result;
		Collection<Item> items;
		OrderItem orderItem;
		
		result = new ArrayList<OrderItem>();
		// Debe devolver los items no borrados del sistema
		items = itemService.findAllByShoppingCart(shoppingCart);
		
		Assert.notEmpty(items, "Can't create OrderItems if the shoppingCart is empty");
		
		for (Item item : items) {
			int units;
			
			units = shoppingCartService.consultItemQuantity(shoppingCart, item);			
			orderItem = this.createByShoppingCart(item, order, units);
			result.add(orderItem);
		}	
		
		return result;
	}
	
	/**
	 * Crea un OrderItem dado un item. No se almacena.
	 */
	//req: 11.7
	private OrderItem createByShoppingCart(Item item, Order order, int units){
		OrderItem result;
		Tax tax;
		Category category;

		category = categoryService.findByItem(item);
		tax = taxService.findByCategory(category);
		
		result = this.create();
		result.setSku(item.getSku());
		result.setName(item.getName());
		result.setDescription(item.getDescription());
		result.setPrice(item.getPrice());
		result.setTags(item.getTags());
		result.setDeleted(item.getDeleted());
		result.setTax(tax.getValue());
		result.setNameCategory(category.getName());
		result.setUnits(units);
		result.setOrder(order);
		
		return result;
	}

	public Collection<OrderItem> findAllByOrderId(int orderId) {
		Assert.notNull(orderId);
		
		Collection<OrderItem> result;
		
		result = orderItemRepository.findAllByOrderId(orderId);
		
		return result;
	}

	public OrderItem findOne(int orderItemId) {
		Assert.notNull(orderItemId);

		OrderItem result;
		
		result = orderItemRepository.findOne(orderItemId);
		
		return result;
	}
	

	
}
