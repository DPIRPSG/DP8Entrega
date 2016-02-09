package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Folder;
import domain.Message;

import repositories.FolderRepository;

@Service
@Transactional 
public class FolderService {
 	//Managed repository -----------------------------------------------------
	
	@Autowired
	private FolderRepository folderRepository;
	
	//Supporting services ----------------------------------------------------
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private MessageService messageService;
	
	//Constructors -----------------------------------------------------------
	
	public FolderService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------

	/** 
	 * Devuelve Folder preparado para ser modificado. Necesita usar save para que persista en la base de datos
	 */	
	//req: 24.2
	public Folder create(){
		Folder result;
		Collection<Message> messages;
		
		result = new Folder();
		messages = new ArrayList<Message>();
		
		result.setMessages(messages);
		
		return result;
	}
	
	/**
	 * Guarda un folder creado o modificado
	 */
	//req: 24.2
	public Folder save(Folder folder){
		Assert.notNull(folder);
		
		Folder result;
		
		result = folderRepository.save(folder);
		
		return result;
	}
	
	/**
	 * Guarda varias folder
	 */
	//req: 24.2
	public Collection<Folder> save(Collection<Folder> folder){
		Assert.notNull(folder);
		
		Collection<Folder> result;
		
		result = new ArrayList<Folder>();
		
		for(Folder a:folder){
			result.add(folderRepository.save(a));
		}

		
		return result;
	}
	
	/**
	 * Elimina un folder. No elimina carpetas del sistema
	 */
	//req: 24.2	
	public void delete(Folder folder){
		Assert.notNull(folder);
		Assert.isTrue(folder.getId() != 0);
		
		Assert.isTrue(folder.getActor().equals(actorService.findByPrincipal()), "Only the owner can delete the folder");
		
		// Si es del sistema no debe poder borrarse
		Assert.isTrue(!folder.getIsSystem(), "It's a system Folder and couldn't be removed");
		
		folderRepository.delete(folder);
	}
	
	public Folder findOne(int folderId){
		Folder result;
		
		result = folderRepository.findOne(folderId);
		
		Assert.notNull(result);
		
		checkActor(result);
		
		return result;
	}
	
	//Other business methods -------------------------------------------------
	
	/**
	 * Añade un mensaje a una carpeta dada
	 */
	public void addMessage(Folder f, Message m){
		Assert.notNull(f);
		Assert.notNull(m);
		
		f.addMessage(m);
		this.save(f);
	}
	
	/**
	 * Borrar un mensaje de una carpeta dada
	 */
	public void removeMessage(Folder f, Message m){
		Assert.notNull(m);
		Assert.notNull(f);
		if (f.getName().equals("TrashBox") && f.getIsSystem()){
			f.removeMessage(m);
			this.save(f);
		}
		else{
			Actor actor = actorService.findByPrincipal();
			int count;
			
			count = 0;
			Folder trashBox;

			for(Folder folder:actor.getFolders()){
				if(folder.getMessages().contains(m)){
					count++;
				}
				if(count>1){
					break;
				}
			}
			f.removeMessage(m);
			if(count == 1){
				trashBox = this.findByNameActorIDIsSystem("TrashBox", actor, true).iterator().next();
				trashBox.addMessage(m);
				this.save(trashBox);
			}
			this.save(f);
		}
	}
	
	/**
	 * Devuelve todas las carpetas del actor actual
	 */
	//req: 24.1
	public Collection<Folder> findAllByActor(){
		Collection<Folder> result;
		Actor actor;
		
		actor = actorService.findByPrincipal();
		result = folderRepository.findAllByActorId(actor.getId());
		
		return result;
	}

	/**
	 * Crea y guarda las carpetas por defecto
	 */
	//req: x
	public Collection<Folder> initializeSystemFolder(Actor actor){
		Collection<Folder> result;
		Collection<String> names;
		
		result = new ArrayList<Folder>();
		names = new ArrayList<String>();
		
		names.add("Inbox");
		names.add("OutBox");
		names.add("TrashBox");
				
		
		for (String string : names) {
			Folder temp;
			
			temp = this.create();
			
			temp.setIsSystem(true);
			temp.setName(string);
			temp.setActor(actor);
						
			result.add(temp);
		}
		
		actor.setFolders(result);
		
		return result;
	}
	
	/**
	 * Encuentra una carpeta dado su nombre, actor e isSystem
	 */
	private Collection<Folder> findByNameActorIDIsSystem(String name, Actor actor, boolean isSystem){
		Collection<Folder> result;
		
		result = folderRepository.findByNameActorIDIsSystem(name.trim(), isSystem, actor.getId());
		
		return result;		
	}
	
	/**
	 * Mover un mensaje de una carpeta a otra
	 */
	public void moveMessage(Folder origin, Folder destination, Message m){
		Assert.notNull(m);
		Assert.isTrue(m.getId()!= 0);
		Assert.notNull(origin);
		Assert.isTrue(origin.getId() != 0);
		Assert.notNull(destination);
		
		Assert.isTrue(origin.getActor().equals(destination.getActor()), "The owner of the source folder is not the same as the destination");
		Assert.isTrue(origin.getActor().equals(actorService.findByPrincipal()), "Only the owner can manage the folder");
		
		Assert.isTrue(origin.getMessages().contains(m));
		
		if(destination.getId()==0){
			destination = this.save(destination);
		}
		
		if(!destination.getMessages().contains(m)){
			this.addMessage(destination, m);
		}
		
		this.removeMessage(origin, m);
	}
	
	
	public void checkActor(Folder folder){
		int actId;
		int inputId;
		
		actId = folder.getActor().getUserAccount().getId();
		inputId = actorService.findByPrincipal().getUserAccount().getId();
		
		Assert.isTrue(actId == inputId, "folder.modify.notOwner");
	}
	
	public Collection<Folder> findByMessageAndActualActor(Message messa){
		messageService.checkActor(messa);
		
		Collection<Folder> result;
		Collection<Folder> folders;
		Actor actor;
		
		actor = actorService.findByPrincipal();
		result = new ArrayList<Folder>();
		
		folders = folderRepository.findAllByActorId(actor.getId());
		
		for (Folder f: folders){
			if(f.getMessages().contains(messa)){
				result.add(f);
			}
		}
		
		
		return result;
	}
}
