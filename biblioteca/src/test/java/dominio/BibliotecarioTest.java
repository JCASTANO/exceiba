package dominio;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import builder.LibroTestDataBuilder;
import exepcion.PrestamoException;
import persistence.sistema.SistemaDePersistencia;
import repositorio.RepositorioLibros;
import service.EmailService;

public class BibliotecarioTest {

	SistemaDePersistencia sistemaPersistencia;
	RepositorioLibros repositorioLibros;

	@Before
	public void setUp() {
		sistemaPersistencia = new SistemaDePersistencia();
		repositorioLibros = sistemaPersistencia.obtenerRepositorioLibros();
		sistemaPersistencia.iniciar();
		
	}

	@Test
	public void prestarLibroTest() {

		
		// arrange
		Libro libro = new LibroTestDataBuilder().conTitutlo("Cronica de una muerta anunciada").build();
		repositorioLibros.agregarLibroDisponibles(libro);
		Bibliotecario blibliotecario = new Bibliotecario(repositorioLibros, createEmailService());

		// act
		blibliotecario.prestar(libro.getIsbn());

		// assert
		Assert.assertTrue(blibliotecario.esPrestado(libro.getIsbn()));
		Assert.assertNotNull(repositorioLibros.obtenerLibroPrestadoPorIsbn(libro.getIsbn()));

	}

	@Test
	public void prestarLibroNoDisponibleTest() {

		// arrange
		Libro libro = new LibroTestDataBuilder().conTitutlo("Cronica de una muerta anunciada").build();
		repositorioLibros.agregarLibroDisponibles(libro);
		Bibliotecario blibliotecario = new Bibliotecario(repositorioLibros, createEmailService());

		// act
		blibliotecario.prestar(libro.getIsbn());
		try {
			blibliotecario.prestar(libro.getIsbn());
			fail();
		} catch (PrestamoException e) {
			// assert
			Assert.assertEquals(Bibliotecario.EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE, e.getMessage());
		}
	}

	private EmailService createEmailService() {
		return Mockito.mock(EmailService.class);
	}

	@After
	public void tearDown() {
		sistemaPersistencia.terminar();
	}

}
