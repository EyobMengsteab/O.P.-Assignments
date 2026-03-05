package ntnu.idata2305;

import java.io.InputStream;
import java.util.Properties;

/**
 * Loads and provides access to configuration properties from a file in the resources directory.
 */
public class Config {
  /**
   * The loaded properties.
   */
  private Properties properties = new Properties();

  /**
   * Loads properties from the specified file in the classpath resources.
   * If the file is not found or an error occurs, default values will be used.
   *
   * @param filename the name of the properties file to load
   */
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

  /**
   * Retrieves an integer property value by key, or returns the default value if not found.
   *
   * @param key the property key
   * @param defaultValue the value to return if the property is not found
   * @return the integer property value, or defaultValue if not found
   */
  public int getInt(String key, int defaultValue) {
    return Integer.parseInt(properties.getProperty(key, String.valueOf(defaultValue)));
  }

  /**
   * Retrieves a string property value by key, or returns the default value if not found.
   *
   * @param key the property key
   * @param defaultValue the value to return if the property is not found
   * @return the string property value, or defaultValue if not found
   */
  public String getString(String key, String defaultValue) {
    return properties.getProperty(key, defaultValue);
  }
}