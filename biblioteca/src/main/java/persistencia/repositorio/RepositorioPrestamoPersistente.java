package persistencia.repositorio;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dominio.Libro;
import dominio.Prestamo;
import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;
import persistencia.builder.LibroBuilder;
import persistencia.entitad.LibroEntity;
import persistencia.entitad.PrestamoEntity;
import persistencia.repositorio.jpa.RepositorioLibroJPA;

public class RepositorioPrestamoPersistente implements RepositorioPrestamo {

	private EntityManager entityManager;

	private RepositorioLibroJPA repositorioLibroJPA;
	
	public RepositorioPrestamoPersistente(EntityManager entityManager, RepositorioLibro repositorioLibro) {
		this.entityManager = entityManager;
		this.repositorioLibroJPA = (RepositorioLibroJPA) repositorioLibro;
	}

	@Override
	public void agregar(Prestamo prestamo) {
		LibroEntity libroEntity = repositorioLibroJPA.obtenerLibroEntityPorIsbn(prestamo.getLibro().getIsbn());

		PrestamoEntity prestamoEntity = buildPrestamoEntity(libroEntity,prestamo.getFecha());

		entityManager.persist(prestamoEntity);
	}
	
	@Override
	public Libro obtenerLibroPrestadoPorIsbn(String isbn) {
		PrestamoEntity prestamoEntity = obtenerPrestamoEntityPorIsbn(isbn);

		return LibroBuilder.convertirADominio(prestamoEntity != null ? prestamoEntity.getLibro() : null);
	}

	@SuppressWarnings("rawtypes")
	private PrestamoEntity obtenerPrestamoEntityPorIsbn(String isbn) {
		Query query = entityManager.createNamedQuery("Prestamo.findByIsbn");
		query.setParameter("isbn", isbn);
		List resultList = query.getResultList();
		return !resultList.isEmpty() ? (PrestamoEntity) resultList.get(0) : null;
	}

	private PrestamoEntity buildPrestamoEntity(LibroEntity libroEntity,Date fecha) {
		PrestamoEntity prestamoEntity = new PrestamoEntity();
		prestamoEntity.setLibro(libroEntity);
		prestamoEntity.setFecha(fecha);
		return prestamoEntity;
	}
}
