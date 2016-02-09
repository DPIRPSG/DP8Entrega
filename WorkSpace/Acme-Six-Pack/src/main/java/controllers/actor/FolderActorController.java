package controllers.actor;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.FolderService;

import controllers.AbstractController;
import domain.Folder;

@Controller
@RequestMapping(value = "/folder/actor")
public class FolderActorController extends AbstractController{

	//Services ----------------------------------------------------------

	@Autowired
	private FolderService folderService;
	
	@Autowired
	private ActorService actorService;
	
	//Constructors ----------------------------------------------------------
	
	public FolderActorController(){
		super();
	}

	//Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
        ModelAndView result;
        Collection<Folder> folders;
        
        folders = folderService.findAllByActor();
        
        result = new ModelAndView("folder/list");
        result.addObject("folders", folders);
        result.addObject("requestURI", "folder/actor/list.do");
        
        return result;
	}
	
	// Creation ----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Folder folder;
		
		folder = folderService.create();
		folder.setActor(actorService.findByPrincipal());
		
		result = createEditModelAndView(folder);
		
		return result;
	}

	// Edition ----------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int folderId) {
	
		ModelAndView result;
		Folder folder;
		
		folder = folderService.findOne(folderId);
		Assert.notNull(folder);
		
		folderService.checkActor(folder);
		
		result = createEditModelAndView(folder);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Folder folder, BindingResult binding) {
		ModelAndView result;
		
		folderService.checkActor(folder);

		if (binding.hasErrors()) {
			result = createEditModelAndView(folder);
		} else {
			try {
				folderService.save(folder);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(folder, "folder.commit.error");				
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Folder folder, BindingResult binding) {
		ModelAndView result;
		
		folderService.checkActor(folder);

		if (binding.hasErrors()) {
			result = createEditModelAndView(folder);
		} else {
			try {
				folderService.delete(folder);		
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(folder, "folder.commit.error");								
			}
		}

		return result;
	}

	// Ancillary Methods ----------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(Folder folder) {
		ModelAndView result;
		
		result = createEditModelAndView(folder, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Folder folder, String message){
		ModelAndView result;
		
		result = new ModelAndView("folder/edit");
		result.addObject("folder", folder);
		result.addObject("message", message);
		
		return result;
	}

}
