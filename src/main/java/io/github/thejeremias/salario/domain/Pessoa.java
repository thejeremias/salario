package io.github.thejeremias.salario.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Pessoa implements PersistEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pessoa")
	private Long id;
	
	private String nome;
	
	private String cidade;
	
	private String email;
	
	private String cep;
	
	private String endereco;
	
	private String pais;
	
	private String usuario;
	
	private String telefone;
	
	@Column(name = "data_nascimento")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@ManyToOne
	@JoinColumn(name = "id_cargo")
	private Cargo cargo;

	private String senha;
	
	public Pessoa() {}
	
	public Pessoa(Long id) {
		this.id = id;
	}
	
	public Pessoa(Long id, String nome, String usuario) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
	}
	
	public Pessoa(Long id, String nome, String cidade, String email, String cep, String endereco, String pais,
			String usuario, String telefone, Date dataNascimento, Cargo cargo, String senha) {
		this.id = id;
		this.nome = nome;
		this.cidade = cidade;
		this.email = email;
		this.cep = cep;
		this.endereco = endereco;
		this.pais = pais;
		this.usuario = usuario;
		this.dataNascimento = dataNascimento;
		this.cargo = cargo;
		this.senha = senha;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
