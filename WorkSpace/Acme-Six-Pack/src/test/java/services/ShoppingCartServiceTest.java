package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Consumer;
import domain.Content;
import domain.CreditCard;
import domain.Item;
import domain.Order;
import domain.OrderItem;
import domain.ShoppingCart;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml",
	"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class ShoppingCartServiceTest extends AbstractTest{

	// Service under test -------------------------
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ConsumerService consumerService;
	
	// Test ---------------------------------------
	
	//Requisito 11.2
	@Test
	public void testFindByConsumer1(){
		System.out.println("Requisito 11.2 - Displaying his or her shopping cart.");
		System.out.println("ShoppingCartServiceTest - testFindByConsumer1 - StartPoint");
		
		ShoppingCart shoppingCart;
		Consumer consumer;
		
		authenticate("admin");
		consumer = consumerService.findAll().iterator().next();
		
		authenticate(consumer.getUserAccount().getUsername());
		shoppingCart = shoppingCartService.findByConsumer(consumer);
		
		System.out.println("Consumer al que hacemos referencia:");
		System.out.println(consumer.getName());
		System.out.println("Su ShoppingCart");
		System.out.println(shoppingCart.getId());
		
		authenticate(null);
		System.out.println("ShoppingCartServiceTest - testFindByConsumer1 - Finish Point");
	}
	
	// Requisito 11.3
	@Test
	public void testAddItem1(){
		System.out.println("Requisito 11.3 - Add an item to his or her shopping cart. If an item is added to a shopping cart that already contains that item, the quantity must be updated accordingly.");
		System.out.println("ShoppingCartServiceTest - testAddItem1 - StartPoint");
		
		ShoppingCart shoppingCart;
		Consumer consumer;
		Item item;
		
		authenticate("admin");
		
		consumer = consumerService.findAll().iterator().next();
		
		authenticate(consumer.getUserAccount().getUsername());		
		shoppingCart = shoppingCartService.findByConsumer(consumer);
		item = itemService.findAll().iterator().next();
		
		System.out.println("Lista de Items de ShoppingCart antes de añadir el nuevo item:");
		for(Content c: shoppingCart.getContents()){
			System.out.println(c.getItem() + ", " + c.getUnits());
		}
		
		shoppingCartService.addItem(shoppingCart, item);
		
		System.out.println("Lista de Items de ShoppingCart después de añadir el nuevo item:");
		for(Content c: shoppingCart.getContents()){
			System.out.println(c.getItem() + ", " + c.getUnits());
		}
		
		authenticate(null);
		
		System.out.println("ShoppingCartServiceTest - testAddItem1 - FinishPoint");
	}
	
	// Requisito 11.4
	@Test
	public void testChangeItemQuantity1(){
		System.out.println("Requisito 11.4 - Change the quantity of an item in his or her shopping cart.");
		System.out.println("ShoppingCartServiceTest - testChangeItemQuantity1 - StartPoint");
		
		ShoppingCart shoppingCart;
		Consumer consumer;
		Item item;
		
		authenticate("admin");
		
		consumer = consumerService.findAll().iterator().next();
		authenticate(consumer.getUserAccount().getUsername());
		
		shoppingCart = shoppingCartService.findByConsumer(consumer);
		item = null;
		for(Content c:shoppingCart.getContents()){
			item = c.getItem();
			break;
		}
		
		System.out.println("Lista de Items de ShoppingCart antes de cambiar el número de unidades de un item:");
		for(Content c: shoppingCart.getContents()){
			System.out.println(c.getItem() + ", " + c.getUnits());
		}
		
		shoppingCartService.changeItemQuantity(shoppingCart, item, 17);
		
		System.out.println("Lista de Items de ShoppingCart después de cambiar el número de unidades de un item:");
		for(Content c: shoppingCart.getContents()){
			System.out.println(c.getItem() + ", " + c.getUnits());
		}
		
		authenticate(null);
		
		System.out.println("ShoppingCartServiceTest - testChangeItemQuantity1 - FinishPoint");

	}
	
	// Requisito 11.5
	@Test
	public void testDeleteItem1(){
		System.out.println("Requisito 11.5 - Delete an item from his or her shopping cart.");
		System.out.println("ShoppingCartServiceTest - testDeleteItem1 - StartPoint");
		
		ShoppingCart shoppingCart;
		Consumer consumer;
		Item item;
		
		authenticate("admin");
		
		consumer = consumerService.findAll().iterator().next();
		authenticate(consumer.getUserAccount().getUsername());		
		
		shoppingCart = shoppingCartService.findByConsumer(consumer);
		item = null;
		
		for(Content c:shoppingCart.getContents()){
			item = c.getItem();
			break;
		}
		
		System.out.println("Lista de Items de ShoppingCart antes de borrar un item:");
		for(Content c: shoppingCart.getContents()){
			System.out.println(c.getItem() + ", " + c.getUnits());
		}
		
		shoppingCartService.deleteItemQuantity(shoppingCart, item);
		
		System.out.println("Lista de Items de ShoppingCart después de borrar un item:");
		for(Content c: shoppingCart.getContents()){
			System.out.println(c.getItem() + ", " + c.getUnits());
		}
		
		authenticate(null);
		
		System.out.println("ShoppingCartServiceTest - testDeleteItem1 - FinishPoint");

	}
	
	// Requisito 11.6
	@Test
	public void testAddComment1(){
		System.out.println("Requisito 11.6 - Add, modify, or delete a comment to his or her shopping cart.");
		System.out.println("ShoppingCartServiceTest - testAddComment1 - StartPoint");
		
		ShoppingCart shoppingCart;
		Consumer consumer;
		String comment;
		
		authenticate("admin");
		
		consumer = consumerService.findAll().iterator().next();
		authenticate(consumer.getUserAccount().getUsername());
		
		shoppingCart = shoppingCartService.findByConsumer(consumer);
		comment = "comentario de los test";
		
		System.out.println("Comentarios del ShoppingCart antes de añadir un comentario:");
		for(String s: shoppingCart.getComments()){
			System.out.println(s);
		}
		
		shoppingCart.addComment(comment);
		shoppingCartService.save(shoppingCart);
		
		shoppingCart = shoppingCartService.findByConsumer(consumer);
		
		System.out.println("Comentarios del ShoppingCart después de añadir un comentario:");
		for(String s: shoppingCart.getComments()){
			System.out.println(s);
		}
		
		authenticate(null);
		
		System.out.println("ShoppingCartServiceTest - testAddComment1 - FinishPoint");

	}

	@Test
	public void testRemoveComment1(){
		System.out.println("Requisito 11.6 - Add, modify, or delete a comment to his or her shopping cart.");
		System.out.println("ShoppingCartServiceTest - testRemoveComment1 - StartPoint");
		
		ShoppingCart shoppingCart;
		Consumer consumer;
		String comment;
		
		authenticate("admin");
		
		consumer = consumerService.findAll().iterator().next();
		authenticate(consumer.getUserAccount().getUsername());		
		
		shoppingCart = shoppingCartService.findByConsumer(consumer);
		comment = shoppingCart.getComments().iterator().next();
		
		System.out.println("Comentarios del ShoppingCart antes de borrar un comentario:");
		for(String s: shoppingCart.getComments()){
			System.out.println(s);
		}
		
		shoppingCart.removeComment(comment);
		shoppingCartService.save(shoppingCart);
		
		shoppingCart = shoppingCartService.findByConsumer(consumer);
		
		System.out.println("Comentarios del ShoppingCart después de borrar un comentario:");
		for(String s: shoppingCart.getComments()){
			System.out.println(s);
		}
		
		authenticate(null);
		
		System.out.println("ShoppingCartServiceTest - testRemoveComment1 - FinishPoint");

	}

	@Test
	public void testModifyComment1(){
		System.out.println("Requisito 11.6 - Add, modify, or delete a comment to his or her shopping cart.");
		System.out.println("ShoppingCartServiceTest - testModifyComment1 - StartPoint");
		
		ShoppingCart shoppingCart;
		Consumer consumer;
		String commentOld;
		String commentNew;
		
		authenticate("admin");
		
		consumer = consumerService.findAll().iterator().next();
		authenticate(consumer.getUserAccount().getUsername());		
		
		shoppingCart = shoppingCartService.findByConsumer(consumer);
		commentOld = shoppingCart.getComments().iterator().next();
		commentNew = "Comentario modificado";
		
		System.out.println("Comentarios del ShoppingCart antes de modificar un comentario:");
		for(String s: shoppingCart.getComments()){
			System.out.println(s);
		}
		
		shoppingCart.removeComment(commentOld);
		shoppingCart.addComment(commentNew);
		shoppingCartService.save(shoppingCart);
		
		shoppingCart = shoppingCartService.findByConsumer(consumer);
		
		System.out.println("Comentarios del ShoppingCart después de modificar un comentario:");
		for(String s: shoppingCart.getComments()){
			System.out.println(s);
		}
		
		authenticate(null);
		
		System.out.println("ShoppingCartServiceTest - testModifyComment1 - FinishPoint");

	}	
	
	// Requisito 11.7
	@Test
	public void testCheckOut1(){
		System.out.println("Requisito 11.7 - Check his or her shopping cart out and place the corresponding order.");
		System.out.println("ShoppingCartServiceTest - testCheckOut1 - StartPoint");

		Consumer consumer;
		Order order;
		CreditCard creditCard;

		authenticate("admin");
		
		consumer = consumerService.findAll().iterator().next();
		authenticate(consumer.getUserAccount().getUsername());
		
		System.out.println("Lista de Items de ShoppingCart antes del checkout:");
		for(Content c: consumer.getShoppingCart().getContents()){
			System.out.println(c.getItem() + ", " + c.getUnits());
		}
		
		System.out.println("Lista de Orders antes del checkout");
		for(Order o: consumer.getOrders()){
			System.out.println(o.getTicker());
		}
		
		order = shoppingCartService.createCheckOut();
		
		order.setConsumer(consumer);
		
		order.setAddress("Calle 1");
		
		creditCard = new CreditCard();

		creditCard.setHolderName("HolderName de CC");

		creditCard.setBrandName("Maestro");

		creditCard.setNumber("869985684881412");

		creditCard.setExpirationMonth(12);
		creditCard.setExpirationYear(2020);

		creditCard.setCvvCode(805);

		order.setCreditCard(creditCard);

		shoppingCartService.saveCheckOut(order, consumer);
		
		System.out.println("Lista de las Order después del checkout:");
		for(Order o: consumer.getOrders()){
			System.out.println(o.getTicker());
		}
		
		System.out.println("Lista de Orders después del checkout");	
		for(Order o: consumer.getOrders()){
			System.out.println(o.getTicker());
		}
		
		System.out.println("OrderItems dentro de la order");
		
		for(OrderItem o : order.getOrderItems()) {
			System.out.println(o.getName());
		}
		
		System.out.println("Ticker de la Order creada");
		System.out.println(order.getTicker());
		
		authenticate(null);
		
		System.out.println("ShoppingCartServiceTest - testCheckOut1 - FinishPoint");

	}
}
