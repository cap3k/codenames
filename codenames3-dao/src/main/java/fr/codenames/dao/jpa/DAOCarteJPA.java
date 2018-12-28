package fr.codenames.dao.jpa;

import java.util.List;

import fr.codenames.dao.IDAOCarte;
import fr.codenames.model.Carte;


public class DAOCarteJPA extends DAOJPA implements IDAOCarte {

	@Override
	public List<Carte> findAll() {
		// TODO Auto-generated method stub
		return em
				.createQuery("select c from Carte c", Carte.class)
				.getResultList();
		
	}

	@Override
	public Carte findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Carte.class, id);
	}

	@Override
	public Carte save(Carte entity) {
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
	public void delete(Carte entity) {
		em.getTransaction().begin();
		// TODO Auto-generated method stub
		em.remove(em.merge(entity));
		em.getTransaction().commit();
	}

	@Override
	public void deleteById(int id) {
		
		// TODO Auto-generated method stub
		Carte myCarte = new Carte();
		myCarte.setId(id);
		this.delete(myCarte);
	}
}
