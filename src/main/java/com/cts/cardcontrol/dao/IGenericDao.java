package com.cts.cardcontrol.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public interface IGenericDao<T, K> {
	
	void persist(T entity);
    void remove(T entity);
    void merge(T entity);
    T findById(K id);
    List<T> findAll();
    TypedQuery<T> createQuery(String jpql);
    EntityManager getEntityManager();
 
}
