package persistencia.repositorio;

import persistencia.entitad.LibroEntity;

public interface RepositorioLibroJPA {

	LibroEntity obtenerLibroEntityPorIsbn(String isbn);

}
