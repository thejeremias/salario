package io.github.thejeremias.salario.exception;

public class DaoException extends Exception {
	
	public static final String REGISTRO_NAO_ENCONTRADO = "REGISTRO_NAO_ENCONTRADO";

	private static final long serialVersionUID = 1L;
	
	public DaoException(String mensagem) {
		super(mensagem);
	}
	
	public DaoException(String mensagem, Throwable cause) {
		super(mensagem, cause);
	}
	
	public boolean isRegistroNaoEncontrado() {
		return REGISTRO_NAO_ENCONTRADO.equals(getMessage());
	}
	
}
