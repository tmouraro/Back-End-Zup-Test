package br.com.zup.util;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Classe Producer para EntityManager 
 */
public class PersistenceProduces {
	
	@Produces  
    @Dependent  
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
		
	@Produces  
    @Dependent  
    public EntityManager createEntityManager(EntityManagerFactory emf) {  
        return emf.createEntityManager();  
    }

	public void close(@Disposes EntityManager entityManager) {  
        entityManager.close();  
    } 
}
