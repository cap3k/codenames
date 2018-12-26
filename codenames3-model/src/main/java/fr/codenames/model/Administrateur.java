package fr.codenames.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//TABLE_PER_CLASS
@Entity
@Table(name="administrateur")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="ADM_ID")),
	@AttributeOverride(name="nom", column=@Column(name="ADM_NOM", nullable=false)),
	@AttributeOverride(name="prenom", column=@Column(name="ADM_PRENOM", nullable=false)),
	@AttributeOverride(name="username", column=@Column(name="ADM_USERNAME", nullable=false)),
	@AttributeOverride(name="password", column=@Column(name="ADM_PASSWORD", nullable=false))
})
public class Administrateur extends Utilisateur {
	@Override
	public TypeUtilisateur getType() {
		return TypeUtilisateur.ADMINISTRATEUR;
	}
}
