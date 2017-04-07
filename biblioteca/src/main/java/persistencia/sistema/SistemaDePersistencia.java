package persistencia.sistema;

import javax.persistence.EntityManager;

import dominio.repositorio.RepositorioLibro;
import persistencia.conexion.ConexionJPA;
import persistencia.repositorio.RepositorioLibroPersistente;

public class SistemaDePersistencia implements Transaccion {

	private EntityManager entityManager;

	public SistemaDePersistencia() {
		this.entityManager = new ConexionJPA().createEntityManager();
	}

	public RepositorioLibro obtenerRepositorioLibros() {
		return new RepositorioLibroPersistente(entityManager);
	}

	@Override
	public void iniciar() {
		entityManager.getTransaction().begin();
	}

	@Override
	public void terminar() {
		entityManager.getTransaction().commit();
	}

}
