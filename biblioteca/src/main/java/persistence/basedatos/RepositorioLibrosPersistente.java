package persistence.basedatos;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dominio.Libro;
import persistence.entity.LibroEntity;
import repositorio.RepositorioLibros;

public class RepositorioLibrosPersistente implements RepositorioLibros {
	
	private EntityManager entityManeger;
	
	public RepositorioLibrosPersistente(EntityManager entityManeger) {
		this.entityManeger = entityManeger;
	}

	@Override
	public Libro obtenerLibroPrestadoPorIsbn(String isbn) {
		Query query = entityManeger.createNamedQuery("Libro.findByIsbn");
		query.setParameter("isbn", isbn);
		LibroEntity libroEntity = (LibroEntity) query.getSingleResult();
		
		
		
		return null;
	}

	@Override
	public Libro obtenerLibroDisponiblePorIsbn(String isbn) {
		return null;
	}

	@Override
	public void agregarLibroPrestados(Libro libro) {
		
	}

	@Override
	public void agregarLibroDisponibles(Libro libro) {
		
	}

	@Override
	public void removerLibroDisponibles(Libro libro) {
		
	}
	
	

}
