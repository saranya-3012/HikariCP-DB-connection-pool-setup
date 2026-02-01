package poolset;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DBConfiguration {

	// Connection Pool (Reuse DB Connection)
    private static HikariDataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
        	
        	// HikariConfig used to store all the configuration settings for database connection pool
            HikariConfig config = new HikariConfig();
            
            config.setJdbcUrl("jdbc:mysql://localhost:3306/bangtan_db");
            config.setUsername("root");
            config.setPassword("root");
            
            
            config.setMaximumPoolSize(10);     // Max 10 active connections at a time
            config.setMinimumIdle(2);          // Idle Connection is ready-to-use connections sitting in the pool
                                               // Here Always keep 2 idle connections ready
            config.setIdleTimeout(30000);      // Idle connections get closed after 30 seconds
            config.setConnectionTimeout(30000); // Wait max 20 seconds for a free connection before failing
            
            // Creates a pool of ready-to-use database connections
            dataSource = new HikariDataSource(config);
        }
        return dataSource;
    }
}

