package repositorio;

import dominio.Libro;

public interface RepositorioLibros{

	Libro obtenerLibroPrestadoPorIsbn(String isbn);

	Libro obtenerLibroDisponiblePorIsbn(String isbn);

	void agregarLibroPrestados(Libro libro);

	void agregarLibroDisponibles(Libro libro);

}