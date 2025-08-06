package io.github.thejeremias.salario.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa_salario_consolidado")
public class PessoaSalarioConsolidado implements PersistEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pessoa_salario_consolidado")
	private Long id;
	
	@Column(name = "nome_pessoa")
	private String nomePessoa;
	
	@Column(name = "nome_cargo")
	private String nomeCargo;
	
	private Double salario;
	
	@OneToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;

	public PessoaSalarioConsolidado() {}
	
	public PessoaSalarioConsolidado(String nomePessoa, String nomeCargo, Double salario) {
		this.nomePessoa = nomePessoa;
		this.nomeCargo = nomeCargo;
		this.salario = salario;
	}
	
	public PessoaSalarioConsolidado(Long id, String nomePessoa, String nomeCargo, Double salario) {
		this.id = id;
		this.nomePessoa = nomePessoa;
		this.nomeCargo = nomeCargo;
		this.salario = salario;
	}
		
	public PessoaSalarioConsolidado(Long id, String nomePessoa, String nomeCargo, Double salario, Pessoa pessoa) {
		this.id = id;
		this.nomePessoa = nomePessoa;
		this.nomeCargo = nomeCargo;
		this.salario = salario;
		this.pessoa = pessoa;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getNomeCargo() {
		return nomeCargo;
	}

	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
