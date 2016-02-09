package services;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


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
public class FolderServiceTest extends AbstractTest{

	// Service under test -------------------------
	@Autowired
	private FolderService folderService;
	@Autowired
	private ConsumerService consumerService;
	@Autowired
	private MessageService messageService;
	
	// Test ---------------------------------------
	@Test
	public void testListFolders1(){
		System.out.println("Requisito 24.1 - List his or her message folders, and display the messages that they contain.");
		System.out.println("FolderServiceTest - testListFolders1 - StartPoint");
		
		Consumer consumer;
		Collection<Folder> folders;
		
		authenticate("admin");
		consumer = consumerService.findAll().iterator().next();
		System.out.println("Consumer sobre el que se trabaja");
		System.out.println(consumer.getName());
		
		authenticate(consumer.getUserAccount().getUsername());
		folders = consumer.getFolders();
		System.out.println("Lista de folders del consumer y los mensajes de cada folder");
		for(Folder f:folders){
			System.out.println(f.getName());
			for(Message m:f.getMessages()){
				System.out.println(m.getSubject() + ": " + m.getBody());
			}	
		}
		
		authenticate(null);
		
		System.out.println("FolderServiceTest - testListFolders1 - FinishPoint");
	}
	
	@Test
	public void testManageFoldersAndMessages1(){
		System.out.println("Requisito 24.2 - Manage his or her folders and messages.");
		System.out.println("FolderServiceTest - testManageFoldersAndMessages1 - StartPoint");
		
		Folder folder;
		Collection<Folder> folders;
		Collection<Message> messages;
		Consumer consumer;
		
		authenticate("admin");
		consumer = consumerService.findAll().iterator().next();
		authenticate(consumer.getUserAccount().getUsername());
		folder = folderService.create();
		messages = new ArrayList<Message>();
		
		System.out.println("Consumer sobre el que se trabaja");
		System.out.println(consumer.getName());
		folders = consumer.getFolders();
		
		System.out.println("Lista de folders del consumer antes de añadir uno nuevo");
		for(Folder f:folders){
			System.out.println(f.getName());
		}
		folder.setName("Folder nueva");
		folder.setIsSystem(false);
		folder.setActor(consumer);
		folder.setMessages(messages);
		
		folders.add(folder);
		
		System.out.println("Lista de folders del consumer después de añadir una nueva");
		consumer.setFolders(folders);
		folders = consumer.getFolders();
		for(Folder f:folders){
			System.out.println(f.getName());
		}
		
		authenticate(null);
		
		System.out.println("FolderServiceTest - testManageFoldersAndMessages1 - FinishPoint");
	}
	
	@Test
	public void testManageFoldersAndMessages2(){
		System.out.println("Requisito 24.2 - Manage his or her folders and messages.");
		System.out.println("FolderServiceTest - testManageFoldersAndMessages2 - StartPoint");
		
		Folder folder;
		Collection<Folder> folders;
		Collection<Message> messages;
		Consumer consumer;
		
		authenticate("admin");
		consumer = consumerService.findAll().iterator().next();
		authenticate(consumer.getUserAccount().getUsername());
		folder = folderService.create();
		messages = new ArrayList<Message>();
		
		System.out.println("Consumer sobre el que se trabaja");
		System.out.println(consumer.getName());
		folders = consumer.getFolders();
		
		System.out.println("Lista de folders del consumer antes de añadir uno nuevo");
		for(Folder f:folders){
			System.out.println(f.getName());
		}
		folder.setName("Folder nueva");
		folder.setIsSystem(false);
		folder.setActor(consumer);
		folder.setMessages(messages);
		
		folders.add(folder);
		
		System.out.println("Lista de folders del consumer después de añadir una nueva");
		consumer.setFolders(folders);
		folders = consumer.getFolders();
		for(Folder f:folders){
			System.out.println(f.getName());
		}
		
		for(Folder f:folders){
			if(f.getIsSystem()==false){
				folders.remove(f);
				break;
			}
		}
		
		consumer.setFolders(folders);
		folders = consumer.getFolders();
		
		System.out.println("Lista de folders del consumer tras la eliminación de una que no es del sistema.");
		for(Folder f:folders){
			System.out.println(f.getName());
		}
		
		authenticate(null);
		
		System.out.println("FolderServiceTest - testManageFoldersAndMessages2 - FinishPoint");
	}
	
	@Test
	public void testManageFoldersAndMessages3(){
		System.out.println("Requisito 24.2 - Manage his or her folders and messages.");
		System.out.println("FolderServiceTest - testManageFoldersAndMessages3 - StartPoint");
		
		Folder folder;
		Collection<Folder> folders;
		Collection<Message> messages;
		Consumer consumer;
		
		authenticate("admin");
		consumer = consumerService.findAll().iterator().next();
		authenticate(consumer.getUserAccount().getUsername());
		folder = folderService.create();
		messages = new ArrayList<Message>();
		
		System.out.println("Consumer sobre el que se trabaja");
		System.out.println(consumer.getName());
		folders = consumer.getFolders();
		
		System.out.println("Lista de folders del consumer antes de añadir uno nuevo");
		for(Folder f:folders){
			System.out.println(f.getName());
		}
		folder.setName("Folder nueva");
		folder.setIsSystem(false);
		folder.setActor(consumer);
		folder.setMessages(messages);
		
		folders.add(folder);
		
		System.out.println("Lista de folders del consumer después de añadir una nueva");
		consumer.setFolders(folders);
		folders = consumer.getFolders();
		for(Folder f:folders){
			System.out.println(f.getName());
		}
		
		for(Folder f:folders){
			if(f.getIsSystem()==false){
				f.setName("Folder actualizada");
				break;
			}
		}
		
		consumer.setFolders(folders);
		folders = consumer.getFolders();
		
		System.out.println("Lista de folders del consumer tras la modificación de una que no es del sistema.");
		for(Folder f:folders){
			System.out.println(f.getName());
		}
		
		authenticate(null);
		
		System.out.println("FolderServiceTest - testManageFoldersAndMessages3 - FinishPoint");
	}
	
	@Test
	public void testManageFoldersAndMessages4(){
		System.out.println("Requisito 24.2 - Manage his or her folders and messages.");
		System.out.println("FolderServiceTest - testManageFoldersAndMessages4 - StartPoint");
		
		Message message;
		Consumer consumer;
		Collection<Message> received;
		Folder folder;
		
		authenticate("admin");
		message = null;
		folder = null;
		consumer = consumerService.findAll().iterator().next();
		
		authenticate(consumer.getUserAccount().getUsername());
		received = new ArrayList<Message>();
		received = consumer.getReceived();
		
		System.out.println("Mensajes antes:");
		for(Message m:received){
			System.out.println(m.getSubject() + ": " + m.getBody());
			message = m;
		}
		
		for(Folder f:consumer.getFolders()){
			if(f.getMessages().contains(message)){
				folder = f;
			}
		}
		
		messageService.deleteMessageFromFolder(message, folder);
		
		System.out.println("Mensajes después:");
		received = consumer.getReceived();
		for(Message m:received){
			System.out.println(m.getSubject() + ": " + m.getBody());
		}
		
		authenticate(null);
		
		System.out.println("FolderServiceTest - testManageFoldersAndMessages4 - FinishPoint");
	}
}