package io.github.thejeremias.salario.paginator;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import io.github.thejeremias.salario.domain.Usuario;
import io.github.thejeremias.salario.dto.FiltroUsuarioDto;
import io.github.thejeremias.salario.service.UsuarioService;

public class UsuarioPaginator extends LazyDataModel<Usuario> {

	private static final long serialVersionUID = 1L;
	
	private transient UsuarioService usuarioService;
	
	private FiltroUsuarioDto filtroUsuarioDto;
	
	public UsuarioPaginator(FiltroUsuarioDto filtroUsuarioDto) {
		this.usuarioService = new UsuarioService();
		this.filtroUsuarioDto = filtroUsuarioDto;
	}
		
	public List<Usuario> load(int first, int pageSize) {
		  filtroUsuarioDto.setPrimeiroRegistro(first);
		  filtroUsuarioDto.setQuantidadeRegistros(pageSize);
		  List<Usuario> usuarios = usuarioService.filterPaginadoProjetado(filtroUsuarioDto);
          setRowCount(usuarioService.countWithFilter(filtroUsuarioDto));
          return usuarios;
	}
	
	@Override
	public List<Usuario> load(int first, int pageSize, List<SortMeta> multiSortMeta,
			Map<String, Object> filters) {
		return this.load(first, pageSize);
	}
	
	
	@Override
	public List<Usuario> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		return this.load(first, pageSize);
	}

}