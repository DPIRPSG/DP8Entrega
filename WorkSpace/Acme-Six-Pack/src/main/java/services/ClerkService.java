package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Clerk;
import domain.Folder;
import domain.Message;
import domain.Order;

import repositories.ClerkRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;

@Service
@Transactional
public class ClerkService {
	//Managed repository -----------------------------------------------------

	@Autowired
	private ClerkRepository clerkRepository;
	
	//Supporting services ----------------------------------------------------

	@Autowired
	private FolderService folderService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private UserAccountService userAccountService;
	
	//Constructors -----------------------------------------------------------

	public ClerkService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	/** Devuelve create preparado para ser modificado. Necesita usar save para que persista en la base de datos
	 * 
	 */	
	//req: 17.1
	public Clerk create(){
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can create clerk");		
		
		Clerk result;
		UserAccount userAccount;
		
		result = new Clerk();
		
		userAccount = userAccountService.create("CLERK");
		result.setUserAccount(userAccount);
		
		return result;
	}

	/** 
	 * Guarda un clerk creado o modificado
	 */	
	//req: 17.1
	public void save(Clerk clerk){
		Assert.notNull(clerk);
		Assert.isTrue(actorService.checkAuthority("ADMIN") || actorService.checkAuthority("CLERK"), "Only an admin or a clerk can save clerks");

		boolean result = true;
		for(Authority a: clerk.getUserAccount().getAuthorities()){
			if(!a.getAuthority().equals("CLERK")){
				result = false;
				break;
			}
		}
		Assert.isTrue(result, "A clerk can only be a authority.clerk");
		
		Clerk modify;
		
		if(clerk.getId() == 0){
			Collection<Folder> folders;
			Collection<Message> sent;
			Collection<Message> received;
			Collection<Order> orders;
			UserAccount auth;
			
			//Encoding password
			auth = clerk.getUserAccount();
			auth = userAccountService.modifyPassword(auth);
			clerk.setUserAccount(auth);
			
			// Initialize folders
			folders = folderService.initializeSystemFolder(clerk);
			clerk.setFolders(folders);
			
			sent = new ArrayList<Message>();
			received = new ArrayList<Message>();
			clerk.setSent(sent);
			clerk.setReceived(received);
			
			//Initialize orders			
			orders = new ArrayList<Order>();
			clerk.setOrders(orders);			
		}
		
		modify = clerkRepository.save(clerk);
		
		if(clerk.getId() == 0){
			Collection<Folder> folders;

			folders = folderService.initializeSystemFolder(modify);
			folderService.save(folders);
		}
	}
	
	/**
	 * Lista todos los clerks
	 */
	//req: test
	public Collection<Clerk> findAll(){
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can list the clerks");
		
		Collection<Clerk> result;
		
		result = clerkRepository.findAll();
		
		return result;
	}

	//Other business methods -------------------------------------------------

	/**
	 * Encuentra un clerk dada la order
	 */
	//req: 16.1
	public Clerk findByOrder(Order order){
		Assert.notNull(order);
		
		Clerk result;
		
		result = clerkRepository.findByOrderId(order.getId());
		
		return result;
	}
	
	/**
	 * Encuentra el/los clerk con más order
	 */
	//req: 17.6.1
	public Collection<Clerk> findClerkServedMoreOrders(){
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can list the clerks");
		
		Collection<Clerk> result;
		
		result = clerkRepository.findClerkServedMoreOrders();
		
		return result;
	}
	
	/**
	 * Encuentra el/los clerk con menos order
	 */
	//req: 17.6.2
	public Collection<Clerk> findClerkServedLessOrders(){
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can list the clerks");
		
		Collection<Clerk> result;
		
		result = clerkRepository.findClerkServedLessOrders();
		
		return result;
	}
	
	/**
	 * Devuelve el clerk que está realizando la operación
	 */
	//req: x
	public Clerk findByprincipal(){
		Clerk result;
		UserAccount userAccount;
		
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = clerkRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);
		
		return result;
	}

}
