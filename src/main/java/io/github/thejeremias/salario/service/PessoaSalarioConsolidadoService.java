package io.github.thejeremias.salario.service;

import java.util.HashMap;
import java.util.List;


import io.github.thejeremias.salario.dao.PessoaSalarioConsolidadoDao;
import io.github.thejeremias.salario.domain.PessoaSalarioConsolidado;
import io.github.thejeremias.salario.dto.FiltroPessoaSalarioConsolidadoDto;
import io.github.thejeremias.salario.exception.DaoException;
import io.github.thejeremias.salario.exception.NegocioException;
import io.github.thejeremias.salario.util.JasperUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

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
	
	public JasperPrint gerarRelatorioPessoasSalariosConsolidados(FiltroPessoaSalarioConsolidadoDto filtroPessoaSalarioConsolidadoDto) throws NegocioException {	
		try {
			return JasperUtil.gerarRelatorio(pessoaSalarioConsolidadoDao.filterPaginadoProjetadoRelatorio(filtroPessoaSalarioConsolidadoDto), "/relatorios/relatorio_pessoas_salarios_consolidados.jasper", new HashMap<>());
		} catch (JRException e) {
			e.printStackTrace();
			throw new NegocioException("Erro ao gerar relatório", e);
		}
	}
	
	public List<PessoaSalarioConsolidado> findAllPaginadoProjetado(int first, int pageSize) {
		return pessoaSalarioConsolidadoDao.findAllPaginadoProjetado(first, pageSize);
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
	
	public List<PessoaSalarioConsolidado> filterPaginadoProjetado(FiltroPessoaSalarioConsolidadoDto filtroPessoaSalarioConsolidadoDto) {
		return pessoaSalarioConsolidadoDao.filterPaginadoProjetado(filtroPessoaSalarioConsolidadoDto);
	}
	
	public int countWithFilter(FiltroPessoaSalarioConsolidadoDto filtroPessoaSalarioConsolidadoDto) {
		return pessoaSalarioConsolidadoDao.countWithFilter(filtroPessoaSalarioConsolidadoDto);
	}
	
}
