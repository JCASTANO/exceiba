package builder;

import dominio.Libro;

public class LibroTestDataBuilder {

	private String isbn;
	private String titulo;
	private int anio;

	public LibroTestDataBuilder() {
		this.isbn = "1234";
		this.titulo = "Cien años de soledad";
		this.anio = 2010;
	}

	public LibroTestDataBuilder conTitutlo(String titulo) {
		this.titulo = titulo;
		return this;
	}

	public LibroTestDataBuilder conIsbn(String isbn) {
		this.isbn = isbn;
		return this;
	}

	public LibroTestDataBuilder conAnio(int anio) {
		this.anio = anio;
		return this;
	}

	public Libro build() {
		return new Libro(isbn, titulo, anio);
	}

}
