package services;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Comment;
import domain.Gym;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml",
	"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class GymServiceTest extends AbstractTest{

	// Service under test -------------------------
	@Autowired
	private GymService gymService;
	
	// Test ---------------------------------------
	
	@Test
	public void testCreate1(){		
		Gym gym;
		Collection<Gym> gyms;
		int gymId;
		
		authenticate("admin");
		
		gyms = gymService.findAll();
		System.out.println(gyms);
		
		gymId = 33;
		
		System.out.println("Empezamos a crear el Gym");
		
		gym = gymService.create();
		
		gym.setName("Gym de Chiclana");
		gym.setDescription("Gym de Chiclana");
		gym.setPostalAddress("Chiclana");
		gym.setPhone("666333123");
		gym.setFee(10);
		
		gymService.save(gym);
		
		System.out.println("Ya se ha creado?");
		gyms = gymService.findAll();
		System.out.println(gyms);
		
		authenticate(null);
	}
	
	@Test
	public void testDelete1(){		
		Gym gym;
		Collection<Gym> gyms;
		int gymId;
		
		authenticate("admin");
		
		gyms = gymService.findAll();
		System.out.println(gyms);
		
		gymId = 33;
		System.out.println("Pretendemos eliminar el gym con el id 33, ¿Existe?");
		gym = gymService.findOne(gymId);
		System.out.println("Nombre: " + gym.getName());
		System.out.println("Id: " + gym.getId());
		gymService.delete(gym);
		
		System.out.println("Ya se ha pulsado en Delete");
		gyms = gymService.findAll();
		System.out.println(gyms);
		
		authenticate(null);
	}
}