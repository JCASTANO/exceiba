package persistence.builder;

import static org.junit.Assert.*;

import org.junit.Test;

import persistence.JPAHibernateTest;
import persistence.entitad.LibroEntity;

public class LibroTest extends JPAHibernateTest {

	@Test
	public void crearLibro() {

		LibroEntity libro = new LibroEntity();
		libro.setTitulo("Cien años de soledad");
		libro.setAnio(2010);
		libro.setIsbn("541321");

		LibroEntity libroPersistido = entityManager.merge(libro);

		assertNotNull(entityManager.find(LibroEntity.class, libroPersistido.getId()));
	}

}
