package io.github.thejeremias.salario.persistence;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;


public class EntityManagerFactorySingletonTest {
	
	@Test
	public void givenEntityManagerFactory_whenEntityManagerCreated_thenNotNullAndOpen() {
	    EntityManager entityManager = EntityManagerFactorySingleton.createEntityManager();
	    try {
	        Assert.assertNotNull("EntityManager deveria ter sido criado", entityManager);
	        Assert.assertTrue("EntityManager deveria estar aberto", entityManager.isOpen());
	    } finally {
	        if (entityManager != null && entityManager.isOpen()) {
	            entityManager.close();
	        }
	    }
	}
	
}
