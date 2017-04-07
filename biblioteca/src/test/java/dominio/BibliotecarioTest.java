package dominio;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import builder.LibroTestDataBuilder;
import builder.RepositorioLibrosPersistenteTestDataBuilder;
import exepcion.PrestamoException;
import persistence.basedatos.RepositorioLibrosPersistente;
import persistence.configuracion.ConexionJPA;
import repositorio.RepositorioLibros;
import service.EmailService;

public class BibliotecarioTest {

	RepositorioLibros repositorioLibros;

	@Before
	public void setUp() {
		repositorioLibros = new RepositorioLibrosPersistente(new ConexionJPA().createEntityManager());
		repositorioLibros.iniciar();
	}

	@Test
	public void prestarLibroTest() {

		// arrange
		Libro libro = new LibroTestDataBuilder().conTitutlo("Cronica de una muerta anunciada").build();
		new RepositorioLibrosPersistenteTestDataBuilder(repositorioLibros).conLibroDisponible(libro).build();
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
		new RepositorioLibrosPersistenteTestDataBuilder(repositorioLibros).conLibroDisponible(libro).build();

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
		repositorioLibros.terminar();
	}

}
