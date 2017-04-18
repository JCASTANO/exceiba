package dominio;

import java.util.Date;

public class Prestamo {

	private Date fechaSolicitud;
	private Libro libro;
	
	public Prestamo(Libro libro) {
		this.fechaSolicitud = new Date();
		this.libro = libro;
	}
	
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}
	
	public Libro getLibro() {
		return libro;
	}
}
