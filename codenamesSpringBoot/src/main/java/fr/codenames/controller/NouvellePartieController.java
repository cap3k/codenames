package fr.codenames.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.codenames.dao.IDAOCarte;
import fr.codenames.dao.IDAOGrille;
import fr.codenames.dao.IDAOParticipation;
import fr.codenames.dao.IDAOPartie;
import fr.codenames.model.Carte;
import fr.codenames.model.Case;
import fr.codenames.model.Grille;
import fr.codenames.model.Participation;
import fr.codenames.model.Partie;



@Controller
public class NouvellePartieController {
	
	@Autowired
	private IDAOGrille daoGrille;
	
	@Autowired
	private IDAOCarte daoCarte;
	
	@Autowired
	private IDAOPartie daoPartie;
	
	@Autowired
	private IDAOParticipation daoParticipation;
	
	
	@RequestMapping(value="/nouvellePartie", method=RequestMethod.GET)
	public String home2(Model model) {
	
		return "nouvellePartie";
		}
	
	@PostMapping({ "/nouvellePartie" })
	public String creerParticipation(@ModelAttribute Participation participation, Model model) {
		
		//On génère la Grille
		Grille grille = new Grille();
		List<Carte> mesCartes = new ArrayList<Carte>();
		mesCartes = daoCarte.findAll();
		Collections.shuffle(mesCartes);
		grille.generer25Cases(mesCartes, participation.getPartie().getGrille().getDifficulte());
//		grille.setDifficulte(participation.getPartie().getGrille().getDifficulte());
		daoGrille.save(grille);
	
		//On génère la partie
		Partie partie = new Partie();
		partie.setGrille(grille);
		daoPartie.save(partie);
	
		//On génère la participation
		participation.setPartie(partie);
		
		daoParticipation.save(participation);
		boolean equipeId = participation.getEquipe();
		int roleId = participation.getRole().ordinal();
		boolean capitaineId = participation.getCapitaine();
		
		
		int id =daoPartie.findById(participation.getPartie().getId()).get().getGrille().getId();
		
		return "redirect:/plateau/"+id+"/"+equipeId+"/"+roleId+"/"+capitaineId;
	}
}
