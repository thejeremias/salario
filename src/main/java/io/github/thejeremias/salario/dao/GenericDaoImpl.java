package io.github.thejeremias.salario.dao;

import java.util.List;

import javax.persistence.EntityManager;

import io.github.thejeremias.salario.domain.PersistEntity;
import io.github.thejeremias.salario.exception.DaoException;
import io.github.thejeremias.salario.persistence.EntityManagerFactorySingleton;

public class GenericDaoImpl<T extends PersistEntity> implements GenericDao<T> {

    private final Class<T> entityClass;
	
	protected EntityManager entityManager;
	
    public GenericDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.entityManager = EntityManagerFactorySingleton.createEntityManager();
    }
	
	@Override
	public T findById(Long id) {
		return entityManager.find(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
        return entityManager.createQuery(jpql, entityClass).getResultList();
	}

	@Override
	public T save(T entity) {
		return entityManager.merge(entity);
	}

	@Override
	public void deleteById(Long id) throws DaoException {
		 T entity = findById(id);
	     if (entity == null) {
	    	 throw new DaoException("DAO: registro não existe.");
	     }	
	     entityManager.remove(entity);
	}
	
}
