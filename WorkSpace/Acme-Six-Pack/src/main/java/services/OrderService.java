package services;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Clerk;
import domain.Consumer;
import domain.CreditCard;
import domain.Order;
import domain.OrderItem;
import domain.ShoppingCart;

import repositories.OrderRepository;

@Service
@Transactional
public class OrderService {

	private Integer ticker = 0;
	//Managed repository -----------------------------------------------------

	@Autowired
	private OrderRepository orderRepository;
	
	//Supporting services ----------------------------------------------------

	@Autowired
	private ClerkService clerkService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private ConsumerService consumerService;
	
	//Constructors -----------------------------------------------------------
	
	public OrderService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	/** Devuelve Order solo con ticker. Necesita añadir OrderItems y usar save para que persista en la base de datos.
	 * 
	 */	
	//req: 11.7
	private Order create(){
		Order result;
		String ticker;
		
		result = new Order();
		
		ticker = this.tickerGenerate();
		
		result.setPlacementMoment(new Date());
		result.setTicker(ticker);
		
		return result;
	}
	/**
	 * Guarda o actualiza una order
	 */
	//req: 11.7
	public Order save(Order order){
		Assert.notNull(order);
		
		Order result;
		
		//orderRepository.saveAndFlush(order);
		result = orderRepository.save(order);
		
		return result;
    }
	
	/**
	 * Lista todas las orders guardadas en el sistema.
	 */
	//req: 12.6
	public Collection<Order> findAll(){
		Collection<Order> result;
		
		Assert.isTrue(actorService.checkAuthority("ADMIN")||actorService.checkAuthority("CLERK"), "Only an admin or a clerk can list the orders");
		
		result = orderRepository.findAll();
		
		return result;
	}
	
	public Order findOne(int id){
		Order result;
		
		result = orderRepository.findOne(id);
		Assert.notNull(result);
		
		return result;
	}
	

	//Other business methods -------------------------------------------------
	
	/**
	 * Guarda la Order desde ShoppingCart. NO USAR. Usar desde ShoppingCartService.saveCheckOut.
	 */
	public void saveFromShoppingCart(ShoppingCart shoppingCart, Order order){
		Assert.notNull(shoppingCart);
		Assert.notNull(order);
		
		Collection<OrderItem> orderItems;
		double amount;
		
		//Check CreditCard
		Assert.isTrue(this.checkCreditcard(order.getCreditCard()), "order.commit.error.creditcard.date");

		// Adding OrderItems
		orderItems = orderItemService.createByShoppingCart(shoppingCart, order);
		order.setOrderItems(orderItems);

		
		// Calculate amount
		amount = this.amountCalculate(orderItems);
		Assert.isTrue(amount == order.getAmount(), "order.commit.AmountChanged");
		
		order = this.save(order);
		
		//Saving OrderItems
		orderItems = orderItemService.createByShoppingCart(shoppingCart, order);
		orderItemService.save(orderItems);
	}
	
	
	/**
	 * Crea una Order desde ShoppingCart. NO USAR. Usar desde ShoppingCartService.createCheckOut.
	 */
	//req: 11.7
	public Order createFromShoppingCart(ShoppingCart shoppingCart, Consumer consumer){
		Assert.notNull(shoppingCart);
		Assert.isTrue(shoppingCart.getId() != 0);
		Assert.notNull(consumer);
		Assert.isTrue(consumer.getId() != 0);
		
		Order result;
		Collection<OrderItem> orderItems;
		double amount;
		
		result = this.create();
		
			// Adding OrderItems
		orderItems = orderItemService.createByShoppingCart(shoppingCart, result);
		result.setOrderItems(orderItems);
		
			// Calculate amount
		amount = this.amountCalculate(orderItems);
		result.setAmount(amount);
		
			// Adding Order to Consumer
		result.setConsumer(consumer);
		
		return result;
	}
	
	/**
	 * 	Genera un ticker cumpliendo el Pattern
	 */
	//req: 11.7
	private String tickerGenerate(){
		String result;
		String tickerNumber;
		String tickerAleatory;
		
		tickerNumber = calculaTickerNumber();
		tickerAleatory = calculaTickerAleatory();
		result = tickerNumber + "-" + tickerAleatory;
		
		if(!compareTicker(result)) {
			result = tickerGenerate();
		}
		
		return result;
	}
	
	private String calculaTickerNumber() {
		String result;
		
		if(ticker < 10) {
			result = "00000" + ticker.toString();
			ticker++;
		} else if(ticker < 100) {
			result = "0000" + ticker.toString();
			ticker++;
		} else if(ticker < 1000) {
			result = "000" + ticker.toString();
			ticker++;
		} else if(ticker < 10000) {
			result = "00" + ticker.toString();
			ticker++;
		} else if(ticker < 100000) {
			result = "0" + ticker.toString();
			ticker++;
		} else if(ticker < 1000000) {
			result = ticker.toString();
			ticker++;
		} else {
			ticker = 0;
			result = calculaTickerNumber();
		}
		
		return result;
	}
	
	private String calculaTickerAleatory() {
		String result;
		char[] conjunto = new char[4];

		char[] elementos={'0','1','2','3','4','5','6','7','8','9' ,'A',
				'B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T',
				'U','V','W','X','Y','Z'};
		
		for(int i=0;i<4;i++){
			int el = (int)(Math.random()*36);
			conjunto[i] = (char)elementos[el];
		}
		result = new String(conjunto);
		
		return result;
	}
	
	private Boolean compareTicker(String ticker) {
		Boolean result;
		Order order;
		
		order = orderRepository.findByTicker(ticker);
		result = (order == null);
		
		return result;
	}
	/**
	 * Calcula el precio de los orders
	 */
	// req: 11.7
	private double amountCalculate(Collection<OrderItem> orderItems){
		Assert.notEmpty(orderItems);
		
		double result;
		DecimalFormat df;
		
		result = 0.0;
		df = new DecimalFormat("#.00");
		
		for (OrderItem orderItem : orderItems) {
			result += orderItem.getPrice() * orderItem.getUnits();
		}

		result = Double.parseDouble(df.format(result));

		return result;
	}
	
	/**
	 * Marca como cancelada una order
	 */
	//req: 16.1
	public void cancelOrder(Order order){
		Assert.notNull(order);
		Assert.isTrue(order.getId() != 0);
		Assert.isTrue(order.getConsumer().equals(consumerService.findByPrincipal()), "Only the owner can cancel the order");
		Assert.isTrue(order.getCancelMoment() == null,"order.cancel.error.isCancelled");
		Clerk clerk;
		
		clerk = clerkService.findByOrder(order);
		
		Assert.isNull(clerk, "Can't remove a order when a clerk has assigned");
		
		order.setCancelMoment(new Date());
		this.save(order);
	}
	
	/**
	 * Asigna la order mas antigua a un clerk
	 */
	//ref: 18.3
	public Order assignToClerkAutomatically(Clerk clerk){
		Assert.notNull(clerk);
		
		Order result;
		
		result = this.findAllNotAssigned().iterator().next();
		
		this.assignToClerkManual(clerk, result);
		
		return result;
	}

	/**
	 * Asigna la order a un clerk
	 */
	//ref: 18.3
	public void assignToClerkManual(Clerk clerk, Order order){
		Assert.notNull(clerk);
		Assert.isTrue(clerk.getId() != 0);
		Assert.notNull(order);
		Assert.isTrue(order.getId() != 0);
		Assert.isNull(order.getClerk());
		
		order.setClerk(clerk);
		
		this.save(order);
	}
	
	/**
	 * Devuelven las orders no asignadas a ningún clerk siendo la primera la más antigua
	 */
	//ref: 18.3
	public Collection<Order> findAllNotAssigned(){
		Assert.isTrue(actorService.checkAuthority("ADMIN")||actorService.checkAuthority("CLERK"), "Only an admin or a clerk can list the orders");
		
		Collection<Order> result;
		
		//deben estar ordenadas siendo la primera la más antigua
		result = orderRepository.findAllNotAssigned();
		
		return result;
	}
	
	/**
	 * El ratio de orders canceladas durante el mes actual
	 */
	//ref: 17.6.5
	public double rateOrderCancelled(){
		Assert.isTrue(actorService.checkAuthority("ADMIN")||actorService.checkAuthority("CLERK"), "Only an admin or a clerk can list the orders");

		double result;
		try {
			result = orderRepository.rateOrderCancelled();
		} catch (AopInvocationException e) {
			result = 0;
		}
		
		return result;
	}
	/**
	 * Pone el deliveryMoment a la Order
	 * @param order
	 */
	public void completedOrder(Order order) {
		Assert.notNull(order);
		Assert.isTrue(order.getClerk().equals(clerkService.findByprincipal()), "Only the owner clerk can complete the order");
		
		order.setDeliveryMoment(new Date());
		
		this.save(order);
	}
	
	public Collection<Order> findAllByClerk(){
		Collection<Order> result;
		Clerk clerk;
		
		clerk = clerkService.findByprincipal();
		
		Assert.notNull(clerk);
		
		result = orderRepository.findAllByClerkId(clerk.getId());
		
		return result;
	}
	
	public Collection<Order> findAllByConsumer(){
		Collection<Order> result;
		Consumer consu;
		
		consu = consumerService.findByPrincipal();
		
		Assert.notNull(consu);
		
		result = orderRepository.findAllByConsumerId(consu.getId());
		
		return result;
	}
	
	/**
	 * Comprueba fecha creditCard
	 */
	private boolean checkCreditcard(CreditCard input){
		boolean result;
		int actMonth, actYear;
		Calendar act;
		
		result = true;
		act = Calendar.getInstance();
		
		actMonth = act.get(Calendar.MONTH);
		actYear = act.get(Calendar.YEAR);

		if (input.getExpirationYear() == actYear) {
			if(input.getExpirationMonth() < actMonth){
				result = false;
			}
		}else if (input.getExpirationYear() < actYear){
			result = false;
		}
		
		return result;
	}


	
}
