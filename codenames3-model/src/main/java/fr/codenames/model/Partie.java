package fr.codenames.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="partie")
public class Partie {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PAR_ID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="PAR_GRILLE_ID")
	private Grille grille;
	
	@ManyToOne
	@JoinColumn(name="PAR_CAPITAINE_ID")
	private Joueur capitaine;
	
	@OneToMany(mappedBy="partie")
	private List<Message> messages;
	
	@OneToMany(mappedBy="partie")
	private List<Participation> participation;

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

	public Joueur getCapitaine() {
		return capitaine;
	}

	public void setCapitaine(Joueur capitaine) {
		this.capitaine = capitaine;
	}
	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}