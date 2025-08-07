package io.github.thejeremias.salario.controller;


import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.LazyDataModel;

import io.github.thejeremias.salario.async.CalculoSalariosTarefaAsync;
import io.github.thejeremias.salario.async.ExecutorTarefaAsync;
import io.github.thejeremias.salario.domain.PessoaSalarioConsolidado;
import io.github.thejeremias.salario.dto.FiltroPessoaSalarioConsolidadoDto;
import io.github.thejeremias.salario.exception.NegocioException;
import io.github.thejeremias.salario.paginator.PessoaSalarioConsolidadoPaginator;
import io.github.thejeremias.salario.service.PessoaSalarioConsolidadoService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;


@ManagedBean
@ViewScoped
public class PessoaSalarioConsolidadoMbean extends AbstractController {
	
	public static final String LISTA_VIEW = "pessoas_salarios_consolidados/lista";
	
	private static final long serialVersionUID = 1L;

	private transient PessoaSalarioConsolidadoService pessoaSalarioConsolidadoService;
		
	private LazyDataModel<PessoaSalarioConsolidado> lazyModel;
	
	private boolean assincrono;
	
	private FiltroPessoaSalarioConsolidadoDto filtroPessoaSalarioConsolidadoDto;
	
	public PessoaSalarioConsolidadoMbean() {
		pessoaSalarioConsolidadoService = new PessoaSalarioConsolidadoService();
		filtroPessoaSalarioConsolidadoDto = new FiltroPessoaSalarioConsolidadoDto();
		lazyModel = new PessoaSalarioConsolidadoPaginator(filtroPessoaSalarioConsolidadoDto);
		assincrono = false;
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
		adicionarMensagemInfo("Cálculo será iniciado. Assim que ficar pronto, será disponibilizado.");
		assincrono = false;
		return "";
	}
	
	public String gerarRelatorio() {
		try {		
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\"relatorio_pessoas_salarios_consolidados.pdf\"");
			JasperPrint jasperPrint = pessoaSalarioConsolidadoService.gerarRelatorioPessoasSalariosConsolidados(filtroPessoaSalarioConsolidadoDto);
			try (ServletOutputStream out = response.getOutputStream()) {
				JasperExportManager.exportReportToPdfStream(jasperPrint, out);
			}
			FacesContext.getCurrentInstance().responseComplete();
		} catch (NegocioException | IOException | JRException e) {
			e.printStackTrace();
			adicionarMensagemErro(e.getMessage());
		}
		return "";
	}
		
	public LazyDataModel<PessoaSalarioConsolidado> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<PessoaSalarioConsolidado> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public boolean getAssincrono() {
		return assincrono;
	}

	public void setAssincrono(boolean assincrono) {
		this.assincrono = assincrono;
	}

	public FiltroPessoaSalarioConsolidadoDto getFiltroPessoaSalarioConsolidadoDto() {
		return filtroPessoaSalarioConsolidadoDto;
	}

	public void setFiltroPessoaSalarioConsolidadoDto(FiltroPessoaSalarioConsolidadoDto filtroPessoaSalarioConsolidadoDto) {
		this.filtroPessoaSalarioConsolidadoDto = filtroPessoaSalarioConsolidadoDto;
	}
	
}
