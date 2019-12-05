package de.uniba.dsg.dsam.backend.service;

//import de.uniba.dsg.dsam.service.CrudService;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * @author noman
 *
 * @param <ENTITY>
 */

@Stateless
@Remote(CrudService.class)
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
	public Optional<ENTITY> getOne(Class<?> entityClass, int id) {
		return (Optional<ENTITY>) Optional.ofNullable(em.find(entityClass, id));
	}

	@Override
	public List<ENTITY> getByIds(Class<ENTITY> entityClass, List<Integer> ids) {
		return ids.stream().map(id -> em.find(entityClass, id)).collect(Collectors.toList());
	}

	@Override
	public List<ENTITY> getAll(Class<?> entityClass) {
		CriteriaQuery<ENTITY> criteriaQuery = (CriteriaQuery<ENTITY>) em.getCriteriaBuilder().createQuery(entityClass);
		Root<ENTITY> root = (Root<ENTITY>) criteriaQuery.from(entityClass);
		criteriaQuery.select(root);
		criteriaQuery.orderBy(em.getCriteriaBuilder().asc(root.get("id")));
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
