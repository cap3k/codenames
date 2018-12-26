package fr.codenames.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="grille")
public class Grille {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="GRI_ID")
	private int id;
	
	@OneToMany(mappedBy="grille")
	private List<Partie> parties;
	
	
	@Column(name="GRI_DIFFICULTE")
	@Enumerated(EnumType.ORDINAL)
	private Difficulte difficulte;
	
	@OneToMany(mappedBy="grille", cascade = CascadeType.PERSIST)
	private List<Case> cases;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Difficulte getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(Difficulte difficulte) {
		this.difficulte = difficulte;
	}
	
	public void generer25Cases(List<Carte> cartesRandom) {
		for(int i=0; i<25; i++) {
			this.cases.get(i).setCarte(cartesRandom.get(i));
			this.cases.get(i).setCouleur(Couleur.BLEUE);
		}
		
	}
}