package com.rudiwijaya.grudi.dao;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author clutax
 *
 */
@Configuration
public class JpaRepositoryConfig {
	
	private static final Logger log = LoggerFactory.getLogger(JpaRepositoryConfig.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	public void init() {
		log.info("Creating Jpa Repositories using em '{}'", em);
	}
	
	@Bean
	public JpaPersonRepository personRepo() {
		return new JpaPersonRepositoryImpl(em);
	}
	
	@Bean
	public JpaTeamRepository teamRepo() {
		return new JpaTeamRepositoryImpl(em);
	}
	
}
