package io.github.thejeremias.salario.exception;

public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DaoException(String mensagem) {
		super(mensagem);
	}
	
	public DaoException(String mensagem, Throwable cause) {
		super(mensagem, cause);
	}
	
}
