package fr.codenames.dao;

import fr.codenames.model.Utilisateur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface IDAOUtilisateur<T extends Utilisateur> extends JpaRepository<T, Integer> {

	@Query ("select j from #{#entityName} j where j.username = :userName and j.password = :pwd")
	public T auth(@Param("userName") String username, @Param("pwd")String password);

	@Query ("select j from Joueur j where j.username = :userName and j.banni = true")
	public T authBanni(@Param("userName") String username);
	
	public Utilisateur findByUsername(String username);
	
}