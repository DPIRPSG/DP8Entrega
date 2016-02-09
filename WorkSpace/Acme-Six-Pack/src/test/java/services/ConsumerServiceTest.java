package services;


import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import security.UserAccount;
import utilities.AbstractTest;
import domain.Consumer;
import domain.Message;
import domain.Order;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml",
	"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class ConsumerServiceTest extends AbstractTest{

	// Service under test -------------------------
	@Autowired
	private ConsumerService consumerService;
		
	// Test ---------------------------------------
	@Test
	public void testConsumerCancelledMoreOrders1(){
		System.out.println("ConsumerServiceTest - testConsumerCancelledMoreOrders1 - StartPoint");
		
		Collection<Consumer> all;
		
		authenticate("admin");
		
		all = consumerService.findConsumerMoreOrdersCancelled();
		for(Consumer c:all){
			System.out.println(c.getName() + " " + c.getSurname());
		}
		
		authenticate(null);
		
		System.out.println("ConsumerServiceTest - testConsumerCancelledMoreOrders1 - FinishPoint");
	}
	
	@Test
	public void testConsumerCancelledLessOrders1(){
		System.out.println("ConsumerServiceTest - testConsumerCancelledLessOrders1 - StartPoint");
		
		Collection<Consumer> all;
		
		authenticate("admin");
		
		all = consumerService.findConsumerLessOrdersCancelled();
		for(Consumer c:all){
			System.out.println(c.getName() + " " + c.getSurname());
		}
		
		authenticate(null);
		
		System.out.println("ConsumerServiceTest - testConsumerCancelledLessOrders1 - FinishPoint");
	}
	
	@Test
	public void testList1(){
		System.out.println("Requisito 12.5 - List the consumers that are registered in the system.");
		System.out.println("ConsumerServiceTest - testList1 - StartPoint");
		
		Collection<Consumer> all;
		
		authenticate("admin");
		
		all = consumerService.findAll();
		
		System.out.println("Lista de consumer registrados en el sistema:");
		for(Consumer c:all){
			System.out.println(c.getName());
		}
		authenticate(null);	
		System.out.println("ConsumerServiceTest - testList1 - FinishPoint");
	}
	
	@Test
	public void testCreate1(){
		System.out.println("Requisito 10.1 - Register a new consumer to the system.");
		System.out.println("ConsumerServiceTest - testConsumer1 - StartPoint");
		
		Consumer result;
		Collection<Consumer> all;
		UserAccount userAccount;
		Collection<Message> received;
		Collection<Message> sent;
		Collection<Order> orders;
		
		received = new ArrayList<Message>();
		sent = new ArrayList<Message>();
		orders = new ArrayList<Order>();
		
		authenticate("admin");
		all = consumerService.findAll();
		System.out.println("Lista de Consumers antes de la creación de otro");
		for(Consumer c:all){
			System.out.println(c.getName());
		}
		authenticate(null);
		
		result = consumerService.create();
		userAccount = result.getUserAccount();
		
		result.setName("Fatima");
		result.setEmail("fatima@mail.com");
		result.setPhone("666123321");
		result.setSurname("Caballero");
		userAccount.setUsername("Consumer99");
		userAccount.setPassword("Consumer99");
		result.setUserAccount(userAccount);
		result.setReceived(received);
		result.setSent(sent);
		result.setOrders(orders);



		consumerService.save(result);
		
		authenticate("admin");
		all = consumerService.findAll();
		System.out.println("Lista de Consumers después de la creación de otro");
		for(Consumer c:all){
			System.out.println(c.getName());
		}
		authenticate(null);
		
		System.out.println("ConsumerServiceTest - testConsumer1 - FinishPoint");
	}
	
	@Test
	public void testFindConsumerMoreOrders1(){
		System.out.println("Requisito 12.7.1 - The consumer/s who has/have placed more orders.");
		System.out.println("ConsumerServiceTest - testFindConsumerMoreOrders1 - StartPoint");
		
		Collection<Consumer> all;
		
		authenticate("admin");
		
		all = consumerService.findConsumerMoreOrders();
		
		System.out.println("The consumer/s who has/have placed more orders.");
		for(Consumer c:all){
			System.out.println(c.getName());
		}
		
		authenticate(null);
		
		System.out.println("ConsumerServiceTest - testFindConsumerMoreOrders1 - FinishPoint");
	}
	
	@Test
	public void testFindConsumerSpentMoreMoney1(){
		System.out.println("Requisito 12.7.2 - The consumer/s who has/have spent more money on their orders.");
		System.out.println("ConsumerServiceTest - testFindConsumerSpentMoreMoney1 - StartPoint");
		
		Collection<Consumer> all;
		
		authenticate("admin");
		
		all = consumerService.findConsumerSpentMoreMoney();
		
		System.out.println("The consumer/s who has/have spent more money on their orders.");
		for(Consumer c:all){
			System.out.println(c.getName());
		}
		
		authenticate(null);
		
		System.out.println("ConsumerServiceTest - testFindConsumerSpentMoreMoney1 - FinishPoint");

	}
}

