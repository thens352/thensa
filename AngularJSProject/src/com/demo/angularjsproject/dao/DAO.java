package com.demo.angularjsproject.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DAO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3676277784582605325L;

	@PersistenceContext
	protected EntityManager entityManager;

	private Class<T> entityClass;

	public DAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public T persist(T entity) {
		entityManager.persist(entity);
		return entity;
	}

	public T merge(T entity) {
		entityManager.merge(entity);
		return entity;
	}

	public T remove(T entity) {
		if (entityManager.contains(entity)) {
			entityManager.remove(entity);
			return entity;
		} else {
			T newT = entityManager.merge(entity);
			entityManager.remove(newT);
			return newT;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {

		return entityManager.createQuery(
				"from " + entityClass.getName() + "  i").getResultList();
	}

	public T find(int id) {
		T e = entityManager.find(entityClass, id);
		return e;
	}

}
