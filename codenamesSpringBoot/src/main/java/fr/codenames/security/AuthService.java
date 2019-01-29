package fr.codenames.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.codenames.dao.IDAOJoueur;
import fr.codenames.dao.IDAOUtilisateur;

@Service
public class AuthService implements UserDetailsService {
	@Autowired
	//IDAOUtilisateur daoUtilisateur;
	IDAOJoueur daoJoueur;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new UtilisateurPrincipal(daoJoueur.findByUsername(username));
	}
}