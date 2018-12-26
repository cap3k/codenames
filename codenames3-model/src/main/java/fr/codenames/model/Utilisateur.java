package fr.codenames.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "utilisateur")

public abstract class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UTI_ID")
	private int id;
	
	@Column(name="UTI_NOM", length=100, nullable=false)
	@NotEmpty
	@NotNull
	@Size(max=100)
	private String nom;
	
	@Column(name="UTI_PRENOM", length=100, nullable=false)
	@NotEmpty
	@NotNull
	@Size(max=100)
	private String prenom;
	
	@Column(name="UTI_USERNAME", length=100, nullable=false)
	@NotEmpty
	@NotNull
	@Size(max=100)
	private String username;
	
	@Column(name="UTI_PASSWORD", nullable=false, length=300)
	@NotEmpty
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public abstract TypeUtilisateur getType();
}