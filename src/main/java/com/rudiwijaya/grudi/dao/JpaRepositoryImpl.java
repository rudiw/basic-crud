package com.rudiwijaya.grudi.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.rudiwijaya.grudi.data.Page;
import com.rudiwijaya.grudi.data.PageImpl;
import com.rudiwijaya.grudi.data.Pageable;
import com.rudiwijaya.grudi.data.PagingAndSortingRepositoryImpl;
import com.rudiwijaya.grudi.data.Sort;
import com.rudiwijaya.grudi.data.Sort.Direction;
import com.rudiwijaya.grudi.data.Sort.Order;

/**
 * @author clutax
 *
 */
public abstract class JpaRepositoryImpl<T, ID extends Serializable> extends PagingAndSortingRepositoryImpl<T, ID>
	implements JpaRepository<T, ID> {

	protected final Logger log;
	private final Class<T> entityClass;
	protected final EntityManager em;

	public JpaRepositoryImpl(Class<T> entityClass, EntityManager em) {
		this.entityClass = entityClass;
		this.em = Preconditions.checkNotNull(em, "EntityManager must be provided");
		
		
		this.log = LoggerFactory.getLogger(JpaRepositoryImpl.class.getName() + "/" + entityClass.getSimpleName());
	}
	
	@PreDestroy
	public void destroy() {
		log.info("Shutting down {} JPA repository", entityClass.getSimpleName());
	}
	
	@Override @Transactional(readOnly=true)
	public Page<T> findAll(Pageable pageable) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		
		// SELECT COUNT(*) FROM entityClass WHERE ...
		final CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
		final Root<T> countRoot = countCq.distinct(true).from(entityClass);
		countCq.select(cb.countDistinct(countRoot));
		final long totalRowCount = em.createQuery(countCq).getSingleResult();
		
		
		// SELECT * FROM entityClass WHERE ... ORDER BY ... LIMIT x, y
		// FROM
		final CriteriaQuery<T> cq = cb.createQuery(entityClass);
		final Root<T> root = cq.distinct(true).from(entityClass);
		cq.select(root);
		// WHERE
		// ORDER BY
		final List<javax.persistence.criteria.Order> jpaOrders = FluentIterable
				.from(Optional.<Iterable<Sort.Order>>fromNullable(pageable.getSort()).or(ImmutableList.<Sort.Order>of()))
				.transform(new Function<Order, javax.persistence.criteria.Order>() {
			@Override
			@Nullable
			public javax.persistence.criteria.Order apply(@Nullable Order input) {
				return input.getDirection() == Direction.ASC ? cb.asc(root.get(input.getProperty())) : cb.desc(root.get(input.getProperty()));
			}
		}).toList();
		cq.orderBy(jpaOrders);
		
		final TypedQuery<T> typedQuery = em.createQuery(cq)
			.setFirstResult((int) pageable.getOffset()).setMaxResults((int) pageable.getPageSize());
		final List<T> pageContent = typedQuery.getResultList();
		
		log.debug("findAll {} returned {} of {} rows (paged by {})",
				entityClass.getSimpleName(), pageContent.size(), totalRowCount, pageable);
		return new PageImpl<>(pageContent, pageable, totalRowCount);
	}
	
	@Override @Transactional(readOnly=true)
	public List<T> findAll(Sort sort) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		
		// SELECT * FROM entityClass WHERE ... ORDER BY ... LIMIT x, y
		// FROM
		final CriteriaQuery<T> cq = cb.createQuery(entityClass);
		final Root<T> root = cq.distinct(true).from(entityClass);
		cq.select(root);
		// WHERE
		// ORDER BY
		final List<javax.persistence.criteria.Order> jpaOrders = FluentIterable
				.from(Optional.<Iterable<Sort.Order>>fromNullable(sort).or(ImmutableList.<Sort.Order>of()))
				.transform(new Function<Order, javax.persistence.criteria.Order>() {
			@Override
			@Nullable
			public javax.persistence.criteria.Order apply(@Nullable Order input) {
				return input.getDirection() == Direction.ASC ? cb.asc(root.get(input.getProperty())) : cb.desc(root.get(input.getProperty()));
			}
		}).toList();
		cq.orderBy(jpaOrders);
		
		final TypedQuery<T> typedQuery = em.createQuery(cq);
		final List<T> pageContent = typedQuery.getResultList();
		
		log.debug("findAll {} returned {} (sort by {})",
				entityClass.getSimpleName(), pageContent.size(), sort);
		return pageContent;
	}
	
	@Override @Transactional
	public <S extends T> Collection<S> add(Collection<S> entities) {
		log.debug("Adding {} {} entities", entities.size(), entityClass.getSimpleName());
		final List<S> addeds = FluentIterable.from(entities).transform(new Function<S, S>() {
			@Override @Nullable
			public S apply(@Nullable S input) {
				em.persist(input);
				return input;
			}
		}).toList();
		log.debug("Added {} {} entities", entities.size(), entityClass.getSimpleName());
		return addeds;
	}
	
	@Override @Transactional
	public <S extends T> Collection<S> modify(Map<ID, S> entities) {
		log.debug("Modifying {} {} entities", entities.size(), entityClass.getSimpleName());
		final List<S> mergedEntities = FluentIterable.from(entities.entrySet()).transform(new Function<Entry<ID, S>, S>() {
			@Override
			public S apply(Entry<ID, S> input) {
				final S mergedEntity = em.merge(input.getValue());
				return mergedEntity;
			};
		}).toList();
		log.debug("{} {} entities have been modified", mergedEntities.size(), entityClass.getSimpleName());
		return mergedEntities;
	}

	@Override @Transactional(readOnly=true)
	public long count(Collection<ID> ids) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		// SELECT COUNT(*) FROM entityClass WHERE ...
		final CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
		final Root<T> countRoot = countCq.distinct(true).from(entityClass);
		countCq.select(cb.countDistinct(countRoot));
		countCq.where(countRoot.get("id").in(ImmutableList.copyOf(ids)));
		final long totalRowCount = em.createQuery(countCq).getSingleResult();
		log.debug("count {} row(s) by {} ids: {}", totalRowCount, ids.size(), ids);
		return totalRowCount;
	}
	
	@Override @Transactional(readOnly=true)
	public long count() {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
		final Root<T> countRoot = countCq.distinct(true).from(entityClass);
		countCq.select(cb.countDistinct(countRoot));
		final long totalRowCount = em.createQuery(countCq).getSingleResult();
		log.debug("count {} row(s)", totalRowCount);
		return totalRowCount;
	}
	
	@Override @Transactional(readOnly=true)
	public List<T> findAll(Collection<ID> ids) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<T> cq = cb.createQuery(entityClass);
		final Root<T> root = cq.distinct(true).from(entityClass);
		cq.select(root);
		cq.where(root.get("id").in(ImmutableList.copyOf(ids)));
		
		final List<T> result = em.createQuery(cq).getResultList();
		log.debug("found {} rows by {} ids: {}", result.size(), ids.size(), ids);
		return result;
	}
	
	@Override @Transactional(readOnly=true)
	public List<T> findAll() {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<T> cq = cb.createQuery(entityClass);
		final Root<T> root = cq.distinct(true).from(entityClass);
		cq.select(root);
		
		final List<T> result = em.createQuery(cq).getResultList();
		log.debug("found all {} rows", result.size());
		return result;
	}
	
	@Override @Transactional(readOnly=true)
	public List<T> findAll(Collection<ID> ids, Sort sort) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		
		// SELECT * FROM entityClass WHERE ... ORDER BY ... LIMIT x, y
		// FROM
		final CriteriaQuery<T> cq = cb.createQuery(entityClass);
		final Root<T> root = cq.distinct(true).from(entityClass);
		cq.select(root);
		// WHERE
		cq.where(root.get("id").in(ImmutableList.copyOf(ids)));
		// ORDER BY
		final List<javax.persistence.criteria.Order> jpaOrders = FluentIterable
				.from(Optional.<Iterable<Sort.Order>>fromNullable(sort).or(ImmutableList.<Sort.Order>of()))
				.transform(new Function<Order, javax.persistence.criteria.Order>() {
			@Override
			@Nullable
			public javax.persistence.criteria.Order apply(@Nullable Order input) {
				return input.getDirection() == Direction.ASC ? cb.asc(root.get(input.getProperty())) : cb.desc(root.get(input.getProperty()));
			}
		}).toList();
		cq.orderBy(jpaOrders);
		
		final TypedQuery<T> typedQuery = em.createQuery(cq);
		final List<T> pageContent = typedQuery.getResultList();
		
		log.debug("findAll {} returned {} (sort by {}) and {} ids: {}",
				entityClass.getSimpleName(), pageContent.size(), sort, ids.size(), Iterables.limit(ids, 5));
		return pageContent;
	}
	
	@Override
	@Nullable @Transactional(readOnly=true)
	public <S extends T> S findOne(ID id) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<T> cq = cb.createQuery(entityClass);
		final Root<T> root = cq.distinct(true).from(entityClass);
		cq.select(root);
		cq.where(cb.equal(root.get(("id")), id));
		
		try {
			final T found = em.createQuery(cq).getSingleResult();
			log.debug("found by id '{}'", id);
			return (S) found;
		} catch (NoResultException e) {
			log.debug("not found by id '{}'", id);
			return null;
		}
	}

	@Override @Transactional
	public long delete(Collection<? extends T> entities) {
		for (final T t : entities) {
			em.remove(t);
		}
		return entities.size();
	}
	
	@Override @Transactional
	public long deleteIds(Collection<ID> ids) {
		log.debug("Attempt to remove {} rows", ids.size());
		final List<T> found = findAll(ids);
		for (T t : found) {
			em.remove(t);
		}
		return ids.size();
	}
	
	@Override @Transactional(readOnly=true)
	public boolean exists(ID id) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<T> root = cq.distinct(true).from(entityClass);
		
		cq.select(cb.countDistinct(root));
		cq.where(cb.equal(root.get("id"), id));
		
		final Long singleResult = em.createQuery(cq).getSingleResult();
		return singleResult.longValue() > 0;
	}
	
}
