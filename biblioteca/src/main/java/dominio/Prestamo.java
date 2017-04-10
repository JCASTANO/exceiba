package dominio;

import java.util.Date;

public class Prestamo {

	private Date fecha;
	private Libro libro;
	
	public Prestamo(Date fecha, Libro libro) {
		this.fecha = fecha;
		this.libro = libro;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public Libro getLibro() {
		return libro;
	}
}
