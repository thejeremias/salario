package io.github.thejeremias.salario.exception;

public class NegocioException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public NegocioException(String mensagem) {
		super(mensagem);
	}
	
	public NegocioException(String mensagem, Throwable cause) {
		super(mensagem, cause);
	}
	
	public NegocioException(Throwable cause) {
		super(cause);
	}


}
