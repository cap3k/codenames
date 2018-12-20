package fr.codenames.model;

public class Joueur extends Utilisateur {
	private String pseudo;
	private boolean banni;

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public boolean isBanni() {
		return banni;
	}

	public void setBanni(boolean banni) {
		this.banni = banni;
	}
	
	@Override
	public TypeUtilisateur getType() {
		return TypeUtilisateur.JOUEUR;
	}
}