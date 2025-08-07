package io.github.thejeremias.salario.service;

import java.util.List;

import io.github.thejeremias.salario.dao.PessoaDao;
import io.github.thejeremias.salario.domain.Pessoa;

public class PessoaService {
	
	private final PessoaDao pessoaDao;
	
	public PessoaService() {
		pessoaDao = new PessoaDao();
	}
	
	public List<Pessoa> findPessoasSemUsuario() {
		return pessoaDao.findPessoasSemUsuario();
	}

}
