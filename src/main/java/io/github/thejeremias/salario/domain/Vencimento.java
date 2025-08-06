package io.github.thejeremias.salario.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Vencimento implements PersistEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vencimento")
	private Long id;
	
	private String descricao;
	
	private Double valor;
	
	@Enumerated(value = EnumType.STRING)
	private TipoVencimento tipo;
	
	
	public Vencimento() {}
	
	public Vencimento(Long id, String descricao, Double valor, TipoVencimento tipo) {
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public TipoVencimento getTipo() {
		return tipo;
	}

	public void setTipo(TipoVencimento tipo) {
		this.tipo = tipo;
	}
	
}
