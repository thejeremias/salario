package io.github.thejeremias.salario.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.github.thejeremias.salario.exception.NegocioException;


@Entity
public class Usuario implements PersistEntity {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    private String senha;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    public Usuario() {
    	pessoa = new Pessoa(0L);
    }
     
    public Usuario(Long id, String nomePessoa, String usuario) {
    	this.id = id;
    	pessoa = new Pessoa(0L, nomePessoa, usuario);
    }
     
    public Usuario(Long id, String senha, Pessoa pessoa) {
		this.id = id;
		this.senha = senha;
		this.pessoa = pessoa;
	}

	@Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return pessoa.getUsuario();
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public void validar() throws NegocioException {
		if (senha == null || senha.trim().isEmpty()) {
			throw new NegocioException("Campo Senha é obrigatório.");
		}
		if (pessoa == null || pessoa.getId() == null || pessoa.getId() == 0 ) {
			throw new NegocioException("Pessoa é obrigatório.");
		}
	}
    
}
