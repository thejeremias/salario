package io.github.thejeremias.salario.dto;

public class LinhaRelatorioDto {
	
	private String nomePessoa;
	
	private String nomeCargo;
	
	private String salario;

	public LinhaRelatorioDto() {}
	
	public LinhaRelatorioDto(String nomePessoa, String nomeCargo, Double salario) {
		this.nomePessoa = nomePessoa;
		this.nomeCargo = nomeCargo;
		this.salario = "R$ " + salario;
	}
	
	public LinhaRelatorioDto(String nomePessoa, String nomeCargo, String salario) {
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
