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
    public static String setBaseLoginErrorUriProductor;
    public static String setBaseLoginErrorUriProveedor;
    public static String setBaseSignupErrorUriProductor;
    public static String setBaseSignupErrorUriProveedor;
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
        setBaseLoginErrorUriProductor =
                ((setBaseLoginErrorUriProductor = prop.getProperty(String.format("%s.web.setBaseLoginErrorUriProductor", environment)))
                        != null)
                        ? setBaseUriProveedor
                        : prop.getProperty("web.setBaseLoginErrorUriProductor");
        setBaseSignupErrorUriProductor=
                ((setBaseSignupErrorUriProductor = prop.getProperty(String.format("%s.web.setBaseSignupErrorUriProductor", environment)))
                        != null)
                        ? setBaseSignupErrorUriProductor
                        : prop.getProperty("web.setBaseSignupErrorUriProductor");
        setBaseLoginErrorUriProveedor=
                ((setBaseLoginErrorUriProveedor = prop.getProperty(String.format("%s.web.setBaseLoginErrorUriProveedor", environment)))
                        != null)
                        ? setBaseLoginErrorUriProveedor
                        : prop.getProperty("web.setBaseLoginErrorUriProveedor");
        setBaseSignupErrorUriProveedor=
                ((setBaseSignupErrorUriProveedor = prop.getProperty(String.format("%s.web.setBaseSignupErrorUriProveedor", environment)))
                        != null)
                        ? setBaseSignupErrorUriProveedor
                        : prop.getProperty("web.setBaseSignupErrorUriProveedor");
    }

    public String getEnvironment() {
        return environment;
    }

    public String getBaseUriProductor() {
        return setBaseUriProductor;
    }

    public String getBaseUriProveedor() { return setBaseUriProveedor; }

    public String getBaseLoginErrorUriProductor() { return setBaseLoginErrorUriProductor; }

    public String getBaseSignupErrorUriProductor() { return setBaseSignupErrorUriProductor; }

    public String getBaseLoginErrorUriProveedor() { return setBaseLoginErrorUriProveedor; }

    public String getBaseSignupErrorUriProveedor() { return setBaseSignupErrorUriProveedor; }

}
