package fr.codenames;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import fr.codenames.dao.IDAOUtilisateur;
import fr.codenames.dao.jpa.DAOCarteJPA;
import fr.codenames.dao.jpa.DAOGrilleJPA;
import fr.codenames.dao.jpa.DAOUtilisateurJPA;
import fr.codenames.exception.NonUniqueUsernameException;
import fr.codenames.model.Carte;
import fr.codenames.model.Difficulte;
import fr.codenames.model.Grille;
import fr.codenames.model.Joueur;

public class Test {
	private static Scanner sc;

	public static void main(String[] args) {
		
		sc = new Scanner(System.in);
		creerJoueur();
		System.out.println("Fin Programme");

		sc.close();
		
		List<String> listeDeMots = Arrays.asList("brass", "painstaking", "precious", "regular", "mysterious",
				"lunchroom", "enjoy", "whirl", "store", "calculate", "sparkle", "cart", "previous", "whip", "upbeat",
				"girl", "grease", "order", "steady", "rod", "cowardly", "utopian", "mint", "drawer", "reading", "try",
				"coal", "greedy", "psychedelic", "efficacious", "amazing", "bomb", "remarkable", "puncture", "pets", "week",
				"dizzy", "rambunctious", "simplistic", "overconfident", "route", "annoyed", "handsome", "yam", "carriage",
				"coil", "dust", "resolute", "tramp", "moaning", "bore", "desk", "raise", "hang", "tacky", "suit", "type",
				"cheese", "wriggle", "finger", "unfasten", "position", "alluring", "inconclusive", "expand", "knot",
				"early", "receipt", "linen", "breath", "jeans", "ticket", "tickle", "aboriginal", "hulking", "fantastic",
				"chew", "frog", "sand", "balance", "produce", "drag", "watery", "insurance", "tooth", "nation",
				"screeching", "zesty", "metal", "death", "debt", "nice", "separate", "squealing", "request", "tart", "cold",
				"mindless", "spade");

		
		saveGrille();
		saveListeDeCarte(listeDeMots);
//		savePartie();
		
	}

	public static void creerJoueur() {
		IDAOUtilisateur daoUtilisateur = new DAOUtilisateurJPA();

		Joueur myJoueur = new Joueur();

		System.out.print("Indiquer votre nom : ");
		myJoueur.setNom(sc.nextLine());

		System.out.print("Indiquer votre pr�nom : ");
		myJoueur.setPrenom(sc.nextLine());

		System.out.print("Indiquer votre pseudo : ");
		myJoueur.setPseudo(sc.nextLine());

		System.out.print("Indiquer le nom d'utilisateur : ");
		myJoueur.setUsername(sc.nextLine());

		System.out.print("Indiquer le mot de passe : ");
		myJoueur.setPassword(sc.nextLine());

		try {
			daoUtilisateur.save(myJoueur);
		}

		catch (NonUniqueUsernameException e) {
			System.out.println("Le nom d'utilisateur est d�j� utilis� !");
		}
		
	

	}
	
	/** Ajouter une liste de cartes */

	public static void saveListeDeCarte(List<String> listeDeMots) {
		DAOCarteJPA newDAOcarte = new DAOCarteJPA();
		for (int i = 0; i < listeDeMots.size(); i++) {
			Carte myCarte = new Carte();
			myCarte.setLibelle(listeDeMots.get(i));
			newDAOcarte.save(myCarte);
		}
	}

	public static void saveGrille() {
		DAOGrilleJPA newDAOgrille = new DAOGrilleJPA();
		DAOCarteJPA daoCarte = new DAOCarteJPA();
		int i=lireEntier();
		Difficulte d =Difficulte.values()[i - 1];
		Grille newGrille = new Grille();
		List<Carte> lesCartes = daoCarte.findAll();
		Collections.shuffle(lesCartes);
		newGrille.setDifficulte(d);
		newGrille.generer25Cases(lesCartes, d);
		newDAOgrille.save(newGrille);
	}
	
	static int lireEntier() {
		Scanner myScanner = new Scanner(System.in);

		try {
			return myScanner.nextInt();
		}

		catch (Exception ex) {
			return 0;
		}
	}

}
