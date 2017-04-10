package dominio;

import java.util.Date;

import dominio.excepcion.PrestamoException;
import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;
import dominio.servicio.EmailService;

public class Bibliotecario {

	private static final String LIBRO_PRESTADO_EXITOSAMENTE = "Libro prestado exitosamente";
	public static final String EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE = "El libro no se encuentra disponible";

	private RepositorioLibro repositorioLibro;
	private EmailService emailSenderService;
	private RepositorioPrestamo repositorioPrestamo;

	public Bibliotecario(RepositorioLibro repositorioLibro, RepositorioPrestamo repositorioPrestamo,
			EmailService emailService) {
		this.repositorioLibro = repositorioLibro;
		this.emailSenderService = emailService;
		this.repositorioPrestamo = repositorioPrestamo;

	}

	public void prestar(String isbn) {
		if (!esPrestado(isbn)) {
			Libro libroAPrestar = repositorioLibro.obtenerPorIsbn(isbn);
			repositorioPrestamo.agregar(crearPrestamo(new Date(), libroAPrestar));
			emailSenderService.enviarEmail(LIBRO_PRESTADO_EXITOSAMENTE);
		} else {
			throw new PrestamoException(EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE);
		}
	}

	public boolean esPrestado(String isbn) {
		return repositorioPrestamo.obtenerLibroPrestadoPorIsbn(isbn) != null;
	}
	
	private Prestamo crearPrestamo(Date fecha, Libro libro) {
		return new Prestamo(fecha, libro);
	}
}
