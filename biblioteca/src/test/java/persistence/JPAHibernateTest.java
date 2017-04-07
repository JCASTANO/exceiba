package persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class JPAHibernateTest {

	private static final String BIBLIOTECA_PU_TEST = "biblioteca-pu-test";
	protected static EntityManagerFactory entityManagerFactory;
	protected static EntityManager entityManager;

	@BeforeClass
	public static void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory(BIBLIOTECA_PU_TEST);
	}

	@Before
	public void beforeTest() {
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	@After
	public void afterTest() {
		entityManager.clear();
		entityManager.close();
	}

	@AfterClass
	public static void tearDown() {
		entityManagerFactory.close();
	}
}
