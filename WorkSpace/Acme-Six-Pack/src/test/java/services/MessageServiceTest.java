package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Actor;
import domain.Consumer;
import domain.Folder;
import domain.Message;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml",
	"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)

public class MessageServiceTest extends AbstractTest {

	// Service under test -------------------------
	@Autowired
	private ConsumerService consumerService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private FolderService folderService;
		
	// Test ---------------------------------------
	@Test
	public void testManageFoldersAndMessages1(){
		System.out.println("Requisito 24.2 - Manage his or her folders and messages.");
		System.out.println("MessageServiceTest - testManageFoldersAndMessages1 - StartPoint");
		
		Message message;
		Collection<Folder> folders;
		Collection<Message> messages;
		Collection<Actor> recipients;
		Consumer consumer;
		Date moment;
		
		authenticate("consumer1");
		consumer = consumerService.findByPrincipal();

		message = messageService.create();
		messages = new ArrayList<Message>();
		recipients = new ArrayList<Actor>();
		moment = new Date();
		
		System.out.println("Consumer sobre el que se trabaja");
		System.out.println(consumer.getName());
		folders = consumer.getFolders();
		recipients.add(consumer);
		
		System.out.println("Lista de folders del consumer");
		for(Folder f:folders){
			messages.addAll(f.getMessages());
			System.out.println(f.getName());
		}
		System.out.println("Lista de mensajes del consumer antes de añadir uno nuevo");
		for(Message m : messages) {
			System.out.println(m.getSubject() + ": " + m.getBody());
		}
		
		message.setSubject("Prueba");
		message.setBody("Probamos a crear un mensaje");
		message.setMoment(moment);
		message.setSender(consumer);
		message.setRecipients(recipients);
		
		messageService.firstSave(message);
		
		//messageService.addMessageToFolders(message);
		
		Actor actor;
		
		actor = actorService.findByPrincipal();
		System.out.println("Mensajes enviados: ");
		for (Folder f : actor.getFolders()) {
			System.out.println("Folder: "+f.getName());
			for (Message m : f.getMessages()) {
				System.out.println(m.getSubject() + ": " + m.getBody());
			}
		}
		System.out.println();
		
		authenticate(null);
		
		System.out.println("MessageServiceTest - testManageFoldersAndMessages1 - FinishPoint");
	}
	
	@Test
	public void testManageFoldersAndMessages2(){
		System.out.println("Requisito 24.2 - Manage his or her folders and messages.");
		System.out.println("MessageServiceTest - testManageFoldersAndMessages2 - StartPoint");
		
		Message message;
		Collection<Folder> folders;
		Collection<Message> messages;
		Consumer consumer;
		Folder folderToEliminate;
		
		authenticate("consumer1");
		consumer = consumerService.findByPrincipal();

		messages = new ArrayList<Message>();
		message = null;
		folderToEliminate = null;
		
		System.out.println("Consumer sobre el que se trabaja");
		System.out.println(consumer.getName());
		folders = consumer.getFolders();

		
		System.out.println("Lista de folders del consumer");
		for(Folder f:folders){
			messages.addAll(f.getMessages());
			for(Message m:f.getMessages()){
				System.out.println(f.getName() + " -> "+ m.getSubject() + ": " + m.getBody());
				message = m;
				folderToEliminate = f;
				
			}
		}

		System.out.println("Antes de eliminar");
		
		System.out.println("Lo debe mover a TrashBox");
		
		messageService.deleteMessageFromFolder(message, folderToEliminate);
		
		System.out.println("Después de eliminar");
		
		Actor actor;
		
		actor = actorService.findByPrincipal();
		
		System.out.println("Lista de folders del consumer");
		for(Folder f:actor.getFolders()){
			for(Message m:f.getMessages()){
				System.out.println(f.getName() + " -> " + m.getSubject() + ": " + m.getBody());
				message = m;
				folderToEliminate = f;
				
			}
		}
		System.out.println();
		
		authenticate(null);
		
		System.out.println("MessageServiceTest - testManageFoldersAndMessages2 - FinishPoint");
	}
	
	@Test
	public void testManageFoldersAndMessages3(){
		System.out.println("Requisito 24.2 - Manage his or her folders and messages.");
		System.out.println("MessageServiceTest - testManageFoldersAndMessages3 - StartPoint");
		
		Message message;
		Collection<Folder> folders;
		Collection<Message> messages;
		Consumer consumer;
		Folder folderToEliminate;
		
		authenticate("consumer2");
		consumer = consumerService.findByPrincipal();

		messages = new ArrayList<Message>();
		message = null;
		folderToEliminate = null;
		
		System.out.println("Consumer sobre el que se trabaja");
		System.out.println(consumer.getName());
		folders = consumer.getFolders();

		
		System.out.println("Lista de folders del consumer");
		for(Folder f:folders){
			messages.addAll(f.getMessages());
			for(Message m:f.getMessages()){
				System.out.println(f.getName() + " -> "+ m.getSubject() + ": " + m.getBody());
				message = m;
				folderToEliminate = f;
				
			}
		}

		System.out.println("Antes de eliminar");
		
		System.out.println("Lo debe borrar solo de Importantes");
		
		messageService.deleteMessageFromFolder(message, folderToEliminate);
		
		System.out.println("Después de eliminar");
		
		Actor actor;
		
		actor = actorService.findByPrincipal();
		
		System.out.println("Lista de folders del consumer");
		for(Folder f:actor.getFolders()){
			for(Message m:f.getMessages()){
				System.out.println(f.getName() + " -> " + m.getSubject() + ": " + m.getBody());
				message = m;
				folderToEliminate = f;
				
			}
		}
		System.out.println();
		
		authenticate(null);
		
		System.out.println("MessageServiceTest - testManageFoldersAndMessages3 - FinishPoint");
	}
	
	@Test
	public void testManageFoldersAndMessages4(){
		System.out.println("Requisito 24.2 - Manage his or her folders and messages.");
		System.out.println("MessageServiceTest - testManageFoldersAndMessages4 - StartPoint");
		
		Message message;
		Collection<Folder> folders;
		Collection<Message> messages;
		Consumer consumer;
		Folder folderToEliminate;
		
		authenticate("consumer3");
		consumer = consumerService.findByPrincipal();
		messages = new ArrayList<Message>();
		message = null;
		folderToEliminate = null;
		
		System.out.println("Consumer sobre el que se trabaja");
		System.out.println(consumer.getName());
		folders = consumer.getFolders();

		
		System.out.println("Lista de folders del consumer");
		for(Folder f:folders){
			messages.addAll(f.getMessages());
			for(Message m:f.getMessages()){
				System.out.println(f.getName() + " -> "+ m.getSubject() + ": " + m.getBody());
				message = m;
				folderToEliminate = f;
				
			}
		}

		System.out.println("Antes de eliminar");
		
		System.out.println("Lo debe borrar de TrashBox");
		
		messageService.deleteMessageFromFolder(message, folderToEliminate);
		
		System.out.println("Después de eliminar");
		
		Actor actor;
		
		actor = actorService.findByPrincipal();
		
		System.out.println("Lista de folders del consumer");
		for(Folder f:actor.getFolders()){
			for(Message m:f.getMessages()){
				System.out.println(f.getName() + " -> " + m.getSubject() + ": " + m.getBody());
				message = m;
				folderToEliminate = f;
				
			}
		}
		System.out.println();
		
		authenticate(null);
		
		System.out.println("MessageServiceTest - testManageFoldersAndMessages4 - FinishPoint");
	}
	
	@Test
	public void testManageFoldersAndMessages5(){
		System.out.println("Requisito 24.2 - Manage his or her folders and messages.");
		System.out.println("MessageServiceTest - testManageFoldersAndMessages5 - StartPoint");
		
		Message message;
		Collection<Folder> folders;
		Collection<Message> messages;
		Consumer consumer;
		Folder originFolder;
		Folder destinationFolder;
		
		authenticate("consumer4");
		consumer = consumerService.findByPrincipal();
		messages = new ArrayList<Message>();
		message = null;
		originFolder = null;
		destinationFolder = null;
		
		System.out.println("Consumer sobre el que se trabaja");
		System.out.println(consumer.getName());
		folders = consumer.getFolders();

		
		System.out.println("Lista de folders del consumer");
		for(Folder f:folders){
			messages.addAll(f.getMessages());
			if(f.getName().equals("InBox")) {
				originFolder = f;
			} else if(f.getName().equals("Importantes")) {
				destinationFolder = f;
			}
			for(Message m:f.getMessages()){
				System.out.println(f.getName() + " -> "+ m.getSubject() + ": " + m.getBody());
				message = m;				
			}
		}

		System.out.println("Antes de mover de carpeta");
		
		System.out.println("Lo debe mover de InBox a Importantes");
		
		folderService.moveMessage(originFolder, destinationFolder, message);
		
		System.out.println("Después de mover de carpeta");
		
		Actor actor;
		
		actor = actorService.findByPrincipal();
		
		System.out.println("Lista de folders del consumer");
		for(Folder f:actor.getFolders()){
			for(Message m:f.getMessages()){
				System.out.println(f.getName() + " -> " + m.getSubject() + ": " + m.getBody());
			}
		}
		System.out.println();
		
		authenticate(null);
		
		System.out.println("MessageServiceTest - testManageFoldersAndMessages5 - FinishPoint");
	}
}
