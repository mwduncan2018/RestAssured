package cucumberdemo.configuration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationProperties {
	private static String configFile = "configuration/configuration.properties";

	public static String getTestDataPath() {
		String x = readConfigFile().getProperty("testDataPath");
		if (x != null)
			return x;
		else
			throw new RuntimeException("testDataPath not found in configuration.properties!");
	}
	private static Properties readConfigFile() {
		Properties properties;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(configFile));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + configFile);
		}
		return properties;
	}
	
	
}
