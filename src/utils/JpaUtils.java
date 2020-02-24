package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

public class JpaUtils {
	
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	@PersistenceContext
	private static EntityManager em = null;
	
	public void init () {
		em = emf.createEntityManager();
	}
	
	public static  EntityManager getEm()
	{
		return em;
	}

}
