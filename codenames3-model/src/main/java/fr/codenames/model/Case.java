package fr.codenames.model;

import javax.persistence.*;



@Entity
@Table(name="[case]")
public class Case {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CAS_ID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="CAS_CARTE_ID")
	private Carte carte;
	
	@Column(name="CAS_COULEUR")
	@Enumerated(EnumType.ORDINAL)
	private Couleur couleur;
	
	@ManyToOne
	@JoinColumn(name="CAS_GRILLE_ID")
	private Grille grille;
	
	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

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