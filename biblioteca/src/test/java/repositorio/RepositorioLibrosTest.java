package repositorio;

import org.junit.Assert;
import org.junit.Test;

import builder.LibroTestDataBuilder;
import builder.RepositorioLibrosTestDataBuilder;
import dominio.Libro;

public class RepositorioLibrosTest {

	@Test
	public void obtenerLibroPrestadoTest() {
		Libro libro = new LibroTestDataBuilder().conTitutlo("Cronica de una muerta anunciada").conIsbn("1234").build();
		RepositorioLibros repositorioLibros = new RepositorioLibrosTestDataBuilder().conLibroPrestado(libro).build();

		Libro libroPrestado = repositorioLibros.obtenerLibroPrestadoPorIsbn("1234");

		Assert.assertEquals(libro, libroPrestado);

	}

	@Test
	public void libroPrestadoNoEncontradoTest() {

		RepositorioLibros repositorioLibros = new RepositorioLibrosTestDataBuilder().build();

		Libro libro = repositorioLibros.obtenerLibroPrestadoPorIsbn("1234");

		Assert.assertNull(libro);

	}

	@Test
	public void obtenerLibroDisponibleTest() {
		Libro libro = new LibroTestDataBuilder().conTitutlo("Cronica de una muerta anunciada").conIsbn("1234").build();
		RepositorioLibros repositorioLibros = new RepositorioLibrosTestDataBuilder().conLibroDisponible(libro).build();

		Libro libroPrestado = repositorioLibros.obtenerLibroDisponiblePorIsbn("1234");

		Assert.assertEquals(libro, libroPrestado);

	}

	@Test
	public void libroDisponibleNoEncontradoTest() {

		RepositorioLibros repositorioLibros = new RepositorioLibrosTestDataBuilder().build();

		Libro libro = repositorioLibros.obtenerLibroDisponiblePorIsbn("1234");

		Assert.assertNull(libro);

	}

	@Test
	public void agregarLibroPrestadosTest() {

		RepositorioLibros repositorioLibros = new RepositorioLibrosTestDataBuilder().build();
		Libro libro = new LibroTestDataBuilder().build();

		repositorioLibros.agregarLibroPrestados(libro);

		Libro libroPrestado = repositorioLibros.obtenerLibroPrestadoPorIsbn(libro.getIsbn());
		Assert.assertEquals(libro, libroPrestado);

	}

	@Test
	public void agregarLibroDisponibleTest() {

		RepositorioLibros repositorioLibros = new RepositorioLibrosTestDataBuilder().build();
		Libro libro = new LibroTestDataBuilder().build();

		repositorioLibros.agregarLibroDisponibles(libro);

		Libro libroPrestado = repositorioLibros.obtenerLibroDisponiblePorIsbn(libro.getIsbn());
		Assert.assertEquals(libro, libroPrestado);

	}

	@Test
	public void removerLibroDisponibleTest() {

		RepositorioLibros repositorioLibros = new RepositorioLibrosTestDataBuilder().build();
		Libro libro = new LibroTestDataBuilder().build();
		repositorioLibros.agregarLibroDisponibles(libro);

		repositorioLibros.removerLibroDisponibles(libro);

		
		Assert.assertNull(repositorioLibros.obtenerLibroDisponiblePorIsbn(libro.getIsbn()));
	}

}
