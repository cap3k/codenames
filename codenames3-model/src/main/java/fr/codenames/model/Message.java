package fr.codenames.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import fr.codenames.projection.Views;

@Entity
@Table(name = "message")

public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MES_ID")
	@JsonView(Views.Common.class)
	private int id;
	
	@Column(name="MSG_CONTENU", columnDefinition="TEXT", nullable=false)
	@JsonView(Views.Message.class)
	private String contenu;
	
	@ManyToOne
	@JoinColumn(name="MES_PARTIE_ID")
	@JsonView(Views.Message.class)
	private Partie partie;
	
	@ManyToOne
	@JoinColumn(name = "MES_JOUEUR_ID")
	@JsonView(Views.Message.class)
	private Joueur joueur;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
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
}