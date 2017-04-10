package dominio.repositorio;

import dominio.Libro;
import dominio.Prestamo;

public interface RepositorioPrestamo {

	Libro obtenerLibroPrestadoPorIsbn(String isbn);
	
	void agregar(Prestamo prestamo);

}
