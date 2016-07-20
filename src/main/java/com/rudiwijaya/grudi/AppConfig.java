package com.rudiwijaya.grudi;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.rudiwijaya.grudi.dao.BippoSecurityConfig;
import com.rudiwijaya.grudi.dao.JpaRepositoryConfig;


/**
 * @author rudi
 *
 */
@PropertySource({"classpath:/META-INF/grudi.properties"})
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@Import({PostgresConfig.class, GRudiLiquibaseConfig.class,
	BippoJpaConfig.class, BippoSecurityConfig.class, 
	JpaRepositoryConfig.class})
public class AppConfig {
	
	@Inject
	private BippoSecurityConfig bippoSecConfig;
	
	@Bean
	public WicketApplication webApp() {
	    SecurityUtils.setSecurityManager(bippoSecConfig.securityManager());
		return new WicketApplication();
	}
	
}
