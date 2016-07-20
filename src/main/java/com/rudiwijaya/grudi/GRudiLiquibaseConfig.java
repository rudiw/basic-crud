package com.rudiwijaya.grudi;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.core.PostgresDatabase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

/**
 * @author rudi
 *
 */
@Configuration
@Import(PostgresConfig.class)
public class GRudiLiquibaseConfig {
	
	private static final String G_RUDI_LIQUIBASE_PATH = "com/rudiwijaya/grudi/grudi.liquibase.xml";
	
	private static final Logger log = LoggerFactory
			.getLogger(GRudiLiquibaseConfig.class);
	
	@Inject
	private DataSource dataSource;
	
	@PostConstruct
	public void init() throws DatabaseException, SQLException, LiquibaseException {
		migrate();
	}
	
	protected void migrate() throws SQLException, LiquibaseException, DatabaseException {
		final Contexts contexts = new Contexts();
		final ClassLoaderResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor(GRudiLiquibaseConfig.class.getClassLoader());
		
		log.info("[{}] Migrating", G_RUDI_LIQUIBASE_PATH);
		try (final Connection conn = dataSource.getConnection()) {
			// TODO: SET SCHEMA is workaround for Liquibase's not setting schema for <sql>. https://liquibase.jira.com/browse/CORE-1873
			final Statement st = conn.createStatement();
			st.executeUpdate("SET SCHEMA 'public'");
			final JdbcConnection jdbc = new JdbcConnection(conn);
			final PostgresDatabase db = new PostgresDatabase();
			db.setDefaultSchemaName("public");
			try {
				db.setConnection(jdbc);
				final Liquibase liquibase = new Liquibase(G_RUDI_LIQUIBASE_PATH, resourceAccessor, db);
				liquibase.update(contexts);
			} finally {
				db.close();
			}
		}
	
	}

}
