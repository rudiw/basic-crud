package com.rudiwijaya.grudi.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import com.rudiwijaya.grudi.jpa.Person;

/**
 * @author rudi
 *
 */
public class JpaPersonRepositoryImpl extends JpaRepositoryImpl<Person, Long> implements JpaPersonRepository {
	
	public JpaPersonRepositoryImpl(final EntityManager em) {
		super(Person.class, em);
	}

	@Override @Transactional(readOnly=true)
	public Person findOneByUsername(String username) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Person> cq = cb.createQuery(Person.class);
		final Root<Person> root = cq.distinct(true).from(Person.class);
		cq.select(root);
		cq.where(cb.equal(root.get(("username")), username));
		
		try {
			final Person found = em.createQuery(cq).getSingleResult();
			log.debug("Found by username '{}'", username);
			return found;
		} catch (NoResultException e) {
			log.debug("Not found by username '{}'", username);
			return null;
		}
	}

	@Override @Transactional(readOnly=true)
	public boolean existsByUsername(String username) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Person> root = cq.distinct(true).from(Person.class);
		cq.select(cb.countDistinct(root));
		cq.where(cb.equal(root.get(("username")), username));
		
		try {
			final Long found = em.createQuery(cq).getSingleResult();
			log.debug("Found by username '{}'", username);
			return found == 1;
		} catch (NoResultException e) {
			log.debug("Not found by username '{}'", username);
			return false;
		}
	}
	
}