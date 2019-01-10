package fr.codenames;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import fr.codenames.dao.IDAOCarte;
import fr.codenames.dao.IDAOJoueur;
import fr.codenames.dao.IDAOUtilisateur;
import fr.codenames.exception.AccountLockedException;
import fr.codenames.exception.UsernameOrPasswordNotFoundException;
import fr.codenames.model.Joueur;
import fr.codenames.model.TypeUtilisateur;
import fr.codenames.model.Utilisateur;

public class Application {

	@Autowired
	private IDAOCarte myCarte;
	@Autowired
	private IDAOJoueur myUtilisateur;
	@Autowired
	private IDAOJoueur myJoueur;
	private Scanner sc;

	public void run(String[] args) {

		System.out.println(myCarte.findById(1).get().getLibelle());
		System.out.println(myUtilisateur.findById(2).get().getNom());

		connexion();
	}

	public void connexion() {
		sc = new Scanner(System.in);

		System.out.print("Indiquer le nom d'utilisateur : ");
		String username = sc.nextLine();

		System.out.print("Indiquer le mot de passe : ");
		String password = sc.nextLine();

		try {
			System.out.println("lancement authentification");
			myJoueur.auth(username, password);
			System.out.println("connecte!");
		}

		catch (UsernameOrPasswordNotFoundException e) {
			System.out.println("MAUVAIS USERNAME OU PASSWORD !!");
		}

		catch (AccountLockedException e) {
			System.out.println("COMPTE BLOQUE ... SORRY !");
		}
	}
}
