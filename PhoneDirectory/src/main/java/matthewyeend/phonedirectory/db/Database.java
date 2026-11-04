/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.phonedirectory.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Set;
import java.util.regex.Pattern;
import javax.sql.DataSource;

/**
 * Builds a pooled, TLS enforced connection. Credentials come from the
 * environment only, and every URL component is validated to prevent JDBC URL
 * parameter injection.
 *
 * @author matthewyeend
 */
public final class Database implements AutoCloseable {

    private static final Pattern HOST = Pattern.compile("^[A-Za-z0-9.\\-]{1,253}$");
    private static final Pattern PORT = Pattern.compile("^[0-9]{1,5}$");
    private static final Pattern DB_NAME = Pattern.compile("^[A-Za-z0-9_]{1,64}$");
    private static final Set<String> SSL_MODES = Set.of("REQUIRED", "VERIFY_CA", "VERIFY_IDENTITY");

    private final HikariDataSource dataSource;

    /**
     * Creates the connection pool from environment variables.
     *
     * @throws IllegalStateException if configuration is missing, invalid or the
     *                               database cannot be reached
     */
    public Database() {
        String host = env("PHONEDIR_DB_HOST", "localhost");
        String port = env("PHONEDIR_DB_PORT", "3306");
        String name = env("PHONEDIR_DB_NAME", "phone_directory");
        String sslMode = env("PHONEDIR_DB_SSL_MODE", "REQUIRED");
        String user = requireEnv("PHONEDIR_DB_USER");
        String password = requireEnv("PHONEDIR_DB_PASSWORD");

        if (!HOST.matcher(host).matches() || !PORT.matcher(port).matches()
                || !DB_NAME.matcher(name).matches() || !SSL_MODES.contains(sslMode)) {
            throw new IllegalStateException("Invalid database configuration.");
        }

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + name
                + "?sslMode=" + sslMode
                + "&allowPublicKeyRetrieval=false"
                + "&allowMultiQueries=false"
                + "&connectionTimeZone=UTC");
        config.setUsername(user);
        config.setPassword(password);
        config.setPoolName("phonedir-pool");
        config.setMaximumPoolSize(5);
        config.setConnectionTimeout(5_000);

        try {
            this.dataSource = new HikariDataSource(config);
        } catch (RuntimeException ex) {
            // The cause is deliberately not chained, as it may contain connection details.
            throw new IllegalStateException("Unable to connect to the database. Check the configuration.");
        }
    }

    /**
     * Gets the pooled data source.
     *
     * @return the data source used for all database access
     */
    public DataSource dataSource() {
        return dataSource;
    }

    /**
     * Closes the connection pool and releases all connections.
     */
    @Override
    public void close() {
        dataSource.close();
    }

    /**
     * Reads an optional environment variable.
     *
     * @param key      the variable name
     * @param fallback the value used when the variable is unset or blank
     * @return the trimmed variable value, or the fallback
     */
    private static String env(String key, String fallback) {
        String value = System.getenv(key);
        return value == null || value.isBlank() ? fallback : value.strip();
    }

    /**
     * Reads a mandatory environment variable.
     *
     * @param key the variable name
     * @return the variable value
     * @throws IllegalStateException if the variable is unset or blank
     */
    private static String requireEnv(String key) {
        String value = System.getenv(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing required environment variable: " + key);
        }
        return value;
    }
}
