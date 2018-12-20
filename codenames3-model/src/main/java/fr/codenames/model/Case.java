package fr.codenames.model;

public class Case {
	private int id;
	private Carte carte;
	private Couleur couleur;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Carte getCarte() {
		return carte;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}
}