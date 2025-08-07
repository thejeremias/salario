package io.github.thejeremias.salario.dto;

public class LinhaRelatorio {
	
	private String nomePessoa;
	
	private String nomeCargo;
	
	private String salario;

	public LinhaRelatorio() {}
	
	public LinhaRelatorio(String nomePessoa, String nomeCargo, Double salario) {
		this.nomePessoa = nomePessoa;
		this.nomeCargo = nomeCargo;
		this.salario = "R$ " + salario;
	}
	
	public LinhaRelatorio(String nomePessoa, String nomeCargo, String salario) {
		this.nomePessoa = nomePessoa;
		this.nomeCargo = nomeCargo;
		this.salario = salario;
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

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}
	
	
}
