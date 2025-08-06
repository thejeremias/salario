package io.github.thejeremias.salario.paginator;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import io.github.thejeremias.salario.domain.PessoaSalarioConsolidado;
import io.github.thejeremias.salario.service.PessoaSalarioConsolidadoService;

public class PessoaSalarioConsolidadoPaginator extends LazyDataModel<PessoaSalarioConsolidado> {

	private static final long serialVersionUID = 1L;
	
	private PessoaSalarioConsolidadoService pessoaSalarioConsolidadoService;
	
	public PessoaSalarioConsolidadoPaginator() {
		super();
		this.pessoaSalarioConsolidadoService = new PessoaSalarioConsolidadoService();
	}
		
	public List<PessoaSalarioConsolidado> load(int first, int pageSize) {
		  List<PessoaSalarioConsolidado> pessoas = pessoaSalarioConsolidadoService.findAllPaginado(first, pageSize);
          setRowCount(pessoaSalarioConsolidadoService.countAll());
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