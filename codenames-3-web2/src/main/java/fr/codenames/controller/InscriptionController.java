package fr.codenames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.codenames.dao.IDAOJoueur;

@Controller
@RequestMapping("/inscription")
public class InscriptionController {
	@Autowired
	private IDAOJoueur daoJoueur;
	
	@GetMapping({ "" })
	public String inscriptionJoueur() {
		return "inscription";
	}

	@PostMapping("")
	public String inscriptionJoueur(@RequestParam String nom,
									@RequestParam String prenom, 
									@RequestParam String email, 
									@RequestParam String userName,
									@RequestParam String pwd,
									@RequestParam String pwd2, Model model) {
		
		if(pwd!=pwd2) {
			String msgPwd = "Les mots de passe doivent correspondrent!";
			model.addAttribute("msgPwd", msgPwd);
			return "inscription";
		}
		
		
		
		
		
		return pwd;
	
	}
}
