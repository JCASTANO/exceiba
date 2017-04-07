package builder;

import dominio.Libro;
import repositorio.RepositorioLibros;

public class RepositorioLibrosPersistenteTestDataBuilder {

	private RepositorioLibros repositorioLibros;

	public RepositorioLibrosPersistenteTestDataBuilder(RepositorioLibros repositorioLibros) {
		this.repositorioLibros = repositorioLibros;

	}

	public RepositorioLibrosPersistenteTestDataBuilder conLibroDisponible(Libro libroDisponible) {
		this.repositorioLibros.agregarLibroDisponibles(libroDisponible);
		return this;
	}
	
	public RepositorioLibrosPersistenteTestDataBuilder conLibroPrestado(Libro libroPrestado) {
		this.repositorioLibros.agregarLibroPrestados(libroPrestado);
		return this;
	}

	public RepositorioLibros build() {
		return this.repositorioLibros;
	}

}
