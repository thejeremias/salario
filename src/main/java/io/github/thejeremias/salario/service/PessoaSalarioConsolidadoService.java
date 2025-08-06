package io.github.thejeremias.salario.service;

import java.util.List;

import io.github.thejeremias.salario.dao.PessoaSalarioConsolidadoDao;
import io.github.thejeremias.salario.domain.PessoaSalarioConsolidado;
import io.github.thejeremias.salario.exception.DaoException;
import io.github.thejeremias.salario.exception.NegocioException;

public class PessoaSalarioConsolidadoService {
	
	private final PessoaSalarioConsolidadoDao pessoaSalarioConsolidadoDao;
	
	public PessoaSalarioConsolidadoService() {
		this.pessoaSalarioConsolidadoDao = new PessoaSalarioConsolidadoDao();
	}
	
	public void calcularSalariosTodos() throws NegocioException {
		try {
			pessoaSalarioConsolidadoDao.calcularSalariosTodos();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new NegocioException("Erro ao salvar o cálculo", e);
		}
	}

	public List<PessoaSalarioConsolidado> findAll() {
		return pessoaSalarioConsolidadoDao.findAll();
	}
	
	public List<PessoaSalarioConsolidado> findAllPaginado(int first, int pageSize) {
		return pessoaSalarioConsolidadoDao.findAllPaginado(first, pageSize);
	}

	public int countAll() {
		return pessoaSalarioConsolidadoDao.countAll();
	}
	
}
