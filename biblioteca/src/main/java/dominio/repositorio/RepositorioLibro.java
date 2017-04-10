package dominio.repositorio;

import dominio.Libro;

public interface RepositorioLibro {

	Libro obtenerPorIsbn(String isbn);

	void agregar(Libro libro);

}