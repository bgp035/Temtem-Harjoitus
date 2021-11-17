package hh.Harjoitustyo.Temtem.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.Harjoitustyo.Temtem.domain.TemTypeRepository;
import hh.Harjoitustyo.Temtem.domain.TemType;
import hh.Harjoitustyo.Temtem.domain.TemtemRepository;

@CrossOrigin
@Controller
public class TemTypeController {
	
	@Autowired
	private TemTypeRepository temTypeRepo;
	
	@Autowired
	private TemtemRepository temRepo;
	
	
	@GetMapping("/temtemtypes")
	public String getAllTypes(Model model) {
		model.addAttribute("temtypes", temTypeRepo.findAll());
		System.out.println(model);
		return"temtypelist";
	}

	//Lisää temtemtyyppi
		@GetMapping("/addtype")
		@PreAuthorize("hasAuthority('ADMIN')")
		public String addTemType(Model model) {
			model.addAttribute("temType", new TemType());
			return "addtemtype";
			
		}
		
	//Tallentaa temtem tyypin
	@RequestMapping(value = "/savetype", method = RequestMethod.POST)
	public String save(TemType temType) {
		temTypeRepo.save(temType);
		return "redirect:temtemtypes";
	}
	
	
	@RequestMapping(value = "/temtemtypes2/{typeId}")
	public String getType(@PathVariable("typeId") Long typeId, Model model) {
		model.addAttribute("temtypes", temTypeRepo.findById(typeId));
		System.out.println(typeId + " " + "tyyppi testi");
		System.out.println(model + " " + "joku testi");
		return"test";
	}
	
	//Etsii tyypin id:n mukaan muokattavaksi
	@RequestMapping(value = "/edittype/{typeId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editType(@PathVariable("typeId") Long typeId, Model model) {
		model.addAttribute("temtype", temTypeRepo.findById(typeId));
		System.out.println(typeId + " " + "tyyppi testi");
		System.out.println(model + " " + "joku testi");
		return"edittype";
	}

	@RequestMapping(value= "deletetype/{typeId}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteType(@PathVariable("typeId") Long typeId, Model model) {
		temTypeRepo.deleteById(typeId);
		return "redirect:../temtemtypes";
	}
	
	
	/*
	@GetMapping("/temtemtypes2/{typeId}")
	public String getAllTemtems(@PathVariable(value = "typeId") Long typeId, Model model) {
		model.addAttribute("Temtem", temTypeRepo.findByTemTypeIn(typeId));
		System.out.println(typeId + "tyyppi testi");
		System.out.println(model + "testi");
		return"temtypelist";
	}
	*/

	@RequestMapping("/temtemtypes/{typeId}")
	public @ResponseBody Optional<TemType> findSurveyRest(@PathVariable(value = "typeId") Long typeId) {
		return temTypeRepo.findById(typeId);
	}
	
	@GetMapping("/test2")
	public String tesTemType(Model model) {
		model.addAttribute("temtypes", temTypeRepo.findAll());
		return "test";
	}
	@GetMapping("/test")
	public String tesTems(Model model) {
		model.addAttribute("temtems", temRepo.findAll());
		return "test";
	}
	
	/*
	@RequestMapping(value = "temtemtype/{typeId}/temtems")
	public @ResponseBody List<Temtem> findTypeTemtemRest(@PathVariable("typeId") Long typeId) {
		TemType tyyppi = temTypeRepo.findById(typeId);
		return tyyppi.getTemtems();
		
	}
	*/
	
}
