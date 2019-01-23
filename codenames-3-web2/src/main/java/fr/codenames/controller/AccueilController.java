package fr.codenames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccueilController {
	@RequestMapping(value="/accueil", method=RequestMethod.GET)
	public String home(Model model) {
		return "accueil";
		}
}
