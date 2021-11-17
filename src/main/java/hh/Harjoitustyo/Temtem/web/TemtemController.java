package hh.Harjoitustyo.Temtem.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.Harjoitustyo.Temtem.domain.TemTypeRepository;
import hh.Harjoitustyo.Temtem.domain.Temtem;
import hh.Harjoitustyo.Temtem.domain.TemtemRepository;

@Controller
public class TemtemController {
	
	
	@Autowired
	private TemtemRepository temRepo;
	
	@Autowired
	private TemTypeRepository temTypeRepo;
	
	

    @RequestMapping(value="/")
    public String login() {	
        return "login";
    }	

    @RequestMapping(value="/login")
    public String login2() {	
        return "login";
    }	
	//Hae kaikki temtemit reposta
	@GetMapping("/temtemlist")
	public String getAllTemtems(Model model) {
		model.addAttribute("temtems", temRepo.findAll());
		return "temlist";
	}
	
	//Lisää temtem ja hae Temtem tyypit
	@GetMapping("/addtemtem")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addTemtem(Model model) {
		model.addAttribute("Temtem", new Temtem());
		model.addAttribute("temtypes", temTypeRepo.findAll());
		return "addtemtem";
		
	}
	
	//Tallentaa temtemin
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Temtem Temtem) {
		temRepo.save(Temtem);
		return "redirect:temtemlist";
	}
	
	//Ottaa temtemin id:n mukaan muokattavaksi
	@RequestMapping(value = "/edittem/{temId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editTem(@PathVariable("temId") Long temId, Model model) {
		model.addAttribute("temtem", temRepo.findById(temId));
		model.addAttribute("types", temTypeRepo.findAll());
		System.out.println(temId + " " + "tyyppi testi");
		System.out.println(model + " " + "joku testi");
		return"edittem";
	}

	//Poistaa temtemin listalta id:n mukaan
	@RequestMapping(value = "deletetem/{temId}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteTem(@PathVariable("temId") Long temId, Model model) {
		temRepo.deleteById(temId);
		return "redirect:../temtemlist";
	}
	
}
