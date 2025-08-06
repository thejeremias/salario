package io.github.thejeremias.salario.controller;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import io.github.thejeremias.salario.async.CalculoSalariosTarefaAsync;
import io.github.thejeremias.salario.async.ExecutorTarefaAsync;
import io.github.thejeremias.salario.domain.PessoaSalarioConsolidado;
import io.github.thejeremias.salario.dto.FiltroPessoaSalarioConsolidadoDto;
import io.github.thejeremias.salario.exception.NegocioException;
import io.github.thejeremias.salario.paginator.PessoaSalarioConsolidadoPaginator;
import io.github.thejeremias.salario.service.PessoaSalarioConsolidadoService;


@ManagedBean
@ViewScoped
public class PessoaSalarioConsolidadoMbean extends AbstractController {
	
	private static final long serialVersionUID = 1L;

	private PessoaSalarioConsolidadoService pessoaSalarioConsolidadoService;
		
	private LazyDataModel<PessoaSalarioConsolidado> lazyModel;
	
	private Boolean assincrono;
	
	private FiltroPessoaSalarioConsolidadoDto filtroPessoaSalarioConsolidadoDto;
	
	public PessoaSalarioConsolidadoMbean() {
		pessoaSalarioConsolidadoService = new PessoaSalarioConsolidadoService();
		filtroPessoaSalarioConsolidadoDto = new FiltroPessoaSalarioConsolidadoDto();
		lazyModel = new PessoaSalarioConsolidadoPaginator(filtroPessoaSalarioConsolidadoDto);
	}

	public String calcularSalarios() {
		if (assincrono) {
			return calcularSalariosAssincrono();
		}
		return calcularSalariosSincrono();
	}

	private String calcularSalariosSincrono() {
		try {
			pessoaSalarioConsolidadoService.calcularSalariosTodos();
			adicionarMensagemInfo("Cálculo realizado com sucesso!");
		} catch (NegocioException e) {
			adicionarMensagemErro(e.getMessage());
		}
		return "";
	}
	
	public String calcularSalariosAssincrono() {
		ExecutorTarefaAsync.executar(new CalculoSalariosTarefaAsync());
		adicionarMensagemInfo("Cálculo foi iniciado. Assim que ficar pronto, será disponibilizado.");
		assincrono = false;
		return "";
	}
		
	public LazyDataModel<PessoaSalarioConsolidado> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<PessoaSalarioConsolidado> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public Boolean getAssincrono() {
		return assincrono;
	}

	public void setAssincrono(Boolean assincrono) {
		this.assincrono = assincrono;
	}

	public FiltroPessoaSalarioConsolidadoDto getFiltroPessoaSalarioConsolidadoDto() {
		return filtroPessoaSalarioConsolidadoDto;
	}

	public void setFiltroPessoaSalarioConsolidadoDto(FiltroPessoaSalarioConsolidadoDto filtroPessoaSalarioConsolidadoDto) {
		this.filtroPessoaSalarioConsolidadoDto = filtroPessoaSalarioConsolidadoDto;
	}
	
}
