package io.github.thejeremias.salario.dao;


import io.github.thejeremias.salario.domain.PessoaSalarioConsolidado;
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
	
}
