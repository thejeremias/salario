package io.github.thejeremias.salario.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Query;

import io.github.thejeremias.salario.domain.PessoaSalarioConsolidado;
import io.github.thejeremias.salario.dto.FiltroPessoaSalarioConsolidadoDto;
import io.github.thejeremias.salario.dto.LinhaRelatorio;
import io.github.thejeremias.salario.exception.DaoException;

public class PessoaSalarioConsolidadoDao extends GenericDaoImpl<PessoaSalarioConsolidado>{

	public PessoaSalarioConsolidadoDao() {
		super(PessoaSalarioConsolidado.class);
	}
	
	private static final String CALCULAR_SALARIOS_SQL = 
		    "INSERT INTO pessoa_salario_consolidado (id_pessoa, nome_pessoa, nome_cargo, salario) " +
		    "SELECT p.id_pessoa, p.nome as nome_pessoa, c.nome as nome_cargo, " +
		    "       SUM(CASE WHEN v.tipo = 'DEBITO' THEN -v.valor ELSE v.valor END) AS salario " +
		    "FROM pessoa p " +
		    "JOIN cargo c ON c.id_cargo = p.id_cargo " +
		    "JOIN cargo_vencimento cv ON cv.id_cargo = p.id_cargo " +
		    "JOIN vencimento v ON v.id_vencimento = cv.id_vencimento " +
		    "GROUP BY p.id_pessoa, p.nome, c.nome";
	
	private static final String LIMPAR_TABELA_SQL = "DELETE FROM pessoa_salario_consolidado WHERE id_pessoa_salario_consolidado > 0";
		
	public void calcularSalariosTodos() throws DaoException {
		try {
			entityManager.getTransaction().begin();
			entityManager.createNativeQuery(LIMPAR_TABELA_SQL).executeUpdate();
			entityManager.createNativeQuery(CALCULAR_SALARIOS_SQL).executeUpdate();
		    entityManager.getTransaction().commit();
		} catch(Exception e) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			e.printStackTrace();
			throw new DaoException("DAO: erro com dados ao calcular salário.", e);
		}    
	}
	
	public List<PessoaSalarioConsolidado> findAllPaginadoProjetado(int first, int pageSize) {
		String jpql = "SELECT new PessoaSalarioConsolidado(psc.id, psc.nomePessoa, psc.nomeCargo, psc.salario) FROM PessoaSalarioConsolidado psc"
				+ " order by psc.nomePessoa";
		Query query = entityManager.createQuery(jpql);
		query.setFirstResult(first);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
	
	
	public List<PessoaSalarioConsolidado> filterPaginadoProjetado(FiltroPessoaSalarioConsolidadoDto filtroPessoaSalarioConsolidadoDto) {
		StringBuilder jpql = new StringBuilder("SELECT new PessoaSalarioConsolidado(psc.id, psc.nomePessoa, psc.nomeCargo, psc.salario) FROM PessoaSalarioConsolidado psc WHERE 1=1");
		Map<String, Object> filtros = new HashMap<>();
		if (filtroPessoaSalarioConsolidadoDto.getNome() != null && !filtroPessoaSalarioConsolidadoDto.getNome().trim().isEmpty()) {
			jpql.append(" AND lower(psc.nomePessoa) like :nome ");
			filtros.put("nome", "%" + filtroPessoaSalarioConsolidadoDto.getNome().toLowerCase() + "%");
		}
		jpql.append(" ORDER BY psc.nomePessoa ");
		Query query = entityManager.createQuery(jpql.toString());
		query.setFirstResult(filtroPessoaSalarioConsolidadoDto.getPrimeiroRegistro());
		query.setMaxResults(filtroPessoaSalarioConsolidadoDto.getQuantidadeRegistros());
		for (Entry<String, Object> filtro : filtros.entrySet()) {
			query.setParameter(filtro.getKey(), filtro.getValue());
		}
		return query.getResultList();
	}
	
	public List<LinhaRelatorio> filterPaginadoProjetadoRelatorio(FiltroPessoaSalarioConsolidadoDto filtroPessoaSalarioConsolidadoDto) {
		StringBuilder jpql = new StringBuilder("SELECT new io.github.thejeremias.salario.dto.LinhaRelatorio(psc.nomePessoa, psc.nomeCargo, psc.salario) FROM PessoaSalarioConsolidado psc WHERE 1=1");
		Map<String, Object> filtros = new HashMap<>();
		if (filtroPessoaSalarioConsolidadoDto.getNome() != null && !filtroPessoaSalarioConsolidadoDto.getNome().trim().isEmpty()) {
			jpql.append(" AND lower(psc.nomePessoa) like :nome ");
			filtros.put("nome", "%" + filtroPessoaSalarioConsolidadoDto.getNome().toLowerCase() + "%");
		}
		jpql.append(" ORDER BY psc.nomePessoa ");
		Query query = entityManager.createQuery(jpql.toString());
		for (Entry<String, Object> filtro : filtros.entrySet()) {
			query.setParameter(filtro.getKey(), filtro.getValue());
		}
		return query.getResultList();
	}
	
	public int countWithFilter(FiltroPessoaSalarioConsolidadoDto filtroPessoaSalarioConsolidadoDto) {
	    StringBuilder jpql = new StringBuilder("SELECT COUNT(psc) FROM PessoaSalarioConsolidado psc WHERE 1=1");
	    Map<String, Object> parametros = new HashMap<>();
	    if (filtroPessoaSalarioConsolidadoDto.getNome() != null && !filtroPessoaSalarioConsolidadoDto.getNome().trim().isEmpty()) {
	        jpql.append(" AND LOWER(psc.nomePessoa) LIKE :nome");
	        parametros.put("nome", "%" + filtroPessoaSalarioConsolidadoDto.getNome().toLowerCase() + "%");
	    }
	    Query query = entityManager.createQuery(jpql.toString());
	    for (Map.Entry<String, Object> entry : parametros.entrySet()) {
	        query.setParameter(entry.getKey(), entry.getValue());
	    }
	    return ((Long) query.getSingleResult()).intValue();
	}
	
	
}
