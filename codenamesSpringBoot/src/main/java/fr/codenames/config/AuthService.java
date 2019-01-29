package fr.codenames.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.codenames.dao.IDAOUtilisateur;
import fr.formation.clientPrincipal.ClientPrincipal;
import fr.formation.dao.IDAOClient;

@Service
public class AuthService implements UserDetailsService {
@Autowired
IDAOUtilisateur daoUtilisateur;

public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	return new UtilisateurPrincipal(daoUtilisateur.findByEmail(username));
//...
}
}

