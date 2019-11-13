package com.rudiwijaya.grudi.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import com.rudiwijaya.grudi.jpa.Team;

/**
 * @author rudi
 *
 */
public class JpaTeamRepositoryImpl extends JpaRepositoryImpl<Team, Long> implements JpaTeamRepository {
	
	public JpaTeamRepositoryImpl(final EntityManager em) {
		super(Team.class, em);
	}
 
	@Override @Transactional(readOnly=true)
	public boolean existsByName(String name) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Team> root = cq.distinct(true).from(Team.class);
		cq.select(cb.countDistinct(root));
		cq.where(cb.equal(root.get(("name")), name));
		
		try {
			final Long found = em.createQuery(cq).getSingleResult();
			log.debug("Found by name '{}'", name);
			return found == 1;
		} catch (NoResultException e) {
			log.debug("Not found by name '{}'", name);
			return false;
		}
	}

	@Override
	public Team findOneWithLines(long id) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Team> cq = cb.createQuery(Team.class);
		final Root<Team> root = cq.distinct(true).from(Team.class);
		root.fetch("lines", JoinType.LEFT);
		
		cq.select(root).where(cb.equal(root.get("id"), id));
		
		try {
			final Team team = em.createQuery(cq).getSingleResult();
			log.debug("Team {} with {} lines", team.getId(), team.getLines().size());
			return team;
		} catch (Exception e) {
			return null;
		}
	}
	
}