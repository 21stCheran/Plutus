package com.twentyonec.Plutus.config;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.twentyonec.Plutus.Plutus;

public abstract class Config {
	protected Map<String, Object> yamlData;

	public Config(String CONFIG_PATH) {
		try {
			URL resource = Plutus.class.getResource("/yaml/" + CONFIG_PATH + ".yml");
			if (resource == null) {
				throw new IllegalStateException("Could not find the " + CONFIG_PATH + ".yml file.");
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
	protected String getValue(String path) {
		String[] keys = path.split("\\.");
		Map<String, Object> currentMap = yamlData;
		for (int i = 0; i < keys.length - 1; i++) {
			currentMap = (Map<String, Object>) currentMap.get(keys[i]);
		}
		return currentMap.get(keys[keys.length - 1]).toString();
	}
}
