package io.github.thejeremias.salario.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
	
	private static final String NOME_BD = "salario";
	
	private static EntityManagerFactory factory = null;

	static {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory(NOME_BD);
		}
	}

	public static EntityManager createEntityManager() {
		return factory.createEntityManager();
	}

}
