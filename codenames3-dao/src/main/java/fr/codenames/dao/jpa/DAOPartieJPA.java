package fr.codenames.dao.jpa;

import java.util.List;

import fr.codenames.dao.IDAOPartie;
import fr.codenames.model.Partie;


public class DAOPartieJPA extends DAOJPA implements IDAOPartie {

	@Override
	public List<Partie> findAll() {
		// TODO Auto-generated method stub
		return em
				.createQuery("select p from partie p", Partie.class)
				.getResultList();
	}

	@Override
	public Partie findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Partie.class, id);
	}

	@Override
	public Partie save(Partie entity) {
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
	public void delete(Partie entity) {
		// TODO Auto-generated method stub
		em.remove(em.merge(entity));
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Partie myPartie = new Partie();
		myPartie.setId(id);
		this.delete(myPartie);
	}

}
