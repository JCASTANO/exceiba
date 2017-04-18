package persistencia.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dominio.Libro;
import dominio.repositorio.RepositorioLibro;
import persistencia.builder.LibroBuilder;
import persistencia.entitad.LibroEntity;
import persistencia.repositorio.jpa.RepositorioLibroJPA;

public class RepositorioLibroPersistente implements RepositorioLibro, RepositorioLibroJPA {

	private EntityManager entityManeger;

	public RepositorioLibroPersistente(EntityManager entityManeger) {
		this.entityManeger = entityManeger;
	}	

	@Override
	public Libro obtenerPorIsbn(String isbn) {
		LibroEntity libroEntity = obtenerLibroEntityPorIsbn(isbn);

		return LibroBuilder.convertirADominio(libroEntity);
	}

	@Override
	public void agregar(Libro libro) {
		entityManeger.persist(LibroBuilder.convertirAEntity(libro));
	}

	@Override
	public LibroEntity obtenerLibroEntityPorIsbn(String isbn) {
		Query query = entityManeger.createNamedQuery("Libro.findByIsbn");
		query.setParameter("isbn", isbn);

		return (LibroEntity) query.getSingleResult();
	}

}
