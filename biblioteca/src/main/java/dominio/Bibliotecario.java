package dominio;

import exepcion.PrestamoException;
import repositorio.RepositorioLibros;
import service.EmailService;

public class Bibliotecario {

	private static final String LIBRO_PRESTADO_EXITOSAMENTE = "Libro prestado exitosamente";
	public static final String EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE = "El libro no se encuentra disponible";
	
	private RepositorioLibros repositorioLibros;
	private EmailService emailSenderService;

	public Bibliotecario(RepositorioLibros repositorioLibros, EmailService emailService) {
		this.repositorioLibros = repositorioLibros;
		this.emailSenderService = emailService;

	}

	public void prestar(String isbn) {
		if(!esPrestado(isbn)) {
			Libro libroAPrestar = repositorioLibros.obtenerLibroDisponiblePorIsbn(isbn);
			repositorioLibros.agregarLibroPrestados(libroAPrestar);
			repositorioLibros.removerLibroDisponibles(libroAPrestar);
			emailSenderService.sendEmail(LIBRO_PRESTADO_EXITOSAMENTE);
		}
		else {
			throw new PrestamoException(EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE);
		}
	}

	public boolean esPrestado(String isbn) {

		return repositorioLibros.obtenerLibroPrestadoPorIsbn(isbn) != null;

	}

}
