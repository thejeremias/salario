package io.github.thejeremias.salario.dto;

public class FiltroUsuarioDto {
	
	private String login;
	
	private String nomePessoa;
	
	private int primeiroRegistro;
	
	private int quantidadeRegistros;
	
	public FiltroUsuarioDto() {}
	
	public FiltroUsuarioDto(String login, String nomePessoa, int primeiroRegistro, int quantidadeRegistros) {
		this.login = login;
		this.nomePessoa = nomePessoa;
		this.primeiroRegistro = primeiroRegistro;
		this.quantidadeRegistros = quantidadeRegistros;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
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
