package io.github.thejeremias.salario.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import io.github.thejeremias.salario.domain.Pessoa;

public class PessoaDao extends GenericDaoImpl<Pessoa> {

	public PessoaDao() {
		super(Pessoa.class);
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> findPessoasSemUsuario() {
		String sql = "SELECT p.id_pessoa, p.nome, p.usuario FROM pessoa p left join usuario u on u.id_pessoa = p.id_pessoa where u.id_usuario is null"
				+ " order by p.nome";
		List<Object[]> resultSet = entityManager.createNativeQuery(sql).getResultList();
		List<Pessoa> pessoasSemUsuario = new ArrayList<>();
		for (Object[] linha : resultSet) {
			int i = 0;
			pessoasSemUsuario.add(new Pessoa(((BigInteger) linha[i++]).longValue(), (String) linha[i++], (String) linha[i]));
		}
		return pessoasSemUsuario;
	}

}
