package io.github.thejeremias.salario.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	public T save(T entity) throws DaoException {
		try {
			entityManager.getTransaction().begin();
			entity = entityManager.merge(entity);
			entityManager.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			throw new DaoException("Erro ao salvar registro", e);
		}
		return entity;
	}

	@Override
	public void deleteById(Long id) throws DaoException {
		 T entity = findById(id);
	     if (entity == null) {
	    	 throw new DaoException("DAO: registro não existe.");
	     }	
	     try {
			entityManager.getTransaction().begin();
			entityManager.remove(entity);
			entityManager.getTransaction().commit();
		  } catch(Exception e) {
				e.printStackTrace();
				if (entityManager.getTransaction().isActive()) {
					entityManager.getTransaction().rollback();
				}
				throw new DaoException("Erro ao remover registro", e);
		  }
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllPaginado(int first, int pageSize) {
		String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
		Query query = entityManager.createQuery(jpql, entityClass);
		query.setFirstResult(first);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public int countAll() {
		String jpql = "SELECT count(e) FROM " + entityClass.getSimpleName() + " e";
		return ((Long) entityManager.createQuery(jpql, Long.class).getSingleResult()).intValue();
	}
	
}
