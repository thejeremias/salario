package io.github.thejeremias.salario.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/*
 * Esta serve como modelo para os controllers da aplicação, contendo reutilizáveis. 
 */
public abstract class AbstractController implements Serializable {

	private static final long serialVersionUID = 1L;

	public void adicionarMensagemErro(String mensagem) {
		 adicionarMensagem(FacesMessage.SEVERITY_ERROR,  mensagem, "erro");
	}
	
	public void adicionarMensagemInfo(String mensagem) {
		 adicionarMensagem(FacesMessage.SEVERITY_INFO, mensagem, "aviso");
		 
	}
	
	public void adicionarMensagem(Severity severity, String resumo,  String mensagem) {
		 FacesContext.getCurrentInstance().addMessage(null,
	             new FacesMessage(severity, resumo, mensagem));
		 FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}
	
	/**
	 * forward simplificado para fazer redirects.
	 */
	public String forward(String view) {
		return view + "?faces-redirect=true";
	}
		
}
