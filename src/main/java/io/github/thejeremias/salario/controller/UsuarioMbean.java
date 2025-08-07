package io.github.thejeremias.salario.controller;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;

import io.github.thejeremias.salario.domain.Usuario;
import io.github.thejeremias.salario.dto.FiltroUsuarioDto;
import io.github.thejeremias.salario.exception.NegocioException;
import io.github.thejeremias.salario.paginator.UsuarioPaginator;
import io.github.thejeremias.salario.service.UsuarioService;


@ManagedBean
@ViewScoped
public class UsuarioMbean extends AbstractController {

	private static final long serialVersionUID = 1L;
	
	private final transient UsuarioService usuarioService;
	
	private Usuario usuario;
	
	private FiltroUsuarioDto filtroUsuarioDto;
	
	private LazyDataModel<Usuario> lazyModel;
	
	public UsuarioMbean() {
		usuarioService = new UsuarioService();
		usuario = new Usuario();
		filtroUsuarioDto = new FiltroUsuarioDto();
		lazyModel = new UsuarioPaginator(filtroUsuarioDto);
		Object id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if (id != null) {
			usuario = usuarioService.findById(Long.valueOf((String) id));
		}
	}
	
	public String salvar() {
		try {
		  usuarioService.salvar(usuario);
		  adicionarMensagemInfo("Usuário salvo!");
		  return "/menu?redirect-faces=true";	
		} catch(NegocioException e) {
			e.printStackTrace();
			adicionarMensagemErro(e.getMessage());
			return "";
		}
	}
	
	public String remover() {
		try {
			usuarioService.deleteById(usuario.getId());
			adicionarMensagemInfo("Usuário removido!");
		} catch(NegocioException e) {
			 e.printStackTrace();
			 adicionarMensagemErro(e.getMessage());
		 }
		 return "";
	}
	
	public String irParaAlterar() {
	    return "/usuario/form?faces-redirect=true&id=" + usuario.getId();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public FiltroUsuarioDto getFiltroUsuarioDto() {
		return filtroUsuarioDto;
	}

	public void setFiltroUsuarioDto(FiltroUsuarioDto filtroUsuarioDto) {
		this.filtroUsuarioDto = filtroUsuarioDto;
	}

	public LazyDataModel<Usuario> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Usuario> lazyModel) {
		this.lazyModel = lazyModel;
	}
	
	public boolean isCadastrando() {
		return usuario.getId() == null || usuario.getId() == 0L;
	}
	
}
