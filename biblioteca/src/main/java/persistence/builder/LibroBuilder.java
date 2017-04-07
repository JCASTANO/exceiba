package persistence.builder;

import dominio.Libro;
import persistence.entity.LibroEntity;

public class LibroBuilder {

	public Libro convertADominio(LibroEntity libroEntity) {
		return new Libro(libroEntity.getIsbn(), libroEntity.getTitulo(), libroEntity.getAnio());
	}

}
