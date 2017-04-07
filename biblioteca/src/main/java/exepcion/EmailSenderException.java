package exepcion;

public class EmailSenderException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmailSenderException(String message) {
		super(message);
	}
}
