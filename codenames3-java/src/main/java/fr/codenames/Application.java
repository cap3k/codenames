package fr.codenames;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import fr.codenames.dao.IDAOAdministrateur;
import fr.codenames.dao.IDAOCarte;
import fr.codenames.dao.IDAOJoueur;
import fr.codenames.exception.AccountLockedException;
import fr.codenames.exception.NonUniqueUsernameException;
import fr.codenames.exception.UsernameOrPasswordNotFoundException;
import fr.codenames.model.Joueur;
import fr.codenames.model.Password;

public class Application {

	@Autowired
	private IDAOCarte myCarte;
	@Autowired
	private IDAOJoueur myJoueur;
	@Autowired
	private IDAOAdministrateur myAdministrateur;

	private static Scanner sc;

	public void run(String[] args) {

//		System.out.println(myCarte.findById(1).get().getLibelle());
//		System.out.println(myJoueur.findById(2).get().getNom());

		try {
			connexion();
		} catch (AccountLockedException e) {
			System.out.println("compte bloqué");
		} catch (UsernameOrPasswordNotFoundException e) {
			System.out.println("username ou mdp incorrect");
		}
	}

	public void connexion() throws AccountLockedException, UsernameOrPasswordNotFoundException {
		sc = new Scanner(System.in);

		System.out.println("----- CONNEXION -----");
		System.out.print("Indiquer le nom d'utilisateur : ");
		String username = sc.nextLine();

		System.out.print("Indiquer le mot de passe : ");
		String password = sc.nextLine();

		System.out.println("lancement authentification");
		if (myJoueur.auth(username, password) != null || myAdministrateur.auth(username, password) != null) {

			if (myJoueur.authBanni(username) == null) {
				System.out.println("connecte!");
			} else {
				throw new AccountLockedException();
			}
		} else {
			throw new UsernameOrPasswordNotFoundException();
		}
	}
	 
	public void inscription() {
		
		Joueur myNewJoueur = new Joueur();
		
		System.out.println("---------- INSCRIPTION -----------");
		System.out.print("Indiquer votre nom : ");		
		myNewJoueur.setNom(sc.nextLine());

		System.out.print("Indiquer votre prenom : ");
		myNewJoueur.setPrenom(sc.nextLine());

		System.out.print("Indiquer votre pseudo : ");
		myNewJoueur.setPseudo(sc.nextLine());

		System.out.print("Indiquer le nom d'utilisateur : ");
		myNewJoueur.setUsername(sc.nextLine());
				
		System.out.print("Indiquer le mot de passe : ");
		myNewJoueur.setPassword(sc.nextLine());

		myJoueur.save(myNewJoueur);

	}
}
