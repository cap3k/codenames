package fr.codenames;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import fr.codenames.dao.IDAOCarte;
import fr.codenames.dao.IDAOPartie;
import fr.codenames.dao.IDAOUtilisateur;
import fr.codenames.dao.jpa.DAOCarteJPA;
import fr.codenames.dao.jpa.DAOGrilleJPA;
import fr.codenames.dao.jpa.DAOPartieJPA;
import fr.codenames.dao.jpa.DAOUtilisateurJPA;
import fr.codenames.exception.AccountLockedException;
import fr.codenames.exception.NonUniqueUsernameException;
import fr.codenames.model.Carte;
import fr.codenames.model.Difficulte;
import fr.codenames.model.Grille;
import fr.codenames.exception.UsernameOrPasswordNotFoundException;
import fr.codenames.model.Joueur;
import fr.codenames.model.Partie;
import fr.codenames.model.Utilisateur;

public class Test {
	
	private static IDAOUtilisateur daoUtilisateur = new DAOUtilisateurJPA();
	private static IDAOCarte daoCarte = new DAOCarteJPA();
	private static IDAOPartie daoPartie = new DAOPartieJPA();
	private static Utilisateur utilisateur;
	private static Scanner sc;

	public static void main(String[] args) {
		
		sc = new Scanner(System.in);
		
		connexion();

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
		
		daoUtilisateur.close();
		daoCarte.close();
		daoPartie.close();
	}
	
	/**
	 * Se connecter avec un nom d'utilisateur et un mot de passe (� saisir)
	 */
	public static void connexion() {
		System.out.print("Indiquer le nom d'utilisateur (touche entrer pour s'inscrire) : ");
		String username = sc.nextLine();
		
		if (username.equals("")) {
			inscription();
			menu();
		}

		System.out.print("Indiquer le mot de passe : ");
		String password = sc.nextLine();

		try {
			utilisateur = daoUtilisateur.auth(username, password);
			System.out.println("connect�!");
			menu();
		}

		catch (UsernameOrPasswordNotFoundException e) {
			System.out.println("MAUVAIS USERNAME OU PASSWORD !!");
		}

		catch (AccountLockedException e) {
			System.out.println("COMPTE BLOQUE ... SORRY !");
		}
	}

	public static void inscription() {
		//IDAOUtilisateur daoUtilisateur = new DAOUtilisateurJPA();

		Joueur myJoueur = new Joueur();
		System.out.println("---------- INSCRIPTION -----------");
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
		
	public static void menu() {
		int menu = 0;	
		do {
			System.out.println("");
			System.out.println("-------------- MENU --------------");
			System.out.println("1.	Voir les cartes");
			System.out.println("10.	Ajouter une carte");
			System.out.println("11.	Modifier une carte");
			System.out.println("12.	Supprimer une carte");
			System.out.println("2.	D�marrer une nouvelle partie");
			System.out.println("20.	Lister les parties");
			System.out.println("3.	Se d�connecter");
			System.out.println("----------------------------------");
			menu = sc.nextInt();

			switch (menu) {
			case 1:
				showCartes();
				break;

			case 10:
				addCarte();
				break;

			case 11:
				editCarte();
				break;

			case 12:
				deleteCarte();
				break;

			case 20:
				showParties();
				break;

			case 3:
				utilisateur = null;
				menu = 0;
				System.out.println("Bye!");
				break;
			}
		} while (menu != 0);
	}
	
	/**
	 * Affiche la liste des cartes
	 */
	public static void showCartes() {
		for (Carte c : daoCarte.findAll()) {
			System.out.println(c.getId() + ". " + c.getLibelle());
		}
	}

	/**
	 * Ajouter une carte
	 */
	public static void addCarte() {
		Carte myCarte = new Carte();

		System.out.println("Saisir le libell� de la carte :");
		myCarte.setLibelle(sc.next());

		daoCarte.save(myCarte);
	}

	/**
	 * Modifier une carte
	 */
	public static void editCarte() {
		
		showCartes();

		System.out.print("Choisir la carte � modifier : ");
		Carte myCarte = daoCarte.findById(sc.nextInt());

		System.out.print(String.format("Saisir le libell� de la carte [%s] : ", myCarte.getLibelle()));
		myCarte.setLibelle(sc.next());

		daoCarte.save(myCarte);
	}

	/**
	 * Supprimer une carte
	 */
	public static void deleteCarte() {
		showCartes();

		System.out.print("Choisir la carte � supprimer : ");
		daoCarte.deleteById(sc.nextInt());
	}

	/**
	 * Affiche la liste des parties
	 */
	public static void showParties() {
		for (Partie p : daoPartie.findAll()) {
			System.out.println(p.getId());
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
