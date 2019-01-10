package fr.codenames.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.codenames.model.Utilisateur;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=fr.codenames.config.JpaConfig.class)

public class IDAOUtilisateurTest {
	
	@Autowired(required=false)
	private IDAOJoueur daoUtilisateur;
	
	@Autowired(required=false)
	private IDAOAdministrateur daoAdmin;
	
	@BeforeClass
	public static void beforeAll() {
		System.out.println("------ DEBUT DU TEST ------");
	}
	
	@Test
	public void authJoueurTest() {
		Utilisateur myOptionnalUtilisateur = daoUtilisateur.auth("dydy","mdp");
		assertNotNull("Utilisateur trouvé mais résultat nul", myOptionnalUtilisateur);
		assertNotNull("Utilisateur trouvé mais infos non remontées", myOptionnalUtilisateur.getNom());
	}
	
	@Test
	public void authAdminTest() {
		Utilisateur myOptionnalUtilisateur = daoAdmin.auth("jeje","mdp");
		assertNotNull("Utilisateur trouvé mais résultat nul", myOptionnalUtilisateur);
		assertNotNull("Utilisateur trouvé mais infos non remontées", myOptionnalUtilisateur.getNom());
	}
	
}
