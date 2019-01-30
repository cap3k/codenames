package fr.codenames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.codenames.dao.IDAOJoueur;
import fr.codenames.model.Joueur;

@Controller
@RequestMapping("/inscription")
public class InscriptionController {
	@Autowired
	private IDAOJoueur daoUtilisateur;
	
	@GetMapping({ "" })
	public String inscriptionJoueur() {
		return "inscription";
	}

	@PostMapping("")
	public String inscriptionJoueur(@ModelAttribute Joueur joueur, Model model) {
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			joueur.setPassword(bcrypt.encode(joueur.getPassword()));
			daoUtilisateur.save(joueur);
			return "redirect:connexion";
	}
}
