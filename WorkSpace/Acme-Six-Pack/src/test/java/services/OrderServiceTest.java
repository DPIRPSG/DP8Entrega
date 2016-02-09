package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Clerk;
import domain.Item;
import domain.Order;
import domain.OrderItem;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml",
	"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class OrderServiceTest extends AbstractTest{

	// Service under test -------------------------
	@Autowired
	private OrderService orderService;
	@Autowired
	private ClerkService clerkService;
	@Autowired
	private WareHouseService warehouseService;
	@Autowired
	private ItemService itemService;
	
	// Test ---------------------------------------
	@Test
	public void testList1(){
		System.out.println("Requisito 12.6 - List the consumers that are registered in the system.");
		System.out.println("ConsumerServiceTest - testList1 - StartPoint");
		
		Collection<Order> all;
		
		authenticate("admin");
		all = orderService.findAll();
		
		System.out.println("Lista de order placed en el sistema");
		
		for(Order o:all){
			System.out.println(o.getTicker() + ", " + o.getPlacementMoment());
		}
		
		System.out.println("ConsumerServiceTest - testList1 - FinishPoint");
	}
	
	@Test
	public void testCancelOrder1(){
		System.out.println("Requisito 16.1 - Cancel an order, as long a no clerk has self-assigned it.");
		System.out.println("OrderServiceTest - testCancelOrder1 - StartPoint");
		
		Order order;
		Collection<Order> all;
		
		order = null;
		
		authenticate("admin");
		
		all = orderService.findAll();
		
		authenticate(null);
		for(Order o:all){
			if(o.getClerk()==null){
				order = o;
				break;
			}
		}
		authenticate(order.getConsumer().getUserAccount().getUsername());
		
		System.out.println("Fecha de cancelación de la order antes de cancelarla:");
		System.out.println(order.getCancelMoment());

		orderService.cancelOrder(order);
		
		System.out.println("Fecha de cancelación de la order después de cancelarla:");
		System.out.println(order.getCancelMoment());
		
		authenticate(null);
		
		System.out.println("OrderServiceTest - testCancelOrder1 - FinishPoint");
	}
	
	@Test
	public void testOrderRatio1(){
		System.out.println("OrderServiceTest - testOrderRatio1 - StartPoint");
		
		double ratio;
		
		authenticate("admin");
		ratio = orderService.rateOrderCancelled();
		System.out.println("Ratio: " + ratio);
		
		authenticate(null);
		
		System.out.println("OrderServiceTest - testOrderRatio1 - FinishPoint");
	}
	
	@Test
	public void testOrderSelfAssignedByClerk1(){
		System.out.println("OrderServiceTest - testOrderSelfAssignedByClerk1 - StartPoint");
		
		Clerk clerk;
		Order order;
		Order orderToAssign;
		Collection<Order> all;
		
		authenticate("clerk1");
		
		System.out.println("Cogemos un clerk:");
		order = orderService.findAll().iterator().next();
		clerk = clerkService.findByOrder(order);
		System.out.println(clerk.getName() + "\n");
		System.out.println("Cogemos una order que no tenga asignada un clerk:");
		all = orderService.findAll();
		orderToAssign = null;
		for(Order o: all){
			if(o.getClerk()==null){
				orderToAssign = o;
				break;
			}
		}
		System.out.println(orderToAssign.getId());
		System.out.println("\nLe asignamos el clerk " + clerk.getName());
		orderService.assignToClerkManual(clerk, orderToAssign);
		System.out.println("\nComprobamos el clerk que tiene ahora esa order:");
		System.out.println(orderToAssign.getClerk().getName());
		
		authenticate(null);
		
		System.out.println("OrderServiceTest - testOrderSelfAssignedByClerk1 - FinishPoint");
	}
	
	@Test
	public void testOrderDeliveredWhenItemsServed1(){
		System.out.println("OrderServiceTest - testOrderDeliveredWhenItemsServed1 - StartPoint");
		
		Order order;
		OrderItem orderItem;
		OrderItem orderItemServed;
		Item item;
		
		authenticate("clerk1");
		
		System.out.println("Vamos a servir todas las unidades de este orderItem:");
		item = itemService.findAll().iterator().next();
		order = orderService.findAll().iterator().next();
		orderItem = order.getOrderItems().iterator().next();
		System.out.println("OrderItem: " + orderItem.getName());
		System.out.println("Unidades a servir: " + orderItem.getUnits());
		System.out.println("Unidades servidas: " + orderItem.getUnitsServed());
		
		System.out.println("Servimos sus unidades restantes:");
		
		warehouseService.addItemToOrderItem(item, 2, order);
		
		System.out.println("Comprobemos que ya tenga deliveryMoment a hoy y unidades servidas 2:");
		orderItemServed = order.getOrderItems().iterator().next();
		System.out.println("OrderItem: " + orderItemServed.getName());
		System.out.println("Unidades servidas: " + orderItemServed.getUnitsServed());
		System.out.println("Fecha: " + order.getDeliveryMoment());
		
		authenticate(null);
		
		System.out.println("OrderServiceTest - testOrderDeliveredWhenItemsServed1 - FinishPoint");
	}
}
