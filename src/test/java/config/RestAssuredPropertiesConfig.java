package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RestAssuredPropertiesConfig {
    private static Properties prop = new Properties();
    private static final String GLOBAL_DATA_FILE_LOCATION =
            "src/test/resources/application.properties";
    public static String setBaseUriBff;
    public static String setBaseUriAgtech;
    public static String bodyData;
    public static String contentType;
    public static String environment;

    public RestAssuredPropertiesConfig() {
        RestAssuredPropertiesConfig.initConfig();
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
        setBaseUriBff =
                ((setBaseUriBff = prop.getProperty(String.format("%s.assured.setBaseUriBff", environment)))
                        != null)
                        ? setBaseUriBff
                        : prop.getProperty("assured.setBaseUriBff");
        setBaseUriAgtech =
                ((setBaseUriAgtech = prop.getProperty(String.format("%s.assured.setBaseUriAgtech", environment)))
                        != null)
                        ? setBaseUriAgtech
                        : prop.getProperty("assured.setBaseUriAgtech");
        bodyData =
                ((bodyData = prop.getProperty(String.format("%s.assured.bodyData", environment))) != null)
                        ? bodyData
                        : prop.getProperty("assured.bodyData");
        contentType =
                ((contentType = prop.getProperty(String.format("%s.assured.content-type", environment)))
                        != null)
                        ? contentType
                        : prop.getProperty("assured.content-type");
    }

    public String getEnvironment() {
        return environment;
    }

    public String getSetBaseUriBff() {
        return setBaseUriBff;
    }

    public String getSetBaseUriAgtech() {
        return setBaseUriAgtech;
    }

    public String getBodyData() {
        return bodyData;
    }

    public String getContentType() {
        return contentType;
    }
}
