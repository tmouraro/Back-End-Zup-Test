package br.com.zup.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.model.BaseEntity;




@Repository
public class GenericRepository<T extends BaseEntity> implements Serializable {

	/** Serial Version ID */
	private static final long serialVersionUID = 1L;

	@Inject
	protected EntityManager entityManager;
    
	@Transactional( readOnly=false, propagation = Propagation.REQUIRED)
	public T persist(T entity) {

		//entity.setDataCriacao(new Date());
		entityManager.persist(entity);
		entityManager.flush();

		return entity;
	}

	@Transactional(readOnly=false)
	public void merge(T entity) {
		
		//entity.setDataAlteracao(new Date());
		entityManager.merge(entity);
		entityManager.flush();
	}

	@Transactional(readOnly=false)
	public void remove(T entity) {
		T entityDelete = entityManager.merge(entity);
		entityManager.remove(entityDelete);
		entityManager.flush();
	}

	@Transactional(readOnly=false)
	public void removeById(Integer id) {
		T entity = entityManager.getReference(getTypeClass(), id);
		entityManager.remove(entity);
		entityManager.flush();;
	}

	public T findById(Integer id) {
		TypedQuery<T> query = entityManager.createNamedQuery(getTypeClass().getSimpleName() + ".findById", getTypeClass());
		query.setParameter("id", id);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
    
	public T findById(Integer id, Class<T> klass) {
		return entityManager.find(klass, id.intValue());
	}

	public List<T> findAll() {	
		return entityManager.createNamedQuery(getTypeClass().getSimpleName() + ".findAll", getTypeClass()).getResultList();
		
	}

	public Query createQuery(String query, Object... parameters) {
		Query q = entityManager.createQuery(query);

		for (int i = 1; i <= parameters.length; i++) {
			q.setParameter(i, parameters[i]);
		}

		return q;
	}

	@SuppressWarnings("unchecked")
	private Class<T> getTypeClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	private void beginTransaction() {
		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
	}

	private void commit() {
		if (entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().commit();
		}
	}

}