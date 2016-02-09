package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Consumer;
import domain.Folder;
import domain.Message;
import domain.Order;
import domain.ShoppingCart;

import repositories.ConsumerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;

@Service
@Transactional
public class ConsumerService {
	//Managed repository -----------------------------------------------------
	
	@Autowired
	private ConsumerRepository consumerRepository;
	
	//Supporting services ----------------------------------------------------

	@Autowired
	private FolderService folderService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	//Constructors -----------------------------------------------------------

	public ConsumerService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	/** Devuelve consumer preparado para ser modificado. Necesita usar save para que persista en la base de datos
	 * 
	 */
	// req: 10.1
	public Consumer create(){
		Consumer result;
		UserAccount userAccount;

		result = new Consumer();
		
		userAccount = userAccountService.create("CONSUMER");
		result.setUserAccount(userAccount);
		
		return result;
	}
	
	/**
	 * Almacena en la base de datos el cambio
	 */
	// req: 10.1
	public void save(Consumer consumer){
		Assert.notNull(consumer);
		
		Consumer modify;
		
		boolean result = true;
		for(Authority a: consumer.getUserAccount().getAuthorities()){
			if(!a.getAuthority().equals("CONSUMER")){
				result = false;
				break;
			}
		}
		Assert.isTrue(result, "A consumer can only be a authority.consumer");
		
		if(consumer.getId() == 0){
			Collection<Folder> folders;
			Collection<Message> sent;
			Collection<Message> received;
			Collection<Order> orders;
			UserAccount auth;
			ShoppingCart shoppingCart;
			
			//Encoding password
			auth = consumer.getUserAccount();
			auth = userAccountService.modifyPassword(auth);
			consumer.setUserAccount(auth);
			
			// Initialize folders
			folders = folderService.initializeSystemFolder(consumer);
			consumer.setFolders(folders);
			
			sent = new ArrayList<Message>();
			received = new ArrayList<Message>();
			consumer.setSent(sent);
			consumer.setReceived(received);
			
			//Initialize orders			
			orders = new ArrayList<Order>();
			consumer.setOrders(orders);
			
			//Initialize shoppingCart
			shoppingCart = shoppingCartService.create(consumer);
			consumer.setShoppingCart(shoppingCart);
			
		}
		modify = consumerRepository.save(consumer);
		
		if(consumer.getId() == 0){
			Collection<Folder> folders;

			folders = folderService.initializeSystemFolder(modify);
			folderService.save(folders);
		}
		
	}
	
	/**
	 * Lista los consumers registrados
	 */
	// req: 12.5
	public Collection<Consumer> findAll(){
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can list consumers");
		
		Collection<Consumer> result;
		
		result = consumerRepository.findAll();
		
		return result;
	}

	//Other business methods -------------------------------------------------

	/**
	 * Devuelve el consumer que está realizando la operación
	 */
	//req: x
	public Consumer findByPrincipal(){
		Consumer result;
		UserAccount userAccount;
		
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = consumerRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);
		
		return result;
	}
	
	/**
	 * Lista el consumers con más orders. En caso de igualdad devuelve varios. 
	 * Cuenta las orders canceladas y las no canceladas
	 */
	//req: 12.7.1
	public Collection<Consumer> findConsumerMoreOrders(){
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can list consumers");
		Collection<Consumer> result;
		
		result = consumerRepository.findConsumerMoreOrders();
		
		return result;
	}

	/**
	 * Lista el consumers que ha gastado más dinero. En caso de igualdad devuelve varios. 
	 * Solo considera las orders no canceladas
	 */
	//req: 12.7.2
	public Collection<Consumer> findConsumerSpentMoreMoney(){
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can list consumers");
		Collection<Consumer> result;
		
		result = consumerRepository.findConsumerSpentMoreMoney();
		
		return result;
	}

	/**
	 * Lista el/los consumer con más order canceladas
	 */
	//req: 17.6.3
	public Collection<Consumer> findConsumerMoreOrdersCancelled(){
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can list consumers");
		Collection<Consumer> result;
		
		result = consumerRepository.findConsumerMoreOrdersCancelled();
		
		return result;
	}
	
	/**
	 * Lista el/los consumer con menos order canceladas
	 */
	//req: 17.6.4
	public Collection<Consumer> findConsumerLessOrdersCancelled(){
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can list consumers");
		Collection<Consumer> result;
		
		result = consumerRepository.findConsumerLessOrdersCancelled();
		
		return result;
	}

}
