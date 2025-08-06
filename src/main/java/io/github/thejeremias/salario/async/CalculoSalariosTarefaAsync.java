package io.github.thejeremias.salario.async;

import io.github.thejeremias.salario.exception.NegocioException;
import io.github.thejeremias.salario.service.PessoaSalarioConsolidadoService;

public class CalculoSalariosTarefaAsync implements Runnable {
	
	@Override
	public void run() {
		PessoaSalarioConsolidadoService pessoaSalarioConsolidadoService = new PessoaSalarioConsolidadoService();
		try {
			pessoaSalarioConsolidadoService.calcularSalariosTodos();
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	
}
