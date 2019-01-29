package fr.codenames.applicationConsole;


import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class ConsoleApplication implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	
		System.out.println(bcrypt.encode("mdp"));

	}
}