package fr.codenames;

import org.springframework.beans.factory.annotation.Autowired;

import fr.codenames.dao.IDAOCarte;
import fr.codenames.dao.IDAOUtilisateur;

public class Application {

	@Autowired
	private IDAOCarte myCarte;

	public void run(String[] args) {

		System.out.println(myCarte.findById(1).get().getLibelle());

	}
}
