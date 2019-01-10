package fr.codenames;


import org.springframework.beans.factory.annotation.Autowired;

import fr.codenames.dao.IDAOUtilisateur;



public class Application {

	@Autowired
	private IDAOUtilisateur myUtilisateur;
	

	public  void run(String[] args) {
		
		System.out.println(myUtilisateur.findById(1).get().getNom());
		

}
}
