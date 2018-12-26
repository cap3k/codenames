package fr.codenames.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//TABLE_PER_CLASS
@Entity
@Table(name="joueur")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="JOU_ID")),
	@AttributeOverride(name="nom", column=@Column(name="JOU_NOM", nullable=false)),
	@AttributeOverride(name="prenom", column=@Column(name="JOU_PRENOM", nullable=false)),
	@AttributeOverride(name="username", column=@Column(name="JOU_USERNAME", nullable=false)),
	@AttributeOverride(name="password", column=@Column(name="JOU_PASSWORD", nullable=false))
})

public class Joueur extends Utilisateur {
	
	@Column(name="JOU_PSEUDO", length=100, nullable=false)
	@NotEmpty
	@NotNull
	@Size(max=100)
	private String pseudo;
	
	@Column(name="JOU_BANNI")
	private boolean banni;
	
	@OneToMany(mappedBy="message")
	private List<Joueur> joueurs;
	

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public boolean isBanni() {
		return banni;
	}

	public void setBanni(boolean banni) {
		this.banni = banni;
	}
	
	@Override
	public TypeUtilisateur getType() {
		return TypeUtilisateur.JOUEUR;
	}
}