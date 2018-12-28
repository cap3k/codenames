package fr.codenames.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="carte")
public class Carte {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CAR_ID")
	private int id;
	
	@Column(name="CAR_LIBELLE", nullable=false)
	@NotEmpty
	@NotNull
	private String libelle;
	
	@OneToMany(mappedBy="carte")
	private List<Case> cases;

	public List<Case> getCases() {
		return cases;
	}

	public void setCases(List<Case> cases) {
		this.cases = cases;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}