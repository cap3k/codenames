package fr.codenames.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.codenames.dao.IDAOCarte;
import fr.codenames.model.Carte;

public class DAOCarteSQL extends DAOSQL implements IDAOCarte {
	public Carte map(ResultSet result) throws SQLException {
		Carte myCarte = new Carte();
		
		//ASSOCIER LES VALEURS DE LA DB A L'OBJET
		myCarte.setId(result.getInt("CAR_ID"));
		myCarte.setLibelle(result.getString("CAR_LIBELLE"));
		
		return myCarte;
	}
	
	
	public List<Carte> findAll() {
		List<Carte> myCartes = new ArrayList<Carte>();
		
		try {
			this.connect();
			Statement myStatement = this.connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("SELECT * FROM carte");

			while (myResult.next()) {
				//AJOUT DE LA CARTE DANS LA LISTE
				myCartes.add(this.map(myResult));
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myCartes;
	}
	
	
	public Carte findById(int id) {
		Carte myCarte = null;
		
		try {
			this.connect();
			PreparedStatement myStatement = this.connection
					.prepareStatement("SELECT * FROM carte WHERE CAR_ID = ?");
			
			myStatement.setInt(1, id);
			ResultSet myResult = myStatement.executeQuery();

			if (myResult.next()) {
				myCarte = this.map(myResult);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myCarte;
	}
	
	
	public Carte save(Carte entity) {
		try {
			this.connect();
			String myQuery = "";
			
			if (entity.getId() == 0) { //Ajout de la carte
				myQuery = "INSERT INTO carte (CAR_LIBELLE)"
						+ " VALUES (?)";
			}
			
			else { //Mise à jour de la carte
				myQuery = "UPDATE carte SET CAR_LIBELLE = ?"
						+ " WHERE CAR_ID = ?";
			}
			
			PreparedStatement myStatement = this.connection.prepareStatement(myQuery);
			
			myStatement.setString(1, entity.getLibelle());
			
			if (entity.getId() > 0) {
				myStatement.setInt(2, entity.getId());
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
					.prepareStatement("DELETE FROM carte WHERE CAR_ID = ?");
			
			myStatement.setInt(1, id);
			myStatement.execute();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void delete(Carte entity) {
		this.deleteById(entity.getId());
	}
}