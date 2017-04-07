package dominio.repositorio;

import dominio.Libro;

public interface RepositorioLibro{

	Libro obtenerPrestadoPorIsbn(String isbn);

	Libro obtenerDisponiblePorIsbn(String isbn);

	void agregarPrestado(Libro libro);

	void agregarDisponible(Libro libro);

}