package com.rudiwijaya.grudi;

import java.beans.PropertyVetoException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.hibernate.cfg.DefaultComponentSafeNamingStrategy;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.google.common.collect.ImmutableMap;

/**
 * Requires {@link PostgresConfig}.
 * @author bippo
 */
@Configuration
@Import(GRudiLiquibaseConfig.class)
public class BippoJpaConfig {
	
	private static final Logger log = LoggerFactory
			.getLogger(BippoJpaConfig.class);

	@Inject
	private DataSource dataSource;
	
	public BippoJpaConfig() {
		super();
	}
	
	@PostConstruct
	public void init()  {
		log.info("Generating for Entity Manager..");
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean commercePU() throws PropertyVetoException {
		final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPersistenceUnitName("commerce"); // persistence.xml
		factoryBean.setDataSource(dataSource);
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		factoryBean.setPersistenceXmlLocation("classpath*:META-INF/donotparsepersistence.xml");
		//kalo ini setPackagesToScan di pake, maka yg setPersistenceUnitName ga kpake
		factoryBean.setPackagesToScan("com.rudiwijaya.grudi.jpa");
		final ImmutableMap.Builder<String, Object> propb = ImmutableMap.builder();
        // Must be supplied, otherwise you won't get JPA 2.1 @Index support
        // "You must specify a SQL Dialect via the hibernate.dialect property when using schema generation"
		propb.put(org.hibernate.cfg.Environment.DIALECT, PostgreSQL9Dialect.class.getName());
		// Write ALL_VALUE_KEY_LIST SQL statements to the <b>console</b> (which we don't want, since we use shell). 
		// This is an alternative to setting the log category org.hibernate.SQL to debug.
		// So please configure via logback.xml instead.
		propb.put(org.hibernate.cfg.Environment.SHOW_SQL, false);
		propb.put(org.hibernate.cfg.Environment.FORMAT_SQL, true);
		propb.put(org.hibernate.cfg.Environment.USE_SQL_COMMENTS, true);
		// https://hibernate.atlassian.net/browse/ANN-99?focusedCommentId=22432&page=com.atlassian.jira.plugin.system.issuetabpanels:comment-tabpanel#comment-22432
		propb.put(org.hibernate.ejb.HibernatePersistence.NAMING_STRATEGY, DefaultComponentSafeNamingStrategy.class.getName());
		
		final Map<String, Object> propertyMap = propb.build();
		factoryBean.setJpaPropertyMap(propertyMap);
		return factoryBean;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

	@Bean @Primary
	public JpaTransactionManager transactionManager() throws Exception {
		return new JpaTransactionManager(commercePU().getObject());
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

}
