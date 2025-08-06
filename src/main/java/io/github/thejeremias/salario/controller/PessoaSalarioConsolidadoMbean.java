package io.github.thejeremias.salario.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import io.github.thejeremias.salario.domain.PessoaSalarioConsolidado;
import io.github.thejeremias.salario.exception.NegocioException;
import io.github.thejeremias.salario.paginator.PessoaSalarioConsolidadoPaginator;
import io.github.thejeremias.salario.service.PessoaSalarioConsolidadoService;

@ManagedBean
@ViewScoped
public class PessoaSalarioConsolidadoMbean extends AbstractController {
	
	private static final long serialVersionUID = 1L;

	private List<PessoaSalarioConsolidado> pessoasSalariosConsolidados;
	
	private PessoaSalarioConsolidadoService pessoaSalarioConsolidadoService;
	
	private LazyDataModel<PessoaSalarioConsolidado> lazyModel;
	
	public PessoaSalarioConsolidadoMbean() {
		pessoaSalarioConsolidadoService = new PessoaSalarioConsolidadoService();
		pessoasSalariosConsolidados = pessoaSalarioConsolidadoService.findAll();
		lazyModel = new PessoaSalarioConsolidadoPaginator();
	}

	public String calcularSalarios() {
		try {
			pessoaSalarioConsolidadoService.calcularSalariosTodos();
			pessoasSalariosConsolidados = pessoaSalarioConsolidadoService.findAll();
			adicionarMensagemInfo("Cálculo realizado com sucesso!");
		} catch (NegocioException e) {
			adicionarMensagemErro(e.getMessage());
		}
		return "";
	}
	
	public List<PessoaSalarioConsolidado> getPessoasSalariosConsolidados(){
		return pessoasSalariosConsolidados;
	}

	public void setPessoasSalariosConsolidados(List<PessoaSalarioConsolidado> pessoasSalariosConsolidados) {
		this.pessoasSalariosConsolidados = pessoasSalariosConsolidados;
	}

	public LazyDataModel<PessoaSalarioConsolidado> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<PessoaSalarioConsolidado> lazyModel) {
		this.lazyModel = lazyModel;
	}
	
}
