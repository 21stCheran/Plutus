package com.twentyonec.Plutus.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Storage {
	
	private static Storage storage;
	
	private final String hostname;
	private final String port;
	private final String username;
	private final String password;
	private final String database;
	
	private HikariDataSource dataSource;
	
	private Storage(final String hostname, final String port, final String username,
			final String password, final String database) {

		this.hostname = hostname;
		this.port = port;
		this.username = username;
		this.password = password;
		this.database = database;
	}
	
	public static synchronized Storage getStorage() {

		if (storage == null) {
//			final Config config = plugin.getConfigManager();
//			storage = new Storage(config.getHostname(), config.getPort(), config.getUsername(),
//					config.getPassword(), config.getDatabase());
		}

		return storage;
	}
	
	private void connect() {

		final String jdbcUrl = "jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database;

		final HikariConfig config = new HikariConfig();

		config.setPoolName("itemslogger");
		config.setJdbcUrl(jdbcUrl);
		config.setUsername(this.username);
		config.setPassword(this.password);

		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		config.addDataSourceProperty("useServerPrepStmts", "true");
		config.addDataSourceProperty("rewriteBatchedStatements", "true");
		config.addDataSourceProperty("maintainTimeStats", "false");

		config.setMaximumPoolSize(10);
		config.setMinimumIdle(10);
		config.setIdleTimeout(300000);
		config.setMaxLifetime(600000);
		config.setConnectionTimeout(5000);
		config.setInitializationFailTimeout(-1);

		try {
			this.dataSource = new HikariDataSource(config);
		} catch (final Exception e) {
			e.printStackTrace();
		}

	}
	
	private Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}
	
	public void closeConnection() throws SQLException {
		this.dataSource.close();
	}
	
	public void update(final String sql, final Object... args) {

//		plugin.debugMessage("Preparing statement for update");
		try (Connection connection = this.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			for (int i = 1; i <= args.length; i++) {
				statement.setObject(i, args[i - 1]);
			}

			statement.executeUpdate();
//			plugin.debugMessage("Successfully executed update statement. (" + sql + ")");

		} catch (final SQLException e) {
//			plugin.debugMessage("Error while attempting to execute statement update. (" + sql + ")");
			e.printStackTrace();
		}

	}
	
	public ResultSet query(final String sql, final Object... args) {

		try (Connection connection = this.getConnection()){

			final PreparedStatement statement = connection.prepareStatement(sql);

//			plugin.debugMessage("Preparing statement for query.");
			for (int i = 1; i <= args.length; i++) {
				statement.setObject(i, args[i-1]);
			}

			final ResultSet resultSet = statement.executeQuery();
//			plugin.debugMessage("Successfully executed query statement. (" + sql + ")");

			return resultSet;
		} catch (final SQLException e) {
//			plugin.debugMessage("Error while attempting to execute query statement. (" + sql + ")");
			e.printStackTrace();
			return null;
		}
	}
	
	//TODO -> modify into your table(s).
	public void setUpTable() {
		this.connect();

//		plugin.debugMessage("Attempting to set up tables if they do not exist.");
		final String createTableQuery = "CREATE TABLE IF NOT EXISTS itemslogger("
				+ "uuid VARCHAR(36) NOT NULL, "
				+ "inventory TEXT NOT NULL, "
				+ "cause VARCHAR(255) NOT NULL, "
				+ "loc_x REAL NOT NULL, "
				+ "loc_y REAL NOT NULL, "
				+ "loc_z REAL NOT NULL, "
				+ "experience REAL NOT NULL, "
				+ "date DATE NOT NULL, "
				+ "time TIME NOT NULL);";
		this.update(createTableQuery);
	}

}
