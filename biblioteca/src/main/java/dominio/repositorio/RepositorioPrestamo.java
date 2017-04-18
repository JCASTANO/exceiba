package dominio.repositorio;

import dominio.Libro;
import dominio.Prestamo;

public interface RepositorioPrestamo {

	/**
	 * Permite obtener un libro prestado dado un isbn
	 * @param isbn
	 * @return
	 */
	Libro obtenerLibroPrestadoPorIsbn(String isbn);
	
	/**
	 * Permite agregar un prestamo al repositorio de prestamos
	 * @param prestamo
	 */
	void agregar(Prestamo prestamo);

}
