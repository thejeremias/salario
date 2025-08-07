package io.github.thejeremias.salario.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTarefaAsync {
	
	 private ExecutorTarefaAsync() {}

	 private static final ExecutorService executor = Executors.newFixedThreadPool(5);

	 public static void executar(Runnable tarefa) {
	      executor.submit(tarefa);
	 }
	
}
