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
@RequestMapping("/connexion")
public class ConnexionController {
	
	@Autowired
	private IDAOJoueur daoJoueur;
	
	@GetMapping("")
	public String connexionJoueur() {
		return "connexion";
	}
	
	@PostMapping("")
	public String connexionJoueur(@RequestParam String username, @RequestParam String password, Model model) {
		
		model.addAttribute("joueur", daoJoueur.auth(username, password));
//VERIFICATION RENSIGNEMENT CHAMPS DE CONNEXION
		if (username.equals("")) {
			//username � renseigner
			String msgUser;
			msgUser="Veuillez rensigner un nom d'utilisateur";
			model.addAttribute("msgUser", msgUser);
			if (password.equals("")) {
				//mot de passe � renseigner
				String msgPwd;
				msgPwd="Veuillez rensigner un mot de passe";
				model.addAttribute("msgPwd", msgPwd);
				return "/connexion";
			}
			else {
				return "/connexion";
			}
		}

//CONNEXION	
		if(daoJoueur.auth(username, password) == null) {
			//mauvais username ou password
			String msgWrong;
			msgWrong="Le nom d'utilisateur ou le mot de passe est incorrect";
			model.addAttribute("msgWrong", msgWrong);
			return "connexion";
		}
		else {
			if(daoJoueur.authBanni(username)!=null) {
				//vous etes banni
				String msgBanni;
				msgBanni="Vous etes banni!";
				model.addAttribute("msgBanni", msgBanni);
				return "connexion";
			}
			return "redirect:./accueil";
		}
	}
}