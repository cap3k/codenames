package fr.codenames.dao;

import fr.codenames.exception.UsernameOrPasswordNotFoundException;
import fr.codenames.model.Utilisateur;

import java.util.List;

import fr.codenames.exception.AccountLockedException;

public interface IDAOUtilisateur extends IDAO<Utilisateur> {
	/**
	 * Méthode d'authentification pour un utilisateur (Joueur ou Administrateur)
	 * @param username
	 * @param password
	 * @return
	 * @throws UsernameOrPasswordNotFoundException
	 * @throws AccountLockedException
	 */
	public Utilisateur auth(String username, String password) throws UsernameOrPasswordNotFoundException, AccountLockedException;

	public void banById(int nextInt);

	List<Utilisateur> findAllBan();

	List<Utilisateur> findAllUnBan();

	void unBanById(int id);
}