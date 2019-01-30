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
import fr.codenames.model.Carte;
import fr.codenames.security.annotation.IsAdmin;

@Controller
@RequestMapping("/carte")
public class CartesController {
	@Autowired
	private IDAOCarte daoCarte;
	
//LISTER LES CARTES	
	@GetMapping("")
	public String listeCarte(Model model) {
		model.addAttribute("cartes", daoCarte.findAll());
		return "liste-cartes";		
	}
	
//AJOUTER UNE CARTE
	@GetMapping({ "/ajouter" })
	@IsAdmin
	public String ajouterCarte(Model model) {
		return "form-carte";
	}
	
	@PostMapping("/ajouter")
	@IsAdmin
	public String ajouterCarte( @Valid @ModelAttribute Carte carte, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return("form-carte");
		}
		daoCarte.save(carte);
		return "redirect:.";
	}
	
//EDITER UNE CARTE	
	@GetMapping({ "/editer/{id}" })
	@IsAdmin
	public String editerCarte(@PathVariable int id, Model model) {
		model.addAttribute("carte", daoCarte.findById(id).get());
		return "form-carte";
	}
	
	@PostMapping("/editer/{id}")
	@IsAdmin
	public String editerCarte(@Valid @ModelAttribute Carte carte, BindingResult result, @PathVariable int id) {
		carte.setId(id);
		if(result.hasErrors()) {
			return("form-carte");
		}
		daoCarte.save(carte);
		return "redirect:..";
	}
	
//SUPPRIMER LES CARTES	
	@GetMapping("/supprimer/{id}")
	@IsAdmin
	public String supprimerCarte(@PathVariable Integer id) {
		daoCarte.deleteById(id);
		return "redirect:../";
	}	
}
