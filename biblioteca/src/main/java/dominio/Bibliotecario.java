package dominio;

import dominio.excepcion.PrestamoException;
import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;
import dominio.servicio.EmailService;

public class Bibliotecario {

	private static final String LIBRO_PRESTADO_EXITOSAMENTE = "Libro prestado exitosamente";
	public static final String EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE = "El libro no se encuentra disponible";

	private RepositorioLibro repositorioLibros;
	private EmailService emailSenderService;
	private RepositorioPrestamo repositorioPrestamos;

	public Bibliotecario(RepositorioLibro repositorioLibros, RepositorioPrestamo repositorioPrestamos,
			EmailService emailService) {
		this.repositorioLibros = repositorioLibros;
		this.emailSenderService = emailService;
		this.repositorioPrestamos = repositorioPrestamos;

	}

	public void prestar(String isbn) {
		if (!esPrestado(isbn)) {
			Libro libroAPrestar = repositorioLibros.obtenerDisponiblePorIsbn(isbn);
			repositorioPrestamos.agregar(libroAPrestar);
			emailSenderService.enviarEmail(LIBRO_PRESTADO_EXITOSAMENTE);
		} else {
			throw new PrestamoException(EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE);
		}
	}

	public boolean esPrestado(String isbn) {

		return repositorioPrestamos.obtenerLibroPrestadoPorIsbn(isbn) != null;

	}

}
