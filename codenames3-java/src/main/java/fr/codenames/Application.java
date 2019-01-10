package fr.codenames;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import fr.codenames.dao.IDAOAdministrateur;
import fr.codenames.dao.IDAOCarte;
import fr.codenames.dao.IDAOJoueur;
import fr.codenames.exception.AccountLockedException;
import fr.codenames.exception.UsernameOrPasswordNotFoundException;

public class Application {

	@Autowired
	private IDAOCarte myCarte;
	@Autowired
	private IDAOJoueur myJoueur;
	@Autowired
	private IDAOAdministrateur myAdministrateur;

	private Scanner sc;

	public void run(String[] args) {

		System.out.println(myCarte.findById(1).get().getLibelle());
		System.out.println(myJoueur.findById(2).get().getNom());

		try {
			connexion();
		} catch (AccountLockedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsernameOrPasswordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void connexion() throws AccountLockedException, UsernameOrPasswordNotFoundException {
		sc = new Scanner(System.in);

		System.out.println("----- CONNEXION -----");
		System.out.print("Indiquer le nom d'utilisateur : ");
		sc.nextLine();
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
}
