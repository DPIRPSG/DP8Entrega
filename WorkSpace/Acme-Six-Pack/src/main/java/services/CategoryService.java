package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Category;
import domain.Item;

import repositories.CategoryRepository;

@Service
@Transactional
public class CategoryService {
	//Managed repository -----------------------------------------------------

	@Autowired
	private CategoryRepository categoryRepository;
	
	//Supporting services ----------------------------------------------------
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ActorService actorService;
	
	//Constructors -----------------------------------------------------------
	
	public CategoryService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------

	/** Devuelve Category preparada para ser modificada. Necesita usar save para que persista en la base de datos
	 * 
	 */	
	//req: 12.4
	public Category create(){
		Category result;
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can create categories");
		
		result = new Category();
		
		return result;
	}
	
	/**
	 * Guarda una Category creada o modificada
	 */
	//req: 12.4
	public void save(Category category){
		Assert.notNull(category);
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can save categories");

		
		categoryRepository.save(category);
	}

	/**
	 * Elimina una category
	 */
	//req: 12.4
	public void delete(Category category){
		Assert.notNull(category);
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can delete categories");
		
		Collection<Item> items;
		
		items = itemService.findAllNotDeletedByCategory(category);
		
		Assert.isTrue(items.isEmpty(), "Only the categories without items (deleted or not) could be deleted");
		
		categoryRepository.delete(category);
	}
	
	/**
	 * Lista todas las categories
	 */
	//req: 12.4
	public Collection<Category> findAll(){
		Collection<Category> result;
		
		result = categoryRepository.findAll();
		
		return result;
	}

	//Other business methods -------------------------------------------------
 
	public Category findByItem(Item item){
		Category result;
		
		result = categoryRepository.findByItemId(item.getId());
		
		return result;
	}

	public Category findOne(int categoryId) {
		Category result;

		result = categoryRepository.findOne(categoryId);

		return result;
	}
	
	
}
