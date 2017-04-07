package repositorio;

import java.util.List;

import dominio.Libro;

public class RepositorioLibrosEnMemoria implements RepositorioLibros {

	private List<Libro> librosPrestados;

	private List<Libro> librosDisponibles;

	public RepositorioLibrosEnMemoria(List<Libro> librosPrestados, List<Libro> librosDisponibles) {
		this.librosDisponibles = librosDisponibles;
		this.librosPrestados = librosPrestados;
	}

	/* (non-Javadoc)
	 * @see repositorio.RepositorioLibros#obtenerLibroPrestadoPorIsbn(java.lang.String)
	 */
	@Override
	public Libro obtenerLibroPrestadoPorIsbn(String isbn) {

		return librosPrestados.stream().filter(libro -> libro.getIsbn().equals(isbn)).findFirst().orElse(null);
	}

	/* (non-Javadoc)
	 * @see repositorio.RepositorioLibros#obtenerLibroDisponiblePorIsbn(java.lang.String)
	 */
	@Override
	public Libro obtenerLibroDisponiblePorIsbn(String isbn) {
		return librosDisponibles.stream().filter(libro -> libro.getIsbn().equals(isbn)).findFirst().orElse(null);
	}

	/* (non-Javadoc)
	 * @see repositorio.RepositorioLibros#agregarLibroPrestados(dominio.Libro)
	 */
	@Override
	public void agregarLibroPrestados(Libro libro) {
		librosPrestados.add(libro);
	}

	/* (non-Javadoc)
	 * @see repositorio.RepositorioLibros#agregarLibroDisponibles(dominio.Libro)
	 */
	@Override
	public void agregarLibroDisponibles(Libro libro) {
		librosDisponibles.add(libro);

	}

	/* (non-Javadoc)
	 * @see repositorio.RepositorioLibros#removerLibroDisponibles(dominio.Libro)
	 */
	@Override
	public void removerLibroDisponibles(Libro libro) {
		librosDisponibles.remove(libro);
	}

}
