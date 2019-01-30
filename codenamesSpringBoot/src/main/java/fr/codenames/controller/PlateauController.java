package fr.codenames.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.codenames.dao.IDAOCarte;
import fr.codenames.dao.IDAOGrille;
import fr.codenames.dao.IDAOPartie;
import fr.codenames.model.Carte;
import fr.codenames.model.Case;
import fr.codenames.model.CaseAReveler;
import fr.codenames.model.Difficulte;
import fr.codenames.model.Grille;
import fr.codenames.model.Participation;
import fr.codenames.model.Partie;





@Controller
public class PlateauController {
	
	@Autowired
	private IDAOPartie daoPartie;
	
	@Autowired
	private IDAOGrille daoGrille;
	
	@Autowired
	private IDAOCarte daoCarte;
	
	@RequestMapping({ "/plateau/{id}/{equipeId}/{roleId}/{capitaineId}" })
	public String home(@PathVariable int id,@PathVariable boolean equipeId,@PathVariable int roleId,@PathVariable boolean capitaineId, Model model) {
		String equipe;
		model.addAttribute("cases", daoGrille.findCaseByGrilleID(id));
		if(equipeId==false) {
		 equipe="ROUGE";
		}else {
			equipe="BLEUE";
		}
		model.addAttribute("equipe", equipe);
		model.addAttribute("role", roleId);
		model.addAttribute("capitaine", capitaineId);
		model.addAttribute("grille", id);
		
		return "plateau";
		}
	
	@CrossOrigin
	@PostMapping({"/plateau"})
	@ResponseBody
	public String revelerCouleur(@RequestBody CaseAReveler maCase) {
		
		
	
    return daoGrille.findById(maCase.getGrille()).get().getCases().get(maCase.getPos()).getCouleur().toString();
		

	}
	
	
}
