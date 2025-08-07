package io.github.thejeremias.salario.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import io.github.thejeremias.salario.domain.Pessoa;
import io.github.thejeremias.salario.service.PessoaService;

@ManagedBean
@RequestScoped
public class PessoaMbean extends AbstractController {

	private static final long serialVersionUID = 1L;

	private final PessoaService pessoaService;

	public PessoaMbean() {
		pessoaService = new PessoaService();
	}
	
	public List<SelectItem> getPessoasSemUsuarioCombo() {
		List<Pessoa> pessoasSemUsuario = pessoaService.findPessoasSemUsuario();
		List<SelectItem> pessoasSemUsuarioCombo = new ArrayList<>();
		for (Pessoa pessoa : pessoasSemUsuario) {
			pessoasSemUsuarioCombo.add(new SelectItem(pessoa.getId(), pessoa.getNome() + " - " + pessoa.getUsuario()));
		}
		return pessoasSemUsuarioCombo;
	}
	
}
