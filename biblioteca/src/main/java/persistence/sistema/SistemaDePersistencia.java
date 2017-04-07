package persistence.sistema;

import javax.persistence.EntityManager;

import persistence.basedatos.RepositorioLibrosPersistente;
import persistence.configuracion.ConexionJPA;
import persistence.configuracion.Transaccion;
import repositorio.RepositorioLibros;

public class SistemaDePersistencia implements Transaccion {

	private EntityManager entityManager;

	public SistemaDePersistencia() {
		this.entityManager = new ConexionJPA().createEntityManager();
	}

	public RepositorioLibros obtenerRepositorioLibros() {
		return new RepositorioLibrosPersistente(entityManager);
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
