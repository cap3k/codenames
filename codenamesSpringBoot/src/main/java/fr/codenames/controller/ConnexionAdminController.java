package fr.codenames.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.codenames.dao.IDAOAdministrateur;


@Controller
@RequestMapping("/connexionAdmin")
public class ConnexionAdminController {
	
	@Autowired
	private IDAOAdministrateur daoAdmin;
	
	@GetMapping({ "" })
	public String connexionAdm() {
		return "connexionAdmin";
	}
	
	@PostMapping("")
	public String connexionAdm(@RequestParam String userNameAdm, @RequestParam String pwdAdm, Model model) {
		model.addAttribute("administrateur", daoAdmin.auth(userNameAdm, pwdAdm));
//VERIFICATION RENSIGNEMENT CHAMPS DE CONNEXION	
		if (userNameAdm.equals("")) {
			//username � renseigner
			String msgUser;
			msgUser="Veuillez rensigner un nom d'utilisateur";
			model.addAttribute("msgUser", msgUser);
			if (pwdAdm.equals("")) {
				//mot de passe � renseigner
				String msgPwd;
				msgPwd="Veuillez rensigner un mot de passe";
				model.addAttribute("msgPwd", msgPwd);
				return "connexionAdmin";
			}
			else {
				//username � renseigner
				return "connexionAdmin";
			}
		}		
		
//CONNEXION	
		if(daoAdmin.auth(userNameAdm, pwdAdm) == null) {
			//mauvais username ou password
			String msgWrong;
			msgWrong="Le nom d'utilisateur ou le mot de passe est incorrect";
			model.addAttribute("msgWrong", msgWrong);
			return "connexionAdmin";
		}
		else {
			return "redirect:./admin";
		}
	}
}