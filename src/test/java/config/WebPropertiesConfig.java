package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebPropertiesConfig {
    private static Properties prop = new Properties();
    private static final String GLOBAL_DATA_FILE_LOCATION =
            "src/test/resources/application.properties";
    public static String setBaseUriProductor;
    public static String setBaseUriProveedor;
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
        setBaseUriProductor =
                ((setBaseUriProductor = prop.getProperty(String.format("%s.web.setBaseUriProductor", environment)))
                        != null)
                        ? setBaseUriProductor
                        : prop.getProperty("web.setBaseUriProductor");
        setBaseUriProveedor =
                ((setBaseUriProveedor = prop.getProperty(String.format("%s.web.setBaseUriProveedor", environment)))
                        != null)
                        ? setBaseUriProveedor
                        : prop.getProperty("web.setBaseUriProveedor");
    }

    public String getEnvironment() {
        return environment;
    }

    public String getBaseUriProductor() {
        return setBaseUriProductor;
    }

    public String getBaseUriProveedor() { return setBaseUriProveedor; }

}
