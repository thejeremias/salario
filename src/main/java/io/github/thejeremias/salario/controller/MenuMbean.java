package io.github.thejeremias.salario.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class MenuMbean extends AbstractController {

	private static final long serialVersionUID = 1L;
	
	public static final String MENU_VIEW = "/menu";
	
	public String irParaPessoaSalarioConsolidado() {
		return forward(PessoaSalarioConsolidadoMbean.LISTA_VIEW);
	}
	
	public String irParaCadastroUsuario() {
		return forward(UsuarioMbean.FORM_VIEW);
	}
	
	public String irParaListarUsuario() {
		return forward(UsuarioMbean.LISTA_VIEW);
	}
	
	public String voltar() {
		return forward(MENU_VIEW);
	}
	
}
