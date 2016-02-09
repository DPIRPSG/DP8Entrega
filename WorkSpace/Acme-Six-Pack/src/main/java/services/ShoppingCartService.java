package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Consumer;
import domain.Content;
import domain.Item;
import domain.Order;
import domain.ShoppingCart;

import repositories.ShoppingCartRepository;

@Service
@Transactional
public class ShoppingCartService {

 	//Managed repository -----------------------------------------------------

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	//Supporting services ----------------------------------------------------
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ConsumerService consumerService;
	
	@Autowired
	private ActorService actorService;
	
	//Constructors -----------------------------------------------------------

	public ShoppingCartService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------

	/**
	 * Inicializa un carrito
	 */
	public ShoppingCart create(Consumer consumer){
		ShoppingCart result;
		Collection<Content> content;
		
		result = new ShoppingCart();
		content = new ArrayList<Content>();
		
		result.setConsumer(consumer);
		result.setContents(content);
		
		return result;
	}
	
	/**
	 * Guarda los cambios del carrito. Usar solo para comentarios.
	 */
	//req: 11.6
	public void save(ShoppingCart shoppingCart){
		Assert.notNull(shoppingCart);
		
		shoppingCartRepository.save(shoppingCart);
	}
		
	
	//Other business methods -------------------------------------------------
 
	/**
	 * Crea un Order al que solo se le deben añadir los últimos datos y guardarse en la base de datos.
	 * 
	 * Ninguno de los elementos creados son persistidos en la base de datos
	 */
	// req: 11.7
	public Order createCheckOut(){
		Assert.isTrue(actorService.checkAuthority("CONSUMER"), "Only the consumer can place the order");
		
		Consumer consumer;
		Order result;
		ShoppingCart shoppingCart;
		
		consumer = consumerService.findByPrincipal();
		Assert.notNull(consumer);
		shoppingCart = this.findByConsumer(consumer);		
		
		// Create a order with their orderItems (none is persist)
		result = orderService.createFromShoppingCart(shoppingCart, consumer);

		return result;
	}
	
	/**
	 * Guarda el objeto creado con createCheckOut
	 */
	//req: 11.7
	public void saveCheckOut(Order order, Consumer consumer){
		Assert.notNull(order);
		Assert.notNull(consumer);
		Assert.isTrue(order.getConsumer().equals(consumer), "Only the owner can keep order");
		
		orderService.saveFromShoppingCart(consumer.getShoppingCart(), order);
		this.emptyShoppingCart(consumer);
	}
	
	/**
	 * Vacia el carrito
	 * 
	 */
	//req: 11.7
	private void emptyShoppingCart(Consumer consumer){
		Assert.notNull(consumer);
		Assert.isTrue(consumer.getId() != 0);
		
		ShoppingCart shoppingCart;
		
		shoppingCart = this.findByConsumer(consumer);
		
		shoppingCart.emptyComments();
		contentService.emptyByShoppingCart(shoppingCart);
		
		this.save(shoppingCart);
	}
	
	/**
	 * Devuelve el carrito de un consumer.
	 */
	//req: 11.2, 11.7
	public ShoppingCart findByConsumer(Consumer consumer){
		Assert.notNull(consumer);
		
		ShoppingCart result;
		
		result = shoppingCartRepository.findByConsumerId(consumer.getId());
		
		return result;
	}
	
	/**
	 * 
	 * Dice la cantidad de un Item en un carrito
	 */
	// req: 11.2, 11.7
	public int consultItemQuantity(ShoppingCart shoppingCart, Item item){
		int result;
		
		result = contentService.quantityByShoppingCartAndItem(shoppingCart, item);
		
		return result;
	}
	
	/**
	 * Añade un Item a un carrito
	 */
	//req: 11.3
	public void addItem(ShoppingCart shoppingCart, Item item){
		contentService.createByShoppingCartAndItem(shoppingCart, item);
	}

	/**
	 * Cambia la cantidad de Items en un carrito
	 */
	//req: 11.4
	public void changeItemQuantity(ShoppingCart shoppingCart, Item item, int quantity){
		contentService.updateQuantityByShoppingCartAndItem(shoppingCart, item, quantity);
	}
	
	/**
	 * Cambia la cantidad de Items en un carrito
	 */
	//req: 11.5
	public void deleteItemQuantity(ShoppingCart shoppingCart, Item item){
		contentService.updateQuantityByShoppingCartAndItem(shoppingCart, item, 0);
	}
	
	public void addComment(ShoppingCart shoppingCart, String comment){
		Assert.notNull(shoppingCart);
		Assert.isTrue(shoppingCart.getId() != 0);
		
		shoppingCart.addComment(comment);
		shoppingCartRepository.save(shoppingCart);
	}
	
	public ShoppingCart finOneByShoppingCartId(int shoppingCartId){
		ShoppingCart shoppingCart;
		
		shoppingCart = shoppingCartRepository.findOne(shoppingCartId);
		
		Assert.isTrue(shoppingCart.getConsumer().equals(consumerService.findByPrincipal()), "Only the owner of the shopping cart can display it");
		
		return shoppingCart;
	}
}
