package io.github.thejeremias.salario.paginator;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import io.github.thejeremias.salario.domain.PessoaSalarioConsolidado;
import io.github.thejeremias.salario.dto.FiltroPessoaSalarioConsolidadoDto;
import io.github.thejeremias.salario.service.PessoaSalarioConsolidadoService;

public class PessoaSalarioConsolidadoPaginator extends LazyDataModel<PessoaSalarioConsolidado> {

	private static final long serialVersionUID = 1L;
	
	private PessoaSalarioConsolidadoService pessoaSalarioConsolidadoService;
	
	private FiltroPessoaSalarioConsolidadoDto filtroPessoaSalarioConsolidadoDto;
	
	public PessoaSalarioConsolidadoPaginator(FiltroPessoaSalarioConsolidadoDto filtroPessoaSalarioConsolidadoDto) {
		this.pessoaSalarioConsolidadoService = new PessoaSalarioConsolidadoService();
		this.filtroPessoaSalarioConsolidadoDto = filtroPessoaSalarioConsolidadoDto;
	}
		
	public List<PessoaSalarioConsolidado> load(int first, int pageSize) {
		  filtroPessoaSalarioConsolidadoDto.setPrimeiroRegistro(first);
		  filtroPessoaSalarioConsolidadoDto.setQuantidadeRegistros(pageSize);
		  List<PessoaSalarioConsolidado> pessoas = pessoaSalarioConsolidadoService.filterPaginadoProjetado(filtroPessoaSalarioConsolidadoDto);
          setRowCount(pessoaSalarioConsolidadoService.countWithFilter(filtroPessoaSalarioConsolidadoDto));
          return pessoas;
	}
	
	@Override
	public List<PessoaSalarioConsolidado> load(int first, int pageSize, List<SortMeta> multiSortMeta,
			Map<String, Object> filters) {
		return this.load(first, pageSize);
	}
	
	
	@Override
	public List<PessoaSalarioConsolidado> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		return this.load(first, pageSize);
	}

}