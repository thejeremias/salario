package io.github.thejeremias.salario.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class MenuMbean extends AbstractController {

	private static final long serialVersionUID = 1L;
	
	public String irParaPessoaSalarioConsolidado() {
		return "pessoas_salarios_consolidados/lista?faces-redirect=true";
	}
	
	public String irParaCadastroUsuario() {
		return "usuario/form?faces-redirect=true";
	}
	
	public String irParaListarUsuario() {
		return "usuario/lista?faces-redirect=true";
	}
	
	
	public String voltar() {
		return "/menu?faces-redirect=true";
	}
	
}
