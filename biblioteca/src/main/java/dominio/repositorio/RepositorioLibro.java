package dominio.repositorio;

import dominio.Libro;

public interface RepositorioLibro {

	Libro obtenerDisponiblePorIsbn(String isbn);

	void agregarDisponible(Libro libro);

}