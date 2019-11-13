package com.rudiwijaya.grudi.dao;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.google.common.collect.ImmutableMap;
import com.rudiwijaya.grudi.JpaConfig;
import com.rudiwijaya.grudi.security.JpaRealm;

@Configuration
@Import(JpaConfig.class)
public class SecurityConfig {
	
	private static final Logger log = LoggerFactory
			.getLogger(SecurityConfig.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	public void init() {
		log.info("Creating Realm Security using em '{}'", em);
	}
	
	@Bean
	public Realm jpaRealm() {
		return new JpaRealm(em);
	}

	@Bean(destroyMethod="destroy")
	public DefaultWebSecurityManager securityManager() {
		final DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager(jpaRealm());
		return webSecurityManager;
		
	}
	
	/**
	 * @return
	 * @throws IOException
	 * @see http://azurvii.blogspot.com/2014/04/configuring-apache-shiro-shiro-web.html
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter() throws IOException {
		log.info("Initializing ShiroFilter with SecurityManager={} with {} realms: {}", 
				securityManager(), securityManager().getRealms().size(), securityManager().getRealms());
		final ShiroFilterFactoryBean factory = new ShiroFilterFactoryBean();
		factory.setSecurityManager(securityManager());
		
		final BasicHttpAuthenticationFilter basicFilter = new BasicHttpAuthenticationFilter();
		basicFilter.setApplicationName("Payment OAuth 2.0");
		factory.getFilters().put("authcBearer", basicFilter);
		factory.setFilterChainDefinitionMap(ImmutableMap.of("/api/**", "authcBearer[permissive]"));
		
		return factory;
	}
	
}
