package fr.codenames.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	public void generer25Cases(List<Carte> cartesRandom, Difficulte d) {
		 		 
		    List<Case> les25cases = new ArrayList<Case> ();
			int m = 9; // nombre de cas à deviner pour l'équipe qui commence
			Random random = new Random();
			int nbNoire = d.ordinal();
			if (nbNoire == 2) {
				nbNoire = 8;
			}
			if (random.nextBoolean() == true) {
				m = 9;
			} else {
				m = 8;
			}

			for (int i = 0; i < m; i++) {
				Case case1 = new Case();
				case1.setCarte(cartesRandom.get(i));
				case1.setCouleur(Couleur.BLEUE);
				case1.setGrille(this);
				les25cases.add(case1);
			}

			for (int i = m; i < 17; i++) {
				Case case1 = new Case();
				case1.setCarte(cartesRandom.get(i));
				case1.setCouleur(Couleur.ROUGE);
				case1.setGrille(this);
				les25cases.add(case1);
			}

			for (int i = 17; i < 25 - nbNoire; i++) {
				Case case1 = new Case();
				case1.setCarte(cartesRandom.get(i));
				case1.setCouleur(Couleur.NEUTRE);
				case1.setGrille(this);
				les25cases.add(case1);
			}

			for (int i = 25 - nbNoire; i < 25; i++) {
				Case case1 = new Case();
				case1.setCarte(cartesRandom.get(i));
				case1.setCouleur(Couleur.NOIRE);
				case1.setGrille(this);
				les25cases.add(case1);
			}
			
			this.cases=les25cases;
		}
		
}