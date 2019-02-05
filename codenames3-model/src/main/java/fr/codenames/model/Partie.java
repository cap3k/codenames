package fr.codenames.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;

import fr.codenames.projection.Views;

@Entity
@Table(name="partie")
public class Partie {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PAR_ID")
	@JsonView(Views.Common.class)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="PAR_GRILLE_ID")
	private Grille grille;
	
	@OneToMany(mappedBy="partie")
	private List<Message> messages;
	
	@OneToMany(mappedBy="partie", cascade = CascadeType.PERSIST)
	private List<Participation> participations;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public List<Participation> generer6participations(Joueur j) {
		List<Joueur> joueurs = new ArrayList<Joueur>();
		List<Participation> les6participations = new ArrayList<Participation> ();

		while (joueurs.size() <= 6) {
			for (int i = 0; i < 6; i++) {
				joueurs.add(j);
			}
		}
		Collections.shuffle(joueurs);
		for (int i = 0; i < 6; i++) {
			Participation newParticipation = new Participation();
			switch (i) {

			case 0:
				newParticipation.setJoueur(joueurs.get(i));
				newParticipation.setPartie(this);
				newParticipation.setRole(Role.MAITRE);
				newParticipation.setEquipe(true);
				newParticipation.setCapitaine(false);
				les6participations.add(newParticipation);
				break;
			case 1:
				newParticipation.setJoueur(joueurs.get(i));
				newParticipation.setPartie(this);
				newParticipation.setRole(Role.ESCLAVE);
				newParticipation.setEquipe(true);
				newParticipation.setCapitaine(true);
				les6participations.add(newParticipation);
				break;

			case 2:
				newParticipation.setJoueur(joueurs.get(i));
				newParticipation.setPartie(this);
				newParticipation.setRole(Role.ESCLAVE);
				newParticipation.setEquipe(true);
				newParticipation.setCapitaine(false);
				les6participations.add(newParticipation);
				break;

			case 3:
				newParticipation.setJoueur(joueurs.get(i));
				newParticipation.setPartie(this);
				newParticipation.setRole(Role.MAITRE);
				newParticipation.setEquipe(false);
				newParticipation.setCapitaine(false);
				les6participations.add(newParticipation);
				break;

			case 4:
				newParticipation.setJoueur(joueurs.get(i));
				newParticipation.setPartie(this);
				newParticipation.setRole(Role.ESCLAVE);
				newParticipation.setEquipe(false);
				newParticipation.setCapitaine(true);
				les6participations.add(newParticipation);
				break;

			case 5:
				newParticipation.setJoueur(joueurs.get(i));
				newParticipation.setPartie(this);
				newParticipation.setRole(Role.ESCLAVE);
				newParticipation.setEquipe(false);
				newParticipation.setCapitaine(false);
				les6participations.add(newParticipation);
				break;
			}
		}
		return this.participations= les6participations;
	}
	
	
	
	
}