package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TaxRepository;
import domain.Category;
import domain.Item;
import domain.Tax;

@Service
@Transactional
public class TaxService {
	//Managed repository -----------------------------------------------------

	@Autowired
	private TaxRepository taxRepository;
	
	//Supporting services ----------------------------------------------------

	@Autowired
	private ItemService itemService;
	
	//Constructors -----------------------------------------------------------
	
	public TaxService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	/**
	 * Devuelve un tax preparado para ser modificado. Necesita usar save para que persista en la base de datos
	 */
	//req: 12.3
	public Tax create(){
		Tax result;
		
		result = new Tax();
		
		return result;
	}

	/**
	 * Guarda un tax creado o modificado
	 */
	//req: 12.3
	public void save(Tax tax){
		Assert.notNull(tax);
		
		taxRepository.save(tax);
	}

	/**
	 * Elimina un tax
	 */
	//req: 12.3
	public void delete(Tax tax){
		Assert.notNull(tax);
		Assert.isTrue(tax.getId() != 0);
		
		Collection<Item> itemsWithTax;
		
		itemsWithTax = itemService.findByTax(tax);
		
		Assert.isTrue(itemsWithTax.isEmpty(), "Only the tax without items (deleted or not) could be deleted");
		
		taxRepository.delete(tax);
	}
	
	public Collection<Tax> findAll(){
		Collection<Tax> result;
		
		result = taxRepository.findAll();
		
		return result;
	}
	
	//Other business methods -------------------------------------------------
	
	public Tax findByCategory(Category category){
		Assert.notNull(category);
		
		Tax result;
		
		result = taxRepository.findByCategoryId(category.getId());
		
		return result;
	}

	public Tax findOne(int taxId) {
		Tax result;
		
		result = taxRepository.findOne(taxId);
		Assert.notNull(result, "Comment " + taxId + " don't exist");
		
		return result;
	}
 
}
