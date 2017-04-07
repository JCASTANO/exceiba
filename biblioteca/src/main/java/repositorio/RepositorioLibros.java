package repositorio;

import dominio.Libro;
import persistence.configuracion.Transaccion;

public interface RepositorioLibros extends Transaccion {

	Libro obtenerLibroPrestadoPorIsbn(String isbn);

	Libro obtenerLibroDisponiblePorIsbn(String isbn);

	void agregarLibroPrestados(Libro libro);

	void agregarLibroDisponibles(Libro libro);

}