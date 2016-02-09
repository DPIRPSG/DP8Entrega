package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Consumer;
import domain.Content;
import domain.Item;
import domain.ShoppingCart;

import repositories.ContentRepository;

@Service
@Transactional
public class ContentService {

 	//Managed repository -----------------------------------------------------

	@Autowired
	private ContentRepository contentRepository;
	
	//Supporting services ----------------------------------------------------

	@Autowired
	private ShoppingCartService shoppingCartService;
		
	@Autowired
	private ConsumerService consumerService;
	
	//Constructors -----------------------------------------------------------

	public ContentService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------

	public Content create(){
		Content result;
		
		result = new Content();
		result.setUnits(1);
		
		return result;
	}
	
	
	public void save(Content content){
		Assert.notNull(content);
		
		Consumer actualConsumer = content.getShoppingCart().getConsumer();
		
		Assert.isTrue(actualConsumer.equals(consumerService.findByPrincipal()), "Only the owner of the shopping cart can save the order");
	
		if(content.getUnits() == 0){
			this.deleteComplete(content);
		}else{
			contentRepository.save(content);
		}
	}
	
	/**
	 * Los contents se borran desde COntentService.deleteComplete
	 */
	private void delete(Content content){
		Assert.notNull(content);
		Assert.isTrue(content.getId() != 0);
		
		contentRepository.delete(content);
	}
	
	//Other business methods -------------------------------------------------
 
	//req: x
	private Content findByShoppingCartAndItem(ShoppingCart shoppingCart, Item item){
		Assert.notNull(shoppingCart);
		Assert.isTrue(shoppingCart.getId() != 0);
		Assert.notNull(item);
		Assert.isTrue(item.getId() != 0);
		Assert.isTrue(consumerService.findByPrincipal().equals(shoppingCart.getConsumer()), "The consumer is not the owner of the shoppingcart");
		
		Content result;
		
		result = contentRepository.findByShoppingCartIdAndItemId(shoppingCart.getId(), item.getId());
		
		return result;
	}
	
	public void emptyByShoppingCart(ShoppingCart shoppingCart){
		Assert.isTrue(consumerService.findByPrincipal().equals(shoppingCart.getConsumer()), "The consumer is not the owner of the shoppingcart");
		
		Collection<Content> contents;
		
		contents = this.findByShoppingCart(shoppingCart);
		
		for (Content content : contents) {
			this.delete(content);
		}
	}
	
	private Collection<Content> findByShoppingCart(ShoppingCart shoppingCart){
		Collection<Content> result;
		
		result = contentRepository.findByShoppingCartID(shoppingCart.getId());
		
		return result;
	}
	
	public Collection<Content> findByShoppingCart(int shoppingCartId){
		Collection<Content> result;
		ShoppingCart shoppingCart;
		
		shoppingCart = shoppingCartService.finOneByShoppingCartId(shoppingCartId);
		Assert.isTrue(shoppingCart.getConsumer().equals(consumerService.findByPrincipal()), "Only the owner of the shopping cart can list its items");
		
		result = contentRepository.findByShoppingCartID(shoppingCartId);
				
		return result;
	}
	
	/**
	 * NO USAR. Usar ShoppingCartService.consultItemQuantity
	 */
	//req: 11.2, 11.3
	public int quantityByShoppingCartAndItem(ShoppingCart shoppingCart, Item item){
		Assert.isTrue(consumerService.findByPrincipal().equals(shoppingCart.getConsumer()), "The consumer is not the owner of the shoppingcart");
		
		int result;
		Content content;
		
		content = this.findByShoppingCartAndItem(shoppingCart, item);
		
		if(content == null){
			result = 0;
		}else{
			result = content.getUnits();
		}
		
		return result;
	}
	
	/**
	 * NO USAR. Usar ShoppingCartService.changeItemQuantity
	 */
	//req: 11.3, 11.4, 11.5	
	public void updateQuantityByShoppingCartAndItem(ShoppingCart shoppingCart, Item item, int quantity){
		Assert.isTrue(consumerService.findByPrincipal().equals(shoppingCart.getConsumer()), "The consumer is not the owner of the shoppingcart");
		Assert.notNull(shoppingCart);
		Assert.isTrue(shoppingCart.getId() != 0);
		Assert.notNull(item);
		Assert.isTrue(item.getId() != 0);
		Assert.isTrue(quantity >= 0);
		
		Content content;
		
		content = this.findByShoppingCartAndItem(shoppingCart, item);
		
		if(content == null){
			content = this.create();
			content.setShoppingCart(shoppingCart);
			content.setItem(item);
		}
		
		content.setUnits(quantity);
		
		this.save(content);
	}
	
	/**
	 * NO USAR. Usar ShoppingCartService.addItem
	 */
	//req: 11.3	
	public void createByShoppingCartAndItem(ShoppingCart shoppingCart, Item item){
		Assert.isTrue(consumerService.findByPrincipal().equals(shoppingCart.getConsumer()), "The consumer is not the owner of the shoppingcart");

		int quantity;
		
		quantity = this.quantityByShoppingCartAndItem(shoppingCart, item);
		quantity++;
		
		this.updateQuantityByShoppingCartAndItem(shoppingCart, item, quantity);
	}
	/**
	 * Realiza el borrado completo del contenido
	 */
	//req: x
	public void deleteComplete(Content content){
		Collection<Content> contents;
		ShoppingCart shoppingCart;
		
		shoppingCart = content.getShoppingCart();		
		
		this.delete(content);

		contents = this.findByShoppingCart(shoppingCart);
		shoppingCart.setContents(contents);
		shoppingCartService.save(shoppingCart);
	}
	
	public Content findOneByContentId(int contentId){
		Content content;
		
		content = contentRepository.findOne(contentId);
		
		Consumer actualConsumer = content.getShoppingCart().getConsumer();
		
		Assert.isTrue(actualConsumer.equals(consumerService.findByPrincipal()), "Only the owner of the shopping cart can edit it");
		
		return content;
	}

	public Collection<Content> findAllByItemId(int itemId) {
		Assert.notNull(itemId);
		
		Collection<Content> result;
		
		result = contentRepository.findAllByItemId(itemId);
		
		return result;
	}
}
