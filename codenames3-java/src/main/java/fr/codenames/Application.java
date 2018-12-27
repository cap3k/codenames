package fr.codenames;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import fr.codenames.dao.IDAOCarte;
import fr.codenames.dao.IDAOPartie;
import fr.codenames.dao.IDAOUtilisateur;
import fr.codenames.dao.sql.DAOCarteSQL;
import fr.codenames.dao.sql.DAOPartieSQL;
import fr.codenames.dao.sql.DAOUtilisateurSQL;
import fr.codenames.exception.AccountLockedException;
import fr.codenames.exception.NonUniqueUsernameException;
import fr.codenames.exception.UsernameOrPasswordNotFoundException;
import fr.codenames.model.Carte;
import fr.codenames.model.Joueur;
import fr.codenames.model.Partie;
import fr.codenames.model.Utilisateur;

public class Application {
	private static IDAOUtilisateur daoUtilisateur = new DAOUtilisateurSQL();
	private static IDAOCarte daoCarte = new DAOCarteSQL();
	private static IDAOPartie daoPartie = new DAOPartieSQL();
	private static Utilisateur utilisateur;
	private static Scanner sc;

	/**
	 * Programme principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		sc = new Scanner(System.in);

//		connexion();

	}

	
	// liste de mots aléatoire
	
	/** Ajouter une liste de cartes */
	List<String> listeDeMots=Arrays. asList("brass","painstaking","precious","regular","mysterious","lunchroom","enjoy","whirl","store","calculate","sparkle","cart","previous","whip","upbeat","girl","grease","order","steady","rod","cowardly","utopian","mint","drawer","reading","try","coal","greedy","psychedelic","efficacious","amazing","bomb","remarkable","puncture","pets","week","dizzy","rambunctious","simplistic","overconfident","route","annoyed","handsome","yam","carriage","coil","dust","resolute","tramp","moaning","bore","desk","raise","hang","tacky","suit","type","cheese","wriggle","finger","unfasten","position","alluring","inconclusive","expand","knot","early","receipt","linen","breath","jeans","ticket","tickle","aboriginal","hulking","fantastic","chew","frog","sand","balance","produce","drag","watery","insurance","tooth","nation","screeching","zesty","metal","death","debt","nice","separate","squealing","request","tart","cold","mindless","spade");
	
	public static void addListeDeCarte(List<String> listeDeMots) {
		for(int i=0; i<listeDeMots.size();i++) {
			List<Carte> listeDeCarte=new ArrayList<Carte>();
			Carte myCarte = new Carte();
			Collections.shuffle(listeDeMots);
			myCarte.setLibelle(listeDeMots.get(i));
			listeDeCarte.add(myCarte);
		}
	}
	
	/**
	 * Se connecter avec un nom d'utilisateur et un mot de passe (à saisir)
	 */
	public static void connexion() {
		System.out.print("Indiquer le nom d'utilisateur (touche entrer pour s'inscrire) : ");
		String username = sc.nextLine();
		
		if (username.equals("")) {
			inscription();
			return;
		}

		System.out.print("Indiquer le mot de passe : ");
		String password = sc.nextLine();

		try {
			utilisateur = daoUtilisateur.auth(username, password);
			menu();
		}

		catch (UsernameOrPasswordNotFoundException e) {
			System.out.println("MAUVAIS USERNAME OU PASSWORD !!");
		}

		catch (AccountLockedException e) {
			System.out.println("COMPTE BLOQUE ... SORRY !");
		}
	}

	/**
	 * S'inscrire (créer un nouveau compte utilisateur)
	 */
	public static void inscription() {
		Joueur myJoueur = new Joueur();
		
		System.out.print("Indiquer votre nom : ");
		myJoueur.setNom(sc.nextLine());
		
		System.out.print("Indiquer votre prénom : ");
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
			System.out.println("Le nom d'utilisateur est déjà utilisé !");
		}
		
		connexion();
	}

	/**
	 * Affiche le menu et démarre les sous-programmes
	 */
	public static void menu() {
		int menu = 0;

		do {
			System.out.println("----------------------------------");
			System.out.println("1.	Voir les cartes");
			System.out.println("10.	Ajouter une carte");
			System.out.println("11.	Modifier une carte");
			System.out.println("12.	Supprimer une carte");
			System.out.println("2.	Démarrer une nouvelle partie");
			System.out.println("20.	Lister les parties");
			System.out.println("3.	Se déconnecter");
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

		System.out.println("Saisir le libellé de la carte :");
		myCarte.setLibelle(sc.next());

		daoCarte.save(myCarte);
	}

	/**
	 * Modifier une carte
	 */
	public static void editCarte() {
		showCartes();

		System.out.println("Choisir la carte à modifier : ");
		Carte myCarte = daoCarte.findById(sc.nextInt());

		System.out.println(String.format("Saisir le libellé de la carte [%s]", myCarte.getLibelle()));
		myCarte.setLibelle(sc.next());

		daoCarte.save(myCarte);
	}

	/**
	 * Supprimer une carte
	 */
	public static void deleteCarte() {
		showCartes();

		System.out.println("Choisir la carte à supprimer : ");
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
}
