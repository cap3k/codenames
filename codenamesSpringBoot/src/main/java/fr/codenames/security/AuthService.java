package fr.codenames.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.codenames.dao.IDAOAdministrateur;
import fr.codenames.dao.IDAOJoueur;
import fr.codenames.dao.IDAOUtilisateur;

@Service
public class AuthService implements UserDetailsService {
	@Autowired
	IDAOAdministrateur daoAdministrateur;
	@Autowired
	IDAOJoueur daoJoueur;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (daoAdministrateur.findByUsername(username)!=null) {
			return new UtilisateurPrincipal(daoAdministrateur.findByUsername(username));
		}
		else if (daoJoueur.findByUsername(username)!=null) {
			return new UtilisateurPrincipal(daoJoueur.findByUsername(username));
		}
		throw new UsernameNotFoundException(username);
	}
}