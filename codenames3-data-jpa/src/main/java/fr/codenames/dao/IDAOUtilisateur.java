package fr.codenames.dao;

import fr.codenames.exception.UsernameOrPasswordNotFoundException;
import fr.codenames.model.Utilisateur;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import fr.codenames.exception.AccountLockedException;

@NoRepositoryBean
public interface IDAOUtilisateur<T extends Utilisateur> extends JpaRepository<T, Integer> {
	/**
	 * Methode d'authentification pour un utilisateur (Joueur ou Administrateur)
	 * @param username
	 * @param password
	 * @return
	 * @throws UsernameOrPasswordNotFoundException
	 * @throws AccountLockedException
	 */
	
	
	@Query ("select j from #{#entityName} j where j.username = :userName and j.password = :pwd")
	public T auth(@Param("userName") String username, @Param("pwd")String password) throws UsernameOrPasswordNotFoundException, AccountLockedException;

//	public void banById(int nextInt);
//
//	List<T> findAllBan();
//
//	List<T> findAllUnBan();
//
//	void unBanById(int id);
}