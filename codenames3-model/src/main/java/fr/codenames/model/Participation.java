package fr.codenames.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "participation")

public class Participation {
	
	@ManyToOne
	@JoinColumn(name = "PAT_PARTIE_ID")
	private Partie partie;
	
	@ManyToOne
	@JoinColumn(name = "PAT_JOUEUR_ID")
	private Joueur joueur;
	
	@Enumerated (EnumType.ORDINAL)
	private Role role;

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}