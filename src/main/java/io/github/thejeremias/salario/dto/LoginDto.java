package io.github.thejeremias.salario.dto;

public class LoginDto {
	
	private String usuario;
	
	private String senha;
	
	public LoginDto() {}

	public LoginDto(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void validar() {
		if (usuario == null || usuario.trim().isEmpty()) {
			throw new IllegalArgumentException("Usuário é obrigatório.");
		}
		
		if (senha == null || senha.trim().isEmpty()) {
			throw new IllegalArgumentException("Senha é obrigatória.");
		}
	}

}
