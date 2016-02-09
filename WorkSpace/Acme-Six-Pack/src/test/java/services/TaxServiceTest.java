package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Tax;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml",
	"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class TaxServiceTest extends AbstractTest{

	// Service under test -------------------------
	@Autowired
	private TaxService taxService;
	
	// Test ---------------------------------------
	@Test
	public void testCreate1(){
		System.out.println("Requisito 12.3 - Manage a table of taxes, which includes creating, updating, and deleting them. Only taxes that have never been used in an item can actually be deleted.");
		System.out.println("TaxServiceTest - testCreate1 - StartPoint");
		
		Tax tax;
		Collection<Tax> all;
		
		authenticate("admin");
		
		all = taxService.findAll();
		
		System.out.println("Lista de taxes antes de la creación de otro");
		for(Tax x:all){
			System.out.println(x.getName() + ", " + x.getValue());
		}
		
		tax = taxService.create();
		tax.setName("Tax nueva");
		tax.setValue(14.0);
		taxService.save(tax);
		
		all = taxService.findAll();
		System.out.println("Lista de taxes después de la creación de otro");
		for(Tax x2:all){
			System.out.println(x2.getName() + ", " + x2.getValue());
		}
		
		authenticate(null);
		System.out.println("TaxServiceTest - testCreate1 - FinishPoint");
	}
	
	@Test
	public void testUpdate1(){
		System.out.println("Requisito 12.3 - Manage a table of taxes, which includes creating, updating, and deleting them. Only taxes that have never been used in an item can actually be deleted.");
		System.out.println("TaxServiceTest - testUpdate1 - StartPoint");
		
		Tax tax;
		Collection<Tax> all;
		
		authenticate("admin");
		
		all = taxService.findAll();
		tax = taxService.findAll().iterator().next();
		
		System.out.println("Lista de taxes antes de la modificación de uno");
		for(Tax x:all){
			System.out.println(x.getName() + ", " + x.getValue());
		}
		
		tax.setName("Tax modificada");
		tax.setValue(14.0);
		taxService.save(tax);
		
		all = taxService.findAll();
		System.out.println("Lista de taxes después de la modificación de otro");
		for(Tax x2:all){
			System.out.println(x2.getName() + ", " + x2.getValue());
		}
		
		authenticate(null);
		System.out.println("TaxServiceTest - testUpdate1 - FinishPoint");
	}
	
	@Test
	public void testDelete1(){
		System.out.println("Requisito 12.3 - Manage a table of taxes, which includes creating, updating, and deleting them. Only taxes that have never been used in an item can actually be deleted.");
		System.out.println("TaxServiceTest - testCreate1 - StartPoint");
		
		Tax tax;
		Collection<Tax> all;
		
		authenticate("admin");
		
		all = taxService.findAll();
		
		System.out.println("Lista de taxes antes de la creación de otro");
		for(Tax x:all){
			System.out.println(x.getName() + ", " + x.getValue() + ", " + x.getId());
		}
		
		System.out.println("Creamos una tax nueva para que no tenga nada asociado y se pueda borrar");
		
		tax = taxService.create();
		tax.setName("Tax nueva");
		tax.setValue(14.0);
		taxService.save(tax);
		
		all = taxService.findAll();
		
		System.out.println("Lista de taxes después de la creación de otro");
		for(Tax x:all){
			System.out.println(x.getName() + ", " + x.getValue() + ", " + x.getId());
		}
		
		for(Tax x:all){
			if(x.getName().equals(tax.getName())){
				tax = x;
			}
		}
		
		taxService.delete(tax);
		
		all = taxService.findAll();
		System.out.println("Lista de taxes después de la eliminación de la última");
		for(Tax x:all){
			System.out.println(x.getName() + ", " + x.getValue() + ", " + x.getId());
		}
		
		authenticate(null);
		System.out.println("TaxServiceTest - testCreate1 - FinishPoint");
	}
}
