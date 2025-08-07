package io.github.thejeremias.salario.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import io.github.thejeremias.salario.domain.Usuario;
import io.github.thejeremias.salario.dto.FiltroUsuarioDto;
import io.github.thejeremias.salario.exception.DaoException;

public class UsuarioDao extends GenericDaoImpl<Usuario> {

	public UsuarioDao() {
		super(Usuario.class);
	}
	
	public Usuario findByNome(String nome) throws DaoException {
		String jpql = "SELECT new Usuario(u.id, u.senha, p.nome, p.usuario) FROM Usuario u join u.pessoa p WHERE p.usuario = :nome";
		try {
			return entityManager.createQuery(jpql, Usuario.class).setParameter("nome", nome).getSingleResult();
		} catch(NoResultException e) {
			throw new DaoException(DaoException.REGISTRO_NAO_ENCONTRADO, e);
		}
	}


	@SuppressWarnings("unchecked")
	public List<Usuario> filterPaginadoProjetado(FiltroUsuarioDto filtroUsuarioDto) {
		StringBuilder jpql = new StringBuilder("SELECT new Usuario(u.id, p.nome, p.usuario) FROM Usuario u JOIN u.pessoa p WHERE 1=1");
		Map<String, Object> filtros = new HashMap<>();
		if (filtroUsuarioDto.getNomePessoa() != null && !filtroUsuarioDto.getNomePessoa().trim().isEmpty()) {
			jpql.append(" AND lower(p.nome) like :nome ");
			filtros.put("nome", "%" + filtroUsuarioDto.getNomePessoa().toLowerCase() + "%");
		}
		if (filtroUsuarioDto.getLogin() != null && !filtroUsuarioDto.getLogin().trim().isEmpty()) {
			jpql.append(" AND lower(p.usuario) like :login ");
			filtros.put("login", "%" + filtroUsuarioDto.getLogin().toLowerCase() + "%");
		}
		jpql.append(" ORDER BY p.nome ");
		Query query = entityManager.createQuery(jpql.toString());
		query.setFirstResult(filtroUsuarioDto.getPrimeiroRegistro());
		query.setMaxResults(filtroUsuarioDto.getQuantidadeRegistros());
		for (Entry<String, Object> filtro : filtros.entrySet()) {
			query.setParameter(filtro.getKey(), filtro.getValue());
		}
		return query.getResultList();
	}
	
	public int countWithFilter(FiltroUsuarioDto filtroUsuarioDto) {
		StringBuilder jpql = new StringBuilder("SELECT count(u) FROM Usuario u JOIN u.pessoa p WHERE 1=1");
		Map<String, Object> filtros = new HashMap<>();
		if (filtroUsuarioDto.getNomePessoa() != null && !filtroUsuarioDto.getNomePessoa().trim().isEmpty()) {
			jpql.append(" AND lower(p.nome) like :nome ");
			filtros.put("nome", "%" + filtroUsuarioDto.getNomePessoa().toLowerCase() + "%");
		}
		if (filtroUsuarioDto.getLogin() != null && !filtroUsuarioDto.getLogin().trim().isEmpty()) {
			jpql.append(" AND lower(p.usuario) like :login ");
			filtros.put("login", "%" + filtroUsuarioDto.getLogin().toLowerCase() + "%");
		}
		Query query = entityManager.createQuery(jpql.toString());
		for (Entry<String, Object> filtro : filtros.entrySet()) {
			query.setParameter(filtro.getKey(), filtro.getValue());
		}
		return ((Long) query.getSingleResult()).intValue();
	}
	
	public Usuario findByIdProjetado(Long id) throws DaoException {
		String jpql = "SELECT new Usuario(u.id, p.nome, p.id, p.usuario) FROM Usuario u join u.pessoa p WHERE u.id = :idUsuario";
		try {
			return entityManager.createQuery(jpql, Usuario.class).setParameter("idUsuario", id).getSingleResult();
		} catch(NoResultException e) {
			throw new DaoException(DaoException.REGISTRO_NAO_ENCONTRADO, e);
		}
	}
	
}
