package persistencia.repositorio.jpa;

import persistencia.entitad.LibroEntity;

public interface RepositorioLibroJPA {

	LibroEntity obtenerLibroEntityPorIsbn(String isbn);

}
