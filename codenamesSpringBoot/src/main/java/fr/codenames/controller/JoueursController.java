package fr.codenames.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.codenames.dao.IDAOCarte;
import fr.codenames.dao.IDAOJoueur;
import fr.codenames.model.Carte;
import fr.codenames.security.annotation.IsAdmin;

@Controller
@RequestMapping("/joueur")
public class JoueursController {
	@Autowired
	private IDAOJoueur daoJoueur;
	
//LISTER LES JOUEURS	
	@GetMapping("")
	public String listeCarte(Model model) {
		model.addAttribute("joueurs", daoJoueur.findAll());
		return "liste-joueurs";		
	}
	
// BANNIR UN JOUEUR
	@GetMapping("/bannir/{id}")
	@IsAdmin
	public String bannirJoueur(@PathVariable Integer id) {
		daoJoueur.findById(id).get().setBanni(true);
		return "redirect:../";
	}	
	
	// BANNIR UN JOUEUR
	@GetMapping("/debannir/{id}")
	@IsAdmin
	public String debannirJoueur(@PathVariable Integer id) {
		daoJoueur.findById(id).get().setBanni(false);
		return "redirect:../";
	}	
	
}