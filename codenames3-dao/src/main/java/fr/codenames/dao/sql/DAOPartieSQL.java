package fr.codenames.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.codenames.dao.IDAOPartie;
import fr.codenames.model.Grille;
import fr.codenames.model.Joueur;
import fr.codenames.model.Partie;

public class DAOPartieSQL extends DAOSQL implements IDAOPartie {
	public Partie map(ResultSet result) throws SQLException {
		Partie myPartie = new Partie();
		
		//ASSOCIER LES VALEURS DE LA DB A L'OBJET
		myPartie.setId(result.getInt("PAR_ID"));
		
		myPartie.setGrille(new Grille());
		myPartie.getGrille().setId(result.getInt("PAR_GRILLE_ID"));
		
		myPartie.setCapitaine(new Joueur());
		myPartie.getCapitaine().setId(result.getInt("PAR_CAPITAINE_ID"));
		
		return myPartie;
	}
	
	
	public List<Partie> findAll() {
		List<Partie> myParties = new ArrayList<Partie>();
		
		try {
			this.connect();
			Statement myStatement = this.connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("SELECT * FROM partie");

			while (myResult.next()) {
				//AJOUT DE LA PARTIE DANS LA LISTE
				myParties.add(this.map(myResult));
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myParties;
	}
	
	
	public Partie findById(int id) {
		Partie myPartie = null;
		
		try {
			this.connect();
			PreparedStatement myStatement = this.connection
					.prepareStatement("SELECT * FROM partie WHERE PAR_ID = ?");
			
			myStatement.setInt(1, id);
			ResultSet myResult = myStatement.executeQuery();

			if (myResult.next()) {
				myPartie = this.map(myResult);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myPartie;
	}
	
	
	public Partie save(Partie entity) {
		try {
			this.connect();
			String myQuery = "";
			
			if (entity.getId() == 0) { //Ajout de la partie
				myQuery = "INSERT INTO partie (PAR_GRILLE_ID, PAR_CAPITAINE_ID)"
						+ " VALUES (?, ?)";
			}
			
			else { //Mise à jour du pouvoir
				myQuery = "UPDATE partie SET PAR_GRILLE_ID = ?,"
						+ " PAR_CAPITAINE_ID = ?"
						+ " WHERE PAR_ID = ?";
			}
			
			PreparedStatement myStatement = this.connection.prepareStatement(myQuery);

			myStatement.setInt(1, entity.getGrille().getId());
			myStatement.setInt(2, entity.getCapitaine().getId());
			
			if (entity.getId() > 0) {
				myStatement.setInt(3, entity.getId());
			}
			
			myStatement.execute();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	
	public void deleteById(int id) {
		try {
			this.connect();
			PreparedStatement myStatement = this.connection
					.prepareStatement("DELETE FROM partie WHERE PAR_ID = ?");
			
			myStatement.setInt(1, id);
			myStatement.execute();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void delete(Partie entity) {
		this.deleteById(entity.getId());
	}
}