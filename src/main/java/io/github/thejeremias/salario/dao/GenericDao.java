package io.github.thejeremias.salario.dao;

import java.util.List;

import io.github.thejeremias.salario.domain.PersistEntity;
import io.github.thejeremias.salario.exception.DaoException;

public interface GenericDao<T extends PersistEntity> {
	
	 T findById(Long id) throws DaoException;
	 
	 List<T> findAll() throws DaoException;
	 
	 List<T> findAllPaginado(int first, int pageSize) throws DaoException;
	   
	 T save(T entity) throws DaoException;
	    	    
	 void deleteById(Long id) throws DaoException;
	 
	 int countAll();

}
