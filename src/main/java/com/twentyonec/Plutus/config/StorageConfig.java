package com.twentyonec.Plutus.config;

public class StorageConfig extends Config {
	private static final String CONFIG_PATH = "storage_config";

    private final String USERNAME_PATH = "mysql.username";
    private final String PASSWORD_PATH = "mysql.password";
    private final String HOSTNAME_PATH = "mysql.hostname";
    private final String PORT_PATH = "mysql.port";
    private final String DATABASE_PATH = "mysql.database";

    public StorageConfig() {
        super(CONFIG_PATH);
    }

    public String getUsername() {
        return getValue(USERNAME_PATH);
    }

    public String getPassword() {
        return getValue(PASSWORD_PATH);
    }

    public String getHostname() {
        return getValue(HOSTNAME_PATH);
    }

    public String getPort() {
        return getValue(PORT_PATH);
    }

    public String getDatabase() {
        return getValue(DATABASE_PATH);
    }
}
