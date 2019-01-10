package fr.codenames;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.codenames.config.JpaConfig;

public class SpringApp {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AnnotationConfigApplicationContext myContext = new AnnotationConfigApplicationContext(JpaConfig.class);

		myContext.getBeanFactory().createBean(Application.class).run(args);
	
		myContext.close();
	}
}
