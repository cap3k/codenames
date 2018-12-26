package fr.codenames.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import fr.codenames.dao.IDAOUtilisateur;
import fr.codenames.exception.AccountLockedException;
import fr.codenames.exception.UsernameOrPasswordNotFoundException;
import fr.codenames.model.Administrateur;
import fr.codenames.model.Joueur;
import fr.codenames.model.TypeUtilisateur;
import fr.codenames.model.Utilisateur;

public class DAOUtilisateurJPA extends DAOJPA implements IDAOUtilisateur {

	@Override
	public List<Utilisateur> findAll() {
		return em.createQuery("select p from Produit p", Utilisateur.class).getResultList();
	}

	@Override
	public Utilisateur findById(int id) {
		return em.find(Utilisateur.class, id);
	}

	@Override
	public Utilisateur save(Utilisateur entity) {
		// On démarre la transaction
		em.getTransaction().begin();

		if (entity.getId() == 0) {
			em.persist(entity);
		}

		else {
			entity = em.merge(entity);
		}

		// On commit la transaction
		em.getTransaction().commit();

		return entity;
	}

	@Override
	public void delete(Utilisateur entity) {
		em.remove(em.merge(entity));

	}

	@Override
	public void deleteById(int id) {
		Query myQuery = em.createQuery("delete u from Utilisateur u where u.id = :id", Utilisateur.class);
		// On insère les paramètres
		myQuery.setParameter("id", id);

		myQuery.executeUpdate();
	}

	@Override
	public Utilisateur auth(String username, String password)
			throws UsernameOrPasswordNotFoundException, AccountLockedException {
		// TODO Auto-generated method stub
		return null;
	}

}
