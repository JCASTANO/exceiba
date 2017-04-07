package dominio;

import dominio.excepcion.PrestamoException;
import dominio.repositorio.RepositorioLibro;
import dominio.servicio.EmailService;

public class Bibliotecario {

	private static final String LIBRO_PRESTADO_EXITOSAMENTE = "Libro prestado exitosamente";
	public static final String EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE = "El libro no se encuentra disponible";
	
	private RepositorioLibro repositorioLibros;
	private EmailService emailSenderService;

	public Bibliotecario(RepositorioLibro repositorioLibros, EmailService emailService) {
		this.repositorioLibros = repositorioLibros;
		this.emailSenderService = emailService;

	}

	public void prestar(String isbn) {
		if(!esPrestado(isbn)) {
			Libro libroAPrestar = repositorioLibros.obtenerDisponiblePorIsbn(isbn);
			repositorioLibros.agregarPrestado(libroAPrestar);
			emailSenderService.enviarEmail(LIBRO_PRESTADO_EXITOSAMENTE);
		}
		else {
			throw new PrestamoException(EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE);
		}
	}

	public boolean esPrestado(String isbn) {

		return repositorioLibros.obtenerPrestadoPorIsbn(isbn) != null;

	}

}
