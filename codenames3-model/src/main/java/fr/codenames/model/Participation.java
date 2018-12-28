package fr.codenames.model;

import javax.persistence.*;

@Entity
@Table(name = "participation")

public class Participation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PAT_ID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "PAT_PARTIE_ID")
	private Partie partie;
	
	@ManyToOne
	@JoinColumn(name = "PAT_JOUEUR_ID")
	private Joueur joueur;
	
	@Enumerated (EnumType.ORDINAL)
	private Role role;
	
	@Column (name = "PAT_EQUIPE")
	private Boolean equipe;
	
	@Column (name = "PAT_CAPITAINE")
	private Boolean capitaine;

	public Boolean getCapitaine() {
		return capitaine;
	}

	public void setCapitaine(Boolean capitaine) {
		this.capitaine = capitaine;
	}

	public Boolean getEquipe() {
		return equipe;
	}

	public void setEquipe(Boolean equipe) {
		this.equipe = equipe;
	}

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