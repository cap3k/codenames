package fr.codenames.model;

public class Administrateur extends Utilisateur {
	@Override
	public TypeUtilisateur getType() {
		return TypeUtilisateur.ADMINISTRATEUR;
	}
}
