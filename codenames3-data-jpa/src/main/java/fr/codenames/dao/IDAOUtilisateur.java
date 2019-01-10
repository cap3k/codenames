package fr.codenames.dao;

import fr.codenames.exception.UsernameOrPasswordNotFoundException;
import fr.codenames.model.Utilisateur;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import fr.codenames.exception.AccountLockedException;
@NoRepositoryBean
public interface IDAOUtilisateur<T> extends JpaRepository<Utilisateur, Integer> {
	/**
	 * Methode d'authentification pour un utilisateur (Joueur ou Administrateur)
	 * @param username
	 * @param password
	 * @return
	 * @throws UsernameOrPasswordNotFoundException
	 * @throws AccountLockedException
	 */
	public T auth(String username, String password) throws UsernameOrPasswordNotFoundException, AccountLockedException;

	public void banById(int nextInt);

	List<T> findAllBan();

	List<T> findAllUnBan();

	void unBanById(int id);
}