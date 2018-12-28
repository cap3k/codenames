package fr.codenames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.codenames.dao.*;
import fr.codenames.dao.jpa.*;
import fr.codenames.model.*;
import fr.codenames.exception.*;

public class Test {

	private static IDAOUtilisateur daoUtilisateur = new DAOUtilisateurJPA();
	private static IDAOCarte daoCarte = new DAOCarteJPA();
	private static IDAOPartie daoPartie = new DAOPartieJPA();
	private static IDAOGrille newDAOgrille = new DAOGrilleJPA();
	private static IDAOPartie daoPartieJPA = new DAOPartieJPA();
	private static Utilisateur utilisateur;
	private static Scanner sc;

	public static void main(String[] args) {

		sc = new Scanner(System.in);

//		connexion();

		connexion();

//		List<String> listeDeMots = Arrays.asList("brass", "painstaking", "precious", "regular", "mysterious",
//				"lunchroom", "enjoy", "whirl", "store", "calculate", "sparkle", "cart", "previous", "whip", "upbeat",
//				"girl", "grease", "order", "steady", "rod", "cowardly", "utopian", "mint", "drawer", "reading", "try",
//				"coal", "greedy", "psychedelic", "efficacious", "amazing", "bomb", "remarkable", "puncture", "pets", "week",
//				"dizzy", "rambunctious", "simplistic", "overconfident", "route", "annoyed", "handsome", "yam", "carriage",
//				"coil", "dust", "resolute", "tramp", "moaning", "bore", "desk", "raise", "hang", "tacky", "suit", "type",
//				"cheese", "wriggle", "finger", "unfasten", "position", "alluring", "inconclusive", "expand", "knot",
//				"early", "receipt", "linen", "breath", "jeans", "ticket", "tickle", "aboriginal", "hulking", "fantastic",
//				"chew", "frog", "sand", "balance", "produce", "drag", "watery", "insurance", "tooth", "nation",
//				"screeching", "zesty", "metal", "death", "debt", "nice", "separate", "squealing", "request", "tart", "cold",
//				"mindless", "spade");

//		saveGrille();
//		saveListeDeCarte(listeDeMots);
//		savePartie();
//		List<Carte> test = daoCarte.findAll();
//		sc.close();
//		JoueurDonneurDeMot(addPartie(j));

		daoUtilisateur.close();
		daoCarte.close();
		daoPartie.close();
		newDAOgrille.close();
		daoPartieJPA.close();
	}

	/**
	 * Se connecter avec un nom d'utilisateur et un mot de passe (a saisir)
	 */
	public static void connexion() {
		System.out.print("Indiquer le nom d'utilisateur (touche entrer pour s'inscrire) : ");
		String username = sc.nextLine();

		if (username.equals("")) {
			inscription();
		}

		System.out.print("Indiquer le mot de passe : ");
		String password = sc.nextLine();

		try {
			utilisateur = daoUtilisateur.auth(username, password);
			System.out.println("connecte!");
			if (utilisateur.getType() == TypeUtilisateur.values()[1]) {
				menu(utilisateur);
			} else {
				menuAdmin();
			}

		}

		catch (UsernameOrPasswordNotFoundException e) {
			System.out.println("MAUVAIS USERNAME OU PASSWORD !!");
		}

		catch (AccountLockedException e) {
			System.out.println("COMPTE BLOQUE ... SORRY !");
		}
	}

	public static void inscription() {
		Joueur myJoueur = new Joueur();
		System.out.println("---------- INSCRIPTION -----------");
		System.out.print("Indiquer votre nom : ");
		myJoueur.setNom(sc.nextLine());

		System.out.print("Indiquer votre prenom : ");
		myJoueur.setPrenom(sc.nextLine());

		System.out.print("Indiquer votre pseudo : ");
		myJoueur.setPseudo(sc.nextLine());

		System.out.print("Indiquer le nom d'utilisateur : ");
		myJoueur.setUsername(sc.nextLine());

		System.out.print("Indiquer le mot de passe : ");
		myJoueur.setPassword(sc.nextLine());

		try {
			daoUtilisateur.save(myJoueur);
			connexion();
		}

		catch (NonUniqueUsernameException e) {
			System.out.println("Le nom d'utilisateur est deja utilise !");
		}
	}

	public static void menuAdmin() {
		int menu = 0;
		do {
			System.out.println("");
			System.out.println("------ MENU ADMINISTRATEUR -------");
			System.out.println("1.	Afficher tous les joueurs");
			System.out.println("2.	Bannir un joueur");
			System.out.println("3.	Ne plus bannir un joueur");
			System.out.println("4.	Supprimer un compte");
			System.out.println("5.	Se deconnecter");
			System.out.println("----------------------------------");
			menu = sc.nextInt();

			switch (menu) {

			case 1:
				showUsers();
				break;
			case 2:
				banUser();
				break;

			case 3:
				unBanUser();
				break;

			case 4:
				deleteUser();
				break;

			case 5:
				utilisateur = null;
				menu = 0;
				System.out.println("Bye!");
				break;
			}
		} while (menu != 0);
	}

	/**
	 * Bannir un utilisateur
	 */
	public static void banUser() {
		showUsersUnBan();

		System.out.print("Choisir l'utilisateur a bannir : ");

		daoUtilisateur.banById(sc.nextInt());
	}

	/**
	 * ne plus bannir un utilisateur
	 */
	public static void unBanUser() {
		showUsersBan();

		System.out.print("Choisir l'utilisateur a sortir du bannissement : ");

		daoUtilisateur.unBanById(sc.nextInt());
	}

	/**
	 * Supprimer un utilisateur
	 */
	public static void deleteUser() {
		showUsers();

		System.out.print("Choisir l'utilisateur a supprimer : ");

		daoUtilisateur.deleteById(sc.nextInt());
	}

	/**
	 * Afficher la liste des utilisateurs
	 */
	public static void showUsers() {
		for (Utilisateur j : daoUtilisateur.findAll()) {
			System.out.println(j.getId() + ". " + j.getNom() + " " + j.getPrenom());
		}
	}

	/**
	 * Afficher la liste des utilisateurs
	 */
	public static void showUsersUnBan() {
		for (Utilisateur j : daoUtilisateur.findAllUnBan()) {
			System.out.println(j.getId() + ". " + j.getNom() + " " + j.getPrenom());

		}
	}

	/**
	 * Afficher la liste des utilisateurs
	 */
	public static void showUsersBan() {
		for (Utilisateur j : daoUtilisateur.findAllBan()) {
			System.out.println(j.getId() + ". " + j.getNom() + " " + j.getPrenom());

		}
	}

	public static void menu(Utilisateur u) {
		int menu = 0;
		do {
			System.out.println("");
			System.out.println("-------------- MENU --------------");
			System.out.println("1.	Voir les cartes");
			System.out.println("10.	Ajouter une carte");
			System.out.println("11.	Modifier une carte");
			System.out.println("12.	Supprimer une carte");
			System.out.println("2.	Demarrer une nouvelle partie");
			System.out.println("20.	Lister les parties");
			System.out.println("3.	Se deconnecter");
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

			case 2:
				jouer(addPartie((Joueur) u));

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

		System.out.println("Saisir le libelle de la carte :");
		myCarte.setLibelle(sc.next());

		daoCarte.save(myCarte);
	}

	/**
	 * Modifier une carte
	 */
	public static void editCarte() {

		showCartes();

		System.out.print("Choisir la carte a modifier : ");
		Carte myCarte = daoCarte.findById(sc.nextInt());

		System.out.print(String.format("Saisir le libelle de la carte [%s] : ", myCarte.getLibelle()));
		myCarte.setLibelle(sc.next());

		daoCarte.save(myCarte);
	}

	/**
	 * Supprimer une carte
	 */
	public static void deleteCarte() {
		showCartes();

		System.out.print("Choisir la carte a supprimer : ");
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

	public static Participation addPartie(Joueur j) {

		Partie p = new Partie();
		p.setGrille(saveGrille());
		List<Participation> maParticipation = p.generer6participations(j);
		daoPartie.save(p);
		return maParticipation.get(0);

	}

	public static void jouer(Participation p) {

		if (p.getRole() == Role.MAITRE) {
			JoueurDonneurDeMot(p);
		} else {
			JouerDevineurDeMot(p);
		}

	}

	private static void JouerDevineurDeMot(Participation p) {
		// TODO Auto-generated method stub
		System.out.println("Donnez votre mot");

	}

	public static List<Case> JoueurDonneurDeMot(Participation p) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("codeNamesPU");
		EntityManager em = emf.createEntityManager();
		List<Case> result = em
				.createQuery("select c From Case c where c.grille=" + p.getPartie().getGrille().getId(), Case.class)
				.getResultList();
		for (int i = 0; i < 25; i++) {
			System.out.println(result.get(i).getCarte().getLibelle());
			System.out.println(result.get(i).getCouleur());
		}
		System.out.println("Donnez votre mot");
		String mot = sc.nextLine();
		System.out.println("Et le nombre de mots");
		int nbDeMots = Integer.parseInt(sc.nextLine());

		return result;

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

	/**
	 * Ajouter une grille
	 * 
	 * @return
	 */
	public static Grille saveGrille() {
		System.out.print("indiquer le niveau de difficulte entre 1 et 3 : ");
		int i = sc.nextInt();
		Difficulte d = Difficulte.values()[i - 1];
		Grille newGrille = new Grille();
		List<Carte> lesCartes = daoCarte.findAll();
		Collections.shuffle(lesCartes);
		newGrille.setDifficulte(d);
		newGrille.generer25Cases(lesCartes, d);
		newDAOgrille.save(newGrille);
		return newGrille;
	}
}
