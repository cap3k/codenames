package fr.codenames.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAOJPA {
	protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("codeNamesPU");
	protected EntityManager em = emf.createEntityManager();

	public void close() {
		em.close();
		emf.close();
	}
}
