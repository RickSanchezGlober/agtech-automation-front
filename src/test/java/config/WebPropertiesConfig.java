package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebPropertiesConfig {
  private static Properties prop = new Properties();
  private static final String GLOBAL_DATA_FILE_LOCATION =
      "src/test/resources/application.properties";
  public static String setBaseUri;
  public static String environment;

  public WebPropertiesConfig() {
    WebPropertiesConfig.initConfig();
  }

  public static void initConfig() {
    try {
      InputStream input = null;
      input = new FileInputStream(GLOBAL_DATA_FILE_LOCATION);
      prop.load(input);
    } catch (IOException e) {
      e.printStackTrace();
    }
    environment =
        ((environment = prop.getProperty("env")) != null)
                || (environment = prop.getProperty("env")) == ""
            ? environment
            : null;
    setBaseUri =
        ((setBaseUri = prop.getProperty(String.format("%s.web.setBaseUri", environment)))
                != null)
            ? setBaseUri
            : prop.getProperty("web.setBaseUri");
  }

  public String getEnvironment() {
    return environment;
  }

  public String getBaseUri() {
    return setBaseUri;
  }

}
