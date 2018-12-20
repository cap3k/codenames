package fr.codenames.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import fr.codenames.dao.IDAOUtilisateur;
import fr.codenames.exception.AccountLockedException;
import fr.codenames.exception.UsernameOrPasswordNotFoundException;
import fr.codenames.model.Administrateur;
import fr.codenames.model.Joueur;
import fr.codenames.model.TypeUtilisateur;
import fr.codenames.model.Utilisateur;
import fr.codenames.exception.NonUniqueUsernameException;

public class DAOUtilisateurSQL extends DAOSQL implements IDAOUtilisateur {
	public List<Utilisateur> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Utilisateur findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Utilisateur save(Utilisateur entity) {
		try {
			this.connect();
			String myQuery = "";
			
			if (entity.getId() == 0) { //Ajout de l'utilisateur
				myQuery = "INSERT INTO utilisateur (UTI_NOM, UTI_PRENOM, UTI_USERNAME, UTI_PASSWORD, UTI_TYPE, UTI_PSEUDO)"
						+ " VALUES (?, ?, ?, ?, ?, ?)";
			}
			
			else { //Mise à jour de l'utilisateur
				myQuery = "UPDATE utilisateur SET UTI_NOM = ?,"
						+ "	UTI_PRENOM = ?, "
						+ " UTI_USERNAME = ?, "
						+ " UTI_PASSWORD = ?, "
						+ " UTI_TYPE = ?, "
						+ " UTI_PSEUDO = ? "
						+ " WHERE UTI_ID = ?";
			}
			
			PreparedStatement myStatement = this.connection.prepareStatement(myQuery);

			myStatement.setString(1, entity.getNom());
			myStatement.setString(2, entity.getPrenom());
			myStatement.setString(3, entity.getUsername());
			myStatement.setString(4, entity.getPassword());
			myStatement.setInt(5, entity.getType().ordinal());
			
			if (entity.getType() == TypeUtilisateur.JOUEUR) {
				myStatement.setString(6, ((Joueur)entity).getPseudo());
			}
			
			if (entity.getId() > 0) {
				myStatement.setInt(7, entity.getId());
			}
			
			myStatement.execute();
		}
		
		catch (SQLIntegrityConstraintViolationException e) {
			throw new NonUniqueUsernameException();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return entity;
	}

	public void delete(Utilisateur entity) {
		// TODO Auto-generated method stub
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
	}

	public Utilisateur auth(String username, String password) throws UsernameOrPasswordNotFoundException, AccountLockedException {
		try {
			this.connect();
			PreparedStatement myStatement = this.connection
					.prepareStatement("SELECT * FROM utilisateur WHERE UTI_USERNAME = ? AND UTI_PASSWORD = ?");

			myStatement.setString(1, username);
			myStatement.setString(2, password);
			ResultSet myResult = myStatement.executeQuery();

			if (myResult.next()) {
				Utilisateur myUtilisateur = this.map(myResult);
				
				if (myUtilisateur.getType() == TypeUtilisateur.JOUEUR) {
					if (((Joueur)myUtilisateur).isBanni()) {
						throw new AccountLockedException();
					}
				}
				
				return myUtilisateur;
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		throw new UsernameOrPasswordNotFoundException();
	}
	
	
	
	
	public Utilisateur map(ResultSet result) throws SQLException {
		Utilisateur myUtilisateur = null;
		
		if (result.getInt("UTI_TYPE") == TypeUtilisateur.JOUEUR.ordinal()) {
			myUtilisateur = new Joueur();
			((Joueur)myUtilisateur).setPseudo(result.getString("UTI_PSEUDO"));
			((Joueur)myUtilisateur).setBanni(result.getBoolean("UTI_EST_BANNI"));
		}
		
		else {
			myUtilisateur = new Administrateur();
		}
		
		//ASSOCIER LES VALEURS DE LA DB A L'OBJET
		myUtilisateur.setId(result.getInt("UTI_ID"));
		myUtilisateur.setNom(result.getString("UTI_NOM"));
		myUtilisateur.setPrenom(result.getString("UTI_PRENOM"));
		myUtilisateur.setUsername(result.getString("UTI_USERNAME"));
		
		return myUtilisateur;
	}
}