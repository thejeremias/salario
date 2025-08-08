package io.github.thejeremias.salario.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.github.thejeremias.salario.domain.TipoVencimento;
import io.github.thejeremias.salario.domain.Vencimento;


/**
 *  Resolução com implementação mais em Java, se que menos otimizada. 
 */
public class CalculoSalarioTest {

	private List<Vencimento> vencimentosAnalista;
	
	@Before
	public void setUp() {
		vencimentosAnalista = new ArrayList<>();
		vencimentosAnalista.add(new Vencimento(3L, "Vencimento Basico Analista", 2500.0, TipoVencimento.CREDITO));
		vencimentosAnalista.add(new Vencimento(9L, "Previdencia", 11.0, TipoVencimento.DEBITO));
		vencimentosAnalista.add(new Vencimento(8L, "Plano de Saude", 350.0, TipoVencimento.DEBITO));
	}
	
	@Test
	public void givenVencimentosAnalista_WhenCalculadoSalarioAnalista_thenValorLiquidoAnalista() {
		Double salario = vencimentosAnalista
				.stream()
				.mapToDouble(v ->  v.getTipo().name() == "DEBITO" ? - v.getValor() : v.getValor()).sum();
		Assert.assertEquals(new Double(2139.0), salario);
	}
	
}
