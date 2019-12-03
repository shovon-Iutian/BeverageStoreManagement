package de.uniba.dsg.dsam.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import de.uniba.dsg.dsam.service.CrudService;


/**
 * @author noman
 *
 * @param <ENTITY>
 */
public class CrudServiceImpl<ENTITY> implements CrudService<ENTITY> {

	@PersistenceContext(type=PersistenceContextType.TRANSACTION)
	private EntityManager em;
	
	@Override
	public ENTITY addOne(ENTITY entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public ENTITY updateOne(ENTITY entity) {
		return em.merge(entity);
	}

	@Override
	public Optional<ENTITY> getOne(Class<ENTITY> entityClass, int id) {
		return Optional.ofNullable(em.find(entityClass, id));
	}

	@Override
	public List<ENTITY> getByIds(Class<ENTITY> entityClass, List<Integer> ids) {
		return ids.stream().map(id -> em.find(entityClass, id)).collect(Collectors.toList());
	}

	@Override
	public List<ENTITY> getAll(Class<ENTITY> entityClass) {
		CriteriaQuery<ENTITY> criteriaQuery = em.getCriteriaBuilder().createQuery(entityClass);
		Root<ENTITY> root = criteriaQuery.from(entityClass);
		criteriaQuery.select(root);
		criteriaQuery.orderBy(em.getCriteriaBuilder().asc(root.get("timestamp")));
		return em.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public void deleteOne(ENTITY entity) {
		em.remove(entity);
	}

	@Override
	public void deleteAll() {
		em.clear();
	}
}
