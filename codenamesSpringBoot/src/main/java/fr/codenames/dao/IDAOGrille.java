package fr.codenames.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import fr.codenames.model.Case;
import fr.codenames.model.Grille;


public interface IDAOGrille extends JpaRepository<Grille, Integer> {
	public List<Case> findCaseByGrilleID(@Param("id") int id);
	
}
