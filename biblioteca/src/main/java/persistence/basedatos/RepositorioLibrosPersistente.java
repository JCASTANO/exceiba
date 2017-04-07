package persistence.basedatos;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dominio.Libro;
import persistence.builder.LibroBuilder;
import persistence.entity.LibroEntity;
import persistence.entity.PrestamoEntity;
import repositorio.RepositorioLibros;

public class RepositorioLibrosPersistente implements RepositorioLibros {

	private EntityManager entityManeger;

	public RepositorioLibrosPersistente(EntityManager entityManeger) {
		this.entityManeger = entityManeger;
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
		return !resultList.isEmpty() ? (PrestamoEntity)resultList.get(0) : null;
	}

	@Override
	public Libro obtenerLibroDisponiblePorIsbn(String isbn) {
		LibroEntity libroEntity = obtenerLibroEntityPorIsbn(isbn);
		
		return LibroBuilder.convertirADominio(libroEntity);
	}

	@Override
	public void agregarLibroPrestados(Libro libro) {
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
	public void agregarLibroDisponibles(Libro libro) {
		entityManeger.merge(LibroBuilder.convertirAEntity(libro));
	}

	private LibroEntity obtenerLibroEntityPorIsbn(String isbn) {
		Query query = entityManeger.createNamedQuery("Libro.findByIsbn");
		query.setParameter("isbn", isbn);
		
		return (LibroEntity) query.getSingleResult();
	}

	@Override
	public void iniciar() {
		entityManeger.getTransaction().begin();
	}

	@Override
	public void terminar() {
		entityManeger.getTransaction().commit();
	}
}
