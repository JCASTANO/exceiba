package persistencia.repositorio;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dominio.Libro;
import dominio.repositorio.RepositorioLibro;
import persistencia.builder.LibroBuilder;
import persistencia.entitad.LibroEntity;
import persistencia.entitad.PrestamoEntity;

public class RepositorioLibroPersistente implements RepositorioLibro {

	private EntityManager entityManeger;

	public RepositorioLibroPersistente(EntityManager entityManeger) {
		this.entityManeger = entityManeger;
	}

	@Override
	public Libro obtenerPrestadoPorIsbn(String isbn) {
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

	@Override
	public Libro obtenerDisponiblePorIsbn(String isbn) {
		LibroEntity libroEntity = obtenerLibroEntityPorIsbn(isbn);

		return LibroBuilder.convertirADominio(libroEntity);
	}

	@Override
	public void agregarPrestado(Libro libro) {
		LibroEntity libroEntity = obtenerLibroEntityPorIsbn(libro.getIsbn());

		PrestamoEntity prestamoEntity = buildPrestamoEntity(libroEntity);

		entityManeger.merge(prestamoEntity);
	}

	private PrestamoEntity buildPrestamoEntity(LibroEntity libroEntity) {
		PrestamoEntity prestamoEntity = new PrestamoEntity();
		prestamoEntity.setLibro(libroEntity);
		prestamoEntity.setFecha(new Date());
		return prestamoEntity;
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
