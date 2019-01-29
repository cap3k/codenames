package fr.codenames.utilisateurPrincipal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.codenames.model.Utilisateur;
import fr.formation.model.Client;

public class UtilisateurPrincipal implements UserDetails {
	private Utilisateur utilisateur;

	public UtilisateurPrincipal(Utili client) {
		if (client == null) {
			throw new UsernameNotFoundException("L'utilisateur n'existe pas.");
		}
		this.client = client;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> myAuthorities = new ArrayList<GrantedAuthority>();
		myAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return myAuthorities;

	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return this.client.getPassword();

	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return this.client.getEmail();
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
