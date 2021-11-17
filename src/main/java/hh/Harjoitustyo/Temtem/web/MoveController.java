package hh.Harjoitustyo.Temtem.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.Harjoitustyo.Temtem.domain.Move;
import hh.Harjoitustyo.Temtem.domain.MoveRepository;
import hh.Harjoitustyo.Temtem.domain.TemTypeRepository;

@Controller
public class MoveController {
	
	@Autowired
	private MoveRepository moveRepo;
	@Autowired 
	private TemTypeRepository temTypeRepo;
	
	@GetMapping("/moves")
	public String getAllMoves(Model model) {
		model.addAttribute("moves", moveRepo.findAll());
		return"movelist";
	}
	
	
	//Lisää Move ja hae temtem tyypit
	@GetMapping("/addmove")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addMove(Model model) {
		model.addAttribute("Move", new Move());
		model.addAttribute("temtypes", temTypeRepo.findAll());
		return "addmove";
	}
	
	//Tallenna uusi Move
	@RequestMapping(value = "/savemove", method = RequestMethod.POST)
	public String save(Move Move) {
		moveRepo.save(Move);
		return "redirect:moves";
	}
	
	@RequestMapping(value = "editmove/{moveId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editMove(@PathVariable("moveId") Long moveId, Model model) {
		model.addAttribute("move", moveRepo.findById(moveId));
		model.addAttribute("types", temTypeRepo.findAll());
		return"editmove";
	}
	
	@RequestMapping(value = "deletemove/{moveId}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteMove(@PathVariable("moveId") Long moveId, Model model) {
		moveRepo.deleteById(moveId);
		return "redirect:../moves";
	}
	
	
}
