package persistencia.repositorio;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dominio.Libro;
import dominio.repositorio.RepositorioPrestamo;
import persistencia.builder.LibroBuilder;
import persistencia.entitad.LibroEntity;
import persistencia.entitad.PrestamoEntity;

public class RepositorioPrestamoPersistente implements RepositorioPrestamo {

	private EntityManager entityManeger;

	public RepositorioPrestamoPersistente(EntityManager entityManeger) {
		this.entityManeger = entityManeger;
	}

	@Override
	public void agregar(Libro libro) {
		LibroEntity libroEntity = obtenerLibroEntityPorIsbn(libro.getIsbn());

		PrestamoEntity prestamoEntity = buildPrestamoEntity(libroEntity);

		entityManeger.merge(prestamoEntity);
	}
	
	@Override
	public Libro obtenerLibroPrestadoPorIsbn(String isbn) {
		PrestamoEntity prestamoEntity = obtenerPrestamoEntityPorIsbn(isbn);

		return LibroBuilder.convertirADominio(prestamoEntity != null ? prestamoEntity.getLibro() : null);
	}

	@SuppressWarnings("rawtypes")
	private PrestamoEntity obtenerPrestamoEntityPorIsbn(String isbn) {
		Query query = entityManeger.createNamedQuery("Prestamo.findByIsbn");
		query.setParameter("isbn", isbn);
		List resultList = query.getResultList();
		return !resultList.isEmpty() ? (PrestamoEntity) resultList.get(0) : null;
	}

	private PrestamoEntity buildPrestamoEntity(LibroEntity libroEntity) {
		PrestamoEntity prestamoEntity = new PrestamoEntity();
		prestamoEntity.setLibro(libroEntity);
		prestamoEntity.setFecha(new Date());
		return prestamoEntity;
	}

	private LibroEntity obtenerLibroEntityPorIsbn(String isbn) {
		Query query = entityManeger.createNamedQuery("Libro.findByIsbn");
		query.setParameter("isbn", isbn);

		return (LibroEntity) query.getSingleResult();
	}

}
