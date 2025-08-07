package io.github.thejeremias.salario.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import io.github.thejeremias.salario.dto.LoginDto;
import io.github.thejeremias.salario.exception.NegocioException;
import io.github.thejeremias.salario.service.UsuarioService;

@ManagedBean
@ViewScoped
public class LoginMbean extends AbstractController {

	private static final long serialVersionUID = 1L;
	
	private LoginDto loginDto;
	
	private final UsuarioService usuarioService; 
	
	public LoginMbean() {
		this.loginDto = new LoginDto();
		this.usuarioService = new UsuarioService();
	}
	
	public String entrar() {
	  try {
		loginDto.validar();
		usuarioService.autenticar(loginDto, (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false));
		return "/menu?faces-redirect?true";
	  } catch(IllegalArgumentException | NegocioException e) {
		  e.printStackTrace();
		  adicionarMensagemErro(e.getMessage());
		  return "";
	  }
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect?true";
	}

	public LoginDto getLoginDto() {
		return loginDto;
	}

	public void setLoginDto(LoginDto loginDto) {
		this.loginDto = loginDto;
	}

}
