package dominio.excepcion;

public class EnvioEmailException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EnvioEmailException(String message) {
		super(message);
	}
}
