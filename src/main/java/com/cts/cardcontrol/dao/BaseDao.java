package com.cts.cardcontrol.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public  class BaseDao<T, K> implements IGenericDao<T, K> {
	
	private static Logger LOGGER = LoggerFactory.getLogger(BaseDao.class);

	protected Class<T> entityClass;
	protected String typeName ;

	@PersistenceContext
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public BaseDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) (getClass().getGenericSuperclass());
		//this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0].getClass();
		this.typeName = genericSuperclass.getActualTypeArguments()[0].getTypeName();
		String className = genericSuperclass.getActualTypeArguments()[0].toString().split(" ")[1];
		try {
			this.entityClass = (Class<T>) Class.forName(className);
		} catch (ClassNotFoundException e) {
			LOGGER.error("Unable to create the class of parameterixed type",e);
			throw new RuntimeException(e);
		}
				
	}

	public void persist(T entity) {
		entityManager.persist(entity);
	}

	public void remove(T entity) {
		entityManager.remove(entity);
	}

	public T findById(K id) {
		return (T) entityManager.find(entityClass, id);
	}
	public List<T> findAll() {
	      return entityManager.createQuery("Select t from " + typeName +" t" )
	                          .getResultList();
	  }

	public Class getEntityClass() {
		return entityClass;
	}

	@Override
	public void merge(T entity) {
		entityManager.merge(entity);
		
	}

	@Override
	public  TypedQuery<T> createQuery(String jpql) {
		return entityManager.createQuery(jpql, entityClass);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}




		

}
