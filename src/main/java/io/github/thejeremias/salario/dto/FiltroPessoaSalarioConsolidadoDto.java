package io.github.thejeremias.salario.dto;
import java.io.Serializable;

public class FiltroPessoaSalarioConsolidadoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private int primeiroRegistro;
	
	private int quantidadeRegistros;
	
	public FiltroPessoaSalarioConsolidadoDto() {}
	
	public FiltroPessoaSalarioConsolidadoDto(String nome, int primeiroRegistro, int quantidadeRegistros) {
		this.nome = nome;
		this.primeiroRegistro = primeiroRegistro;
		this.quantidadeRegistros = quantidadeRegistros;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPrimeiroRegistro() {
		return primeiroRegistro;
	}

	public void setPrimeiroRegistro(int primeiroRegistro) {
		this.primeiroRegistro = primeiroRegistro;
	}

	public int getQuantidadeRegistros() {
		return quantidadeRegistros;
	}

	public void setQuantidadeRegistros(int quantidadeRegistros) {
		this.quantidadeRegistros = quantidadeRegistros;
	}
	
}
