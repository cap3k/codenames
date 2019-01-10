package fr.codenames.dao.jpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.codenames.dao.IDAOUtilisateur;
import fr.codenames.exception.AccountLockedException;
import fr.codenames.exception.UsernameOrPasswordNotFoundException;
import fr.codenames.model.Administrateur;
import fr.codenames.model.Joueur;
import fr.codenames.model.Password;
import fr.codenames.model.Utilisateur;

public class DAOUtilisateurJPA extends DAOJPA implements IDAOUtilisateur {

	@Override
	public List<Utilisateur> findAll() {
		return em.createQuery("select j from Joueur j", Utilisateur.class).getResultList();
	}
	
	@Override
	public List<Utilisateur> findAllUnBan() {
		return em.createQuery("select j from Joueur j where j.banni = 0", Utilisateur.class).getResultList();
	}
	
	@Override
	public List<Utilisateur> findAllBan() {
		return em.createQuery("select j from Joueur j where j.banni = 1", Utilisateur.class).getResultList();
	}

	@Override
	public Utilisateur findById(int id) {
		return em.find(Utilisateur.class, id);
	}

	@Override
	public Utilisateur save(Utilisateur entity) {
		// On demarre la transaction
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
		em.getTransaction().begin();
		em.remove(em.merge(entity));
		em.getTransaction().commit();

	}

	@Override
	public void deleteById(int id) {

		Joueur myJoueur = new Joueur();
		myJoueur.setId(id);
		this.delete(myJoueur);

	}

	@Override
	public void banById(int id) {
		em.getTransaction().begin();
		Query myQuery = em.createQuery("UPDATE Joueur j SET j.banni = 1 WHERE j.id = :id");
		myQuery.setParameter("id", id);
		int updt = myQuery.executeUpdate();
		em.getTransaction().commit();
	}
	
	@Override
	public void unBanById(int id) {
		em.getTransaction().begin();
		Query myQuery = em.createQuery("UPDATE Joueur j SET j.banni = 0 WHERE j.id = :id");
		myQuery.setParameter("id", id);
		int updt = myQuery.executeUpdate();
		em.getTransaction().commit();
	}

	@Override
	public Utilisateur auth(String username, String password)
			throws UsernameOrPasswordNotFoundException, AccountLockedException {
		try {
			TypedQuery<Joueur> myQueryJ = em.createQuery(
					"select j from Joueur j where j.username = :userName", Joueur.class);
			myQueryJ.setParameter("userName", username);
			

			Joueur monJoueur = myQueryJ.getSingleResult();
			boolean passwordcheck = false;
			try {
				passwordcheck = Password.check(password,monJoueur.getPassword());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (monJoueur.isBanni()) {
				System.out.println("banni!");
				throw new AccountLockedException();
				
			}
			else if
			(passwordcheck ==true) {
			System.out.println("bienvenu joueur");
			return monJoueur;
			}
			else if
			(passwordcheck==false) {
				System.out.println("Mauvais mot de passe");
			}

		} catch (NoResultException e) {
			try {
				TypedQuery<Administrateur> myQueryA = em.createQuery(
						"select a from Administrateur a where a.username = :userName and a.password = :pwd",
						Administrateur.class);
				myQueryA.setParameter("userName", username);
				myQueryA.setParameter("pwd", password);
				Administrateur monAdmin = myQueryA.getSingleResult();
				System.out.println("bienvenu admin");
				return monAdmin;

			} catch (Exception e2) {
				throw new UsernameOrPasswordNotFoundException();
			}

		}
		return null;
	}
	
}
