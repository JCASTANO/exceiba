package dominio.repositorio;

import dominio.Libro;

public interface RepositorioPrestamo {

	void agregar(Libro libro);

	Libro obtenerLibroPrestadoPorIsbn(String isbn);

}
