package persistencia.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dominio.Libro;
import dominio.repositorio.RepositorioLibro;
import persistencia.builder.LibroBuilder;
import persistencia.entitad.LibroEntity;

public class RepositorioLibroPersistente implements RepositorioLibro {

	private EntityManager entityManeger;

	public RepositorioLibroPersistente(EntityManager entityManeger) {
		this.entityManeger = entityManeger;
	}	

	@Override
	public Libro obtenerDisponiblePorIsbn(String isbn) {
		LibroEntity libroEntity = obtenerLibroEntityPorIsbn(isbn);

		return LibroBuilder.convertirADominio(libroEntity);
	}

	@Override
	public void agregarDisponible(Libro libro) {
		entityManeger.merge(LibroBuilder.convertirAEntity(libro));
	}

	private LibroEntity obtenerLibroEntityPorIsbn(String isbn) {
		Query query = entityManeger.createNamedQuery("Libro.findByIsbn");
		query.setParameter("isbn", isbn);

		return (LibroEntity) query.getSingleResult();
	}

}
