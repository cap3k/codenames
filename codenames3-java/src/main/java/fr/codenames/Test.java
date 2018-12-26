package fr.codenames;

import java.util.Scanner;

import fr.codenames.dao.IDAOUtilisateur;
import fr.codenames.dao.jpa.DAOUtilisateurJPA;
import fr.codenames.exception.NonUniqueUsernameException;
import fr.codenames.model.Joueur;

public class Test {
	private static Scanner sc;

	public static void main(String[] args) {
		
		sc = new Scanner(System.in);
		creerJoueur();
		System.out.println("Fin Programme");

		sc.close();
	}

	public static void creerJoueur() {
		IDAOUtilisateur daoUtilisateur = new DAOUtilisateurJPA();

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


	}

}
