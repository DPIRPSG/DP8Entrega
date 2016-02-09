package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.WareHouseRepository;
import domain.Item;
import domain.Order;
import domain.OrderItem;
import domain.Storage;
import domain.WareHouse;

@Service
@Transactional 
public class WareHouseService {
 	//Managed repository -----------------------------------------------------

	@Autowired
	private WareHouseRepository wareHouseRepository;
	
	//Supporting services ----------------------------------------------------
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private ClerkService clerkService;
	
	//Constructors -----------------------------------------------------------

	public WareHouseService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------

	/**
	 * Lista todos los warehouse
	 */
	//req: 17.2
	public Collection<WareHouse> findAll(){
		Assert.isTrue(actorService.checkAuthority("ADMIN") || actorService.checkAuthority("CLERK"), "Only an admin or clerk can list the warehouses");
		
		Collection<WareHouse> result;
		
		result = wareHouseRepository.findAll();
		
		return result;
	}
	
	/**
	 * Devuelve el warehouse con el id
	 */
	public WareHouse findOne(int id){
		WareHouse result;
		
		result = wareHouseRepository.findOne(id);
		Assert.notNull(result);
		
		return result;
	}
	
	/**
	 * Devuelve WareHouse preparado para ser modificado. Necesita usar save para que persista en la base de datos
	 */
	//req: 17.3
	public WareHouse create(){
		WareHouse result;
		Collection<Storage> storages;
		
		storages = new ArrayList<Storage>();		
		result = new WareHouse();
		
		result.setStorages(storages);
		
		return result;
	}
	
	/**
	 * Guarda un wareHouse creado o modificado
	 */
	//req: 17.3
	public void save(WareHouse wareHouse){
		Assert.isTrue(actorService.checkAuthority("ADMIN") || actorService.checkAuthority("CLERK"), "Only an admin or clerk can save a warehouse");

		Assert.notNull(wareHouse);
		
		wareHouseRepository.save(wareHouse);
	}
	
	/**
	 * Elimina un Warehouse sin Items
	 */
	//req: 17.4
	public void delete(WareHouse wareHouse){
		Assert.notNull(wareHouse);
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can delete a warehouses");

		Collection<Item> items;
		
		items = itemService.findAllByWareHouse(wareHouse);
		
		Assert.isTrue(items.isEmpty(), "The warehouse isn't empty");
		
		wareHouseRepository.delete(wareHouse);
	}
	
	//Other business methods -------------------------------------------------
	
	/**
	 * Dado un wareHouse y un item, actualiza la cantidad (no para las orders)
	 */
	//req: 17.5
	public void changeItemQuantity(WareHouse wareHouse, Item item, int quantity){
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can change the quantity of items in the warehouses");
		
		storageService.updateQuantityByWareHouseAndItem(wareHouse, item, quantity);
	}
	/**
	 * Actualiza la cantidad de item en un WareHouse y de orderItem en una order
	 */
	public WareHouse addItemToOrderItem(Item item, int quantity, Order order) {
		// Assert.isTrue(storageService.quantityByWareHouseAndItem(wareHouse,
		// item) >= quantity,
		// "No se pueden añadir a una order mas items de los que hay en el WareHouse");
		Assert.isTrue(clerkService.findByprincipal().equals(order.getClerk()),
				"Only the clerk of the order can add items");
		Assert.isTrue(order.getCancelMoment() == null, "No se pueden servir unidades de un pedido cancelado");
		Assert.notNull(quantity, "No puedes pasar una cantidad vacia");
		
		
		WareHouse result;
		OrderItem orderItem;
		Collection<OrderItem> orderItems;
		Collection<WareHouse> warehouses;
		int unitsServed;

		warehouses = wareHouseRepository.findAll();

		result = null;
		
		orderItem = null;
		orderItems = order.getOrderItems();
		for (OrderItem o : orderItems) {
			if (item.getSku().equals(o.getSku())) {
				orderItem = o;
				break;
			}
		}

		Assert.notNull(orderItem, "No existe OrderItem del Item pasado");

		unitsServed = orderItem.getUnitsServed() + quantity;

		Assert.isTrue(unitsServed <= orderItem.getUnits(),
				"Se intentan añadir mas unidades de las solicitadas por el OrderItem");
		for (WareHouse warehouse : warehouses) {
			try {
				if (storageService.quantityByWareHouseAndItem(warehouse, item) >= quantity) {
					storageService.subtractQuantityByWareHouseAndItem(warehouse,
							item, quantity);
					orderItem.setUnitsServed(unitsServed);
					result = warehouse;
					break;
				}
			} catch (Exception e) {
				result = null;
			}
			
		}
		Assert.notNull(result, "No hay suficientes unidades en los almacenes");
		orderItemService.save(orderItem);
		
		return result;
	}
	
	/**
	 * Elimina una cantidad de items del wareHouse
	 */
	//ref: 18.4
	@SuppressWarnings("unused")
	private void removeItemQuantity(WareHouse wareHouse, Item item, int quantityToEliminate){
		int actualQuantity;
		Integer finalQuantity;
		
		actualQuantity = storageService.quantityByWareHouseAndItem(wareHouse, item);
		finalQuantity = actualQuantity - quantityToEliminate;
		
		// Intenta eliminar más de los Items que hay
		Assert.isTrue(finalQuantity >= 0, "The final quantity is lower than 0");
		
		storageService.updateQuantityByWareHouseAndItem(wareHouse, item, finalQuantity);
	}
	
	/**
	 * Lista los warehouse que contienen alguna unidad de un item
	 */
	//req: 18.2
	public Collection<WareHouse> findAllByItem(Item item){
		Assert.notNull(item);
		
		Collection<WareHouse> result;
		
		result = wareHouseRepository.findAllByItemId(item.getId());
		
		return result;
	}
}
