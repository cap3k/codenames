package fr.codenames.dao.jpa;

import java.util.List;

import fr.codenames.dao.IDAOGrille;
import fr.codenames.model.Grille;


public class  DAOGrilleJPA extends DAOJPA implements IDAOGrille {

	@Override
	public List<Grille> findAll() {
		// TODO Auto-generated method stub
		return em
				.createQuery("select g from grille g", Grille.class)
				.getResultList();
	}

	@Override
	public Grille findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Grille.class, id);
	}

	@Override
	public Grille save(Grille entity) {
		// TODO Auto-generated method stub
		//On demarre la transaction
		em.getTransaction().begin();
		
		if (entity.getId() == 0) {
			em.persist(entity);
		}
		
		else {
			entity = em.merge(entity);
		}
		
		//On commit la transaction
		em.getTransaction().commit();
		
		return entity;
	}

	@Override
	public void delete(Grille entity) {
		// TODO Auto-generated method stub
		em.remove(em.merge(entity));
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Grille myGrille = new Grille();
		myGrille.setId(id);
		this.delete(myGrille);
	}


}
