package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Folder;
import domain.Message;

import repositories.MessageRepository;

@Service
@Transactional 
public class MessageService {
 	//Managed repository -----------------------------------------------------

	@Autowired
	private MessageRepository messageRepository;
	
	//Supporting services ----------------------------------------------------

	@Autowired
	private FolderService folderService;
	
	@Autowired
	private ActorService actorService;
	
	//Constructors -----------------------------------------------------------

	public MessageService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	/** 
	 * Devuelve Message preparado para ser modificado. Necesita usar save para que persista en la base de datos
	 */	
	//req: 24.2
	public Message create(){
		Message result;
		Collection<Folder> folders;
		Collection<Actor> recipients;
		
		folders = new ArrayList<Folder>();
		recipients = new ArrayList<Actor>();
		result = new Message();
			
		result.setFolders(folders);
		result.setRecipients(recipients);
		result.setSender(actorService.findByPrincipal());
		result.setMoment(new Date());
		
		return result;	
	}
	
	/**
	 * Guarda un message creado o modificado
	 */
	//req: 24.2
	private Message save(Message message){
		Assert.notNull(message);
		Assert.isTrue(message.getSender().equals(actorService.findByPrincipal()), "Only the sender can save the message");
		
		message.setMoment(new Date());
		
		Message result;
		
		result = messageRepository.save(message);
		
		return result;
	}
	
	public Message findOne(int messageId){
		Message result;
		
		result = messageRepository.findOne(messageId);
		
		Assert.notNull(result, "message.findOne.UnknownID");
		
		checkActor(result);		
		
		return result;
	}

	//Other business methods -------------------------------------------------
	
	/**
	 * Guarda la primera vez
	 */
	public Message firstSave(Message message){
		Assert.notNull(message);
		
		Message result;
		
		result = this.save(message);
		
		this.addMessageToFolderFirst(result);
		
		return result;
	}
	
	/**
	 * Añade a las respectivas carpetas la primera vez que un mensaje es creado
	 */
	private void addMessageToFolderFirst(Message message){
		
		for (Folder f:message.getSender().getFolders()){
			if (f.getName().equals("OutBox") && f.getIsSystem()){
				folderService.addMessage(f, message);
			}
		}
		
		for (Actor recipient: message.getRecipients()){
			for (Folder f:recipient.getFolders()){
				if (f.getName().equals("InBox") && f.getIsSystem()){
					folderService.addMessage(f, message);
				}
			}
		}
	}
	
	
	/**
	 * Devuelve todos los mensajes contenidos en una determinada carpeta
	 */
	//req: 24.1
	public Collection<Message> findAllByFolder(Folder folder){
		Assert.notNull(folder);
		Assert.isTrue(folder.getActor().equals(actorService.findByPrincipal()), "Only the owner of the folder can display them");
		
		Collection<Message> result;
		
		result = messageRepository.findAllByFolderId(folder.getId());
		
		return result;
	}
	
	/**
	 * Borra un mensaje de una carpeta
	 */
	//req: 24.2
	public void deleteMessageFromFolder(Message message, Folder folder){
		Assert.notNull(message);
		Assert.isTrue(message.getId() != 0);
		Assert.notNull(folder);
		Assert.isTrue(folder.getId() != 0);
		
		Assert.isTrue(folder.getActor().equals(actorService.findByPrincipal()), "Only the owner of the folder can delete a message");		
		
		folderService.removeMessage(folder, message);
	}
	
	
	
	public void checkActor(Message input){
		int actId;
		int inputId;

		boolean res;
		
		actId = actorService.findByPrincipal().getUserAccount().getId();
		inputId = input.getSender().getUserAccount().getId();
		res = false;
		
		if (actId == inputId) {
			res = true;
		} else {
			for (Actor a : input.getRecipients()) {
				inputId = a.getUserAccount().getId();

				if (actId == inputId) {
					res = true;
					break;
				}
			}
		}
		
		Assert.isTrue(res, "message.consult.notOwner");
	}
	
}
