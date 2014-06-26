package org.idiginfo.docsvc.jpa.citagora;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@ContextConfiguration
public class EntityManagerContainer {
	
	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager){
	    this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Transactional
	public void save(Object obj){
		entityManager.persist(obj);
		entityManager.flush();
		entityManager.clear();
	}
}
