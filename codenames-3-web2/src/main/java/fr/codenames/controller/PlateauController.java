package fr.codenames.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.codenames.dao.IDAOCarte;
import fr.codenames.dao.IDAOGrille;
import fr.codenames.model.Carte;
import fr.codenames.model.Case;
import fr.codenames.model.Difficulte;
import fr.codenames.model.Grille;





@Controller
public class PlateauController {
	
	@Autowired
	private IDAOGrille daoGrille;
	
	@Autowired
	private IDAOCarte daoCarte;
	
	@RequestMapping({ "/plateau", "/plateau/{id}/{equipeId}/{roleId}/{capitaineId}" })
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
		
		return "plateau";
		}
	
	@PostMapping({ "/plateau" })
	public String creerGrille(@ModelAttribute Grille grille, Model model) {
		
		

		
		 List<Carte> mesCartes = daoCarte.findAll();
		Collections.shuffle(mesCartes);
		
		grille.generer25Cases(mesCartes, grille.getDifficulte());
		
		daoGrille.save(grille);
	
		model.addAttribute("cases", grille.getCases());

		return "plateau";
	}
}
