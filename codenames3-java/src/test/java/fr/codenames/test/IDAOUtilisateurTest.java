package fr.codenames.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.codenames.dao.IDAOAdministrateur;
import fr.codenames.dao.IDAOJoueur;
import fr.codenames.dao.IDAOUtilisateur;
import fr.codenames.model.Utilisateur;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=fr.codenames.config.JpaConfig.class)
public class IDAOUtilisateurTest {
	
	@Autowired(required = false)
	private IDAOJoueur daoJoueur;
	
	@Autowired(required = false)
	private IDAOAdministrateur daoAdministrateur;
	
	@Test
	public void testonsAuthentification() {
		Utilisateur myOptionalUtilisateur=daoJoueur.auth("dydy", "mdp");
		assertNotNull("L'utilisateur éxiste", myOptionalUtilisateur);
	}
	
	@Test
	public void testonsAuthentification2() {
		assertEquals("Liner", daoJoueur.auth("dydy", "mdp").getNom());
	}
}
