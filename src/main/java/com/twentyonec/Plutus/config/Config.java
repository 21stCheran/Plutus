package com.twentyonec.Plutus.config;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.twentyonec.Plutus.Plutus;

public class Config {
	private Map<String, Object> yamlData;
	
	private final String USERNAME_PATH = "mysql.username";
	private final String PASSWORD_PATH = "mysql.password";
	private final String HOSTNAME_PATH = "mysql.hostname";
	private final String PORT_PATH = "mysql.port";
	private final String DATABASE_PATH = "mysql.database";
	
	public Config() {
        try {
            URL resource = Plutus.class.getResource("/yaml/storage_config.yml");
            if (resource == null) {
                throw new IllegalStateException("Could not find the storage_config.yml file.");
            }

            InputStream inputStream = resource.openStream();
            Yaml yaml = new Yaml();
            yamlData = yaml.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load the configuration file.", e);
        }
    }
	
	@SuppressWarnings("unchecked")
    private String getValue(String path) {
        String[] keys = path.split("\\.");
        Map<String, Object> currentMap = yamlData;
        for (int i = 0; i < keys.length - 1; i++) {
            currentMap = (Map<String, Object>) currentMap.get(keys[i]);
        }
        return currentMap.get(keys[keys.length - 1]).toString();
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
