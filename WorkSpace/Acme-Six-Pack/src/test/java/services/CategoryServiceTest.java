package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Category;
import domain.Tax;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml",
	"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class CategoryServiceTest extends AbstractTest{

	// Service under test -------------------------
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private TaxService taxService;
	
	// Test ---------------------------------------
	@Test
	public void testCreate1(){
		System.out.println("Requisito 12.4 - Manage the catalogue of categories, which includes creating them, listing them, modifying them, and deleting them.");
		System.out.println("CategoryServiceTest - testCreate1 - StartPoint");
		
		Category category;
		Collection<Category> all;
		Tax tax;
		
		authenticate("admin");
		
		all = categoryService.findAll();
		
		System.out.println("Lista de categories antes de crear nueva");
		for(Category c:all){
			System.out.println(c.getName());
		}
		
		category = categoryService.create();
		category.setName("Categoría nueva");
		category.setDescription("Esta es la descripción de la nueva categoría");
		tax = taxService.findAll().iterator().next();
		category.setTax(tax);
		categoryService.save(category);
		
		all = categoryService.findAll();
		
		System.out.println("Lista de categories después de crear nueva");
		for(Category c:all){
			System.out.println(c.getName());
		}
		
		authenticate(null);
		
		System.out.println("CategoryServiceTest - testCreate1 - FinishPoint");
	}
	
	@Test
	public void testList1(){
		System.out.println("Requisito 12.4 - Manage the catalogue of categories, which includes creating them, listing them, modifying them, and deleting them.");
		System.out.println("CategoryServiceTest - testList1 - StartPoint");
		
		Collection<Category> all;
		
		authenticate("admin");
		
		all = categoryService.findAll();
		
		System.out.println("Lista de categories.");
		for(Category c:all){
			System.out.println(c.getName());
		}
		
		authenticate(null);
		
		System.out.println("CategoryServiceTest - testList1 - FinishPoint");
	}
	
	@Test
	public void testModify1(){
		System.out.println("Requisito 12.4 - Manage the catalogue of categories, which includes creating them, listing them, modifying them, and deleting them.");
		System.out.println("CategoryServiceTest - testUpdate1 - StartPoint");
		
		Category category;
		Collection<Category> all;
		
		authenticate("admin");
		
		all = categoryService.findAll();
		category = categoryService.findAll().iterator().next();
		
		System.out.println("Lista de categories antes de modificar una");
		for(Category c:all){
			System.out.println(c.getName());
		}
		
		category.setName("Category modificada");
		category.setDescription("Descripción modificada");
		categoryService.save(category);
		
		all = categoryService.findAll();
		
		System.out.println("Lista de categories después de modificar una");
		for(Category c:all){
			System.out.println(c.getName());
		}
		
		authenticate(null);
		
		System.out.println("CategoryServiceTest - testUpdate1 - FinishPoint");
	}
	
	@Test
	public void testDelete1(){
		System.out.println("Requisito 12.4 - Manage the catalogue of categories, which includes creating them, listing them, modifying them, and deleting them.");
		System.out.println("CategoryServiceTest - testDelete1 - StartPoint");
		
		Category category;
		Collection<Category> all;
		Tax tax;
		
		authenticate("admin");
		
		
		all = categoryService.findAll();
		
		System.out.println("Lista de categories antes de crear una con items vacío para poder borrarla");
		for(Category c:all){
			System.out.println(c.getName());
		}
		
		category = categoryService.create();
		category.setName("Categoría nueva");
		category.setDescription("Esta es la descripción de la nueva categoría");
		tax = taxService.findAll().iterator().next();
		category.setTax(tax);
		categoryService.save(category);
		
		all = categoryService.findAll();
		
		System.out.println("Lista de categories antes de borrar una");
		for(Category c:all){
			System.out.println(c.getName());
		}
		
		for(Category c:all){
			if(c.getName().equals(category.getName())){
				category = c;
			}
		}
		
		categoryService.delete(category);
		
		all = categoryService.findAll();
		
		System.out.println("Lista de categories después de borra una");
		for(Category c:all){
			System.out.println(c.getName());
		}
		
		authenticate(null);
		
		System.out.println("CategoryServiceTest - testDelete1 - FinishPoint");
	}
}
