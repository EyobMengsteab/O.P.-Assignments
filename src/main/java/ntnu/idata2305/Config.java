package ntnu.idata2305;

import java.io.InputStream;
import java.util.Properties;

public class Config {
  private Properties properties = new Properties();

  public Config(String filename) {
    try (InputStream is = getClass().getClassLoader().getResourceAsStream(filename)) {
      if (is != null) {
        properties.load(is);
        System.out.println("Config file loaded successfully.");
      } else {
        System.out.println("Config file not found in resources. Using defaults.");
      }
    } catch (Exception e) {
      System.out.println("Error loading config. Using defaults.");
    }
  }

  public int getInt(String key, int defaultValue) {
    return Integer.parseInt(properties.getProperty(key, String.valueOf(defaultValue)));
  }

  public String getString(String key, String defaultValue) {
    return properties.getProperty(key, defaultValue);
  }
}