package fr.codenames.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.codenames.dao.IDAOJoueur;

@Controller
@RequestMapping("/deconnexion")
public class DeconnexionController {
	
	@GetMapping("")
    public String deconnexion(HttpSession session, Model model) {
        session.invalidate();
        return "connexion";
    }
}