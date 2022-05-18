package utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Logger;

public class RestAssuredExtension {
  public static RequestSpecification request;
  public static ResponseOptions<Response> response = null;
  public static RequestSpecBuilder builder = new RequestSpecBuilder();
  public static RequestSpecBuilder builderMW = new RequestSpecBuilder();
  public static ContentType content;
  private static Properties prop = new Properties();
  private static final String GLOBAL_DATA_FILE_LOCATION =
      "src/test/resources/application.properties";
  public static String setBaseUri;
  public static String bodyData;
  public static Logger log = Logger.getLogger(String.valueOf(RestAssuredExtension.class));

  public static void initConfig() {
    try {
      InputStream input = null;
      input = new FileInputStream(GLOBAL_DATA_FILE_LOCATION);
      prop.load(input);
    } catch (IOException e) {
      e.printStackTrace();
    }
    setBaseUri = prop.getProperty("assured.setBaseUri");
    bodyData = prop.getProperty("assured.bodyData");
  }

  public static String generateBodyFromResource(String path) {
    String bodyPath = null;
    try {
      bodyPath = new String(Files.readAllBytes(_path(path)));
      return bodyPath;
    } catch (IOException e) {
      log.info("check configProperties or path variable");
      return null;
    }
  }

  public static Path _path(String path) {
    return Paths.get(bodyData + path);
  }

  public static ResponseOptions<Response> postMethod(String path, String body) {
    response = null;
    setDefaultHeaders();
    RestAssuredConfig config = RestAssured.config();
    config.httpClient(
        HttpClientConfig.httpClientConfig()
            .setParam("http.socket.timeout", 5000)
            .setParam("http.connection.timeout", 5000));

    try {
      initConfig();
      builderMW.setBaseUri(setBaseUri);
      builderMW.setBody(generateBodyFromResource(body)).setContentType(ContentType.TEXT);
      builderMW.setAccept(ContentType.JSON);
      builderMW.setContentType(ContentType.JSON);
      builderMW.setConfig(config);
      request = RestAssured.given().spec(builderMW.build());
      response = request.post(new URI(path));
    } catch (URISyntaxException e) {
      log.info("* Error in postMethod *");
      e.printStackTrace();
    }
    return response;
  }

  private static void setDefaultHeaders() {
    builder.addHeader("Content-Type", "application/json; charset=utf-8");
  }

  public static ResponseOptions<Response> getMethod(String path) {
    response = null;
    setDefaultHeaders();
    try {
      initConfig();
      builderMW.setBaseUri(setBaseUri);
      request = RestAssured.given().spec(builderMW.build());
      response = request.get(new URI(path));
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    return response;
  }

  public static ResponseOptions<Response> putMethod(String path, String body) {
    response = null;
    setDefaultHeaders();
    RestAssuredConfig config = RestAssured.config();
    config.httpClient(
        HttpClientConfig.httpClientConfig()
            .setParam("http.socket.timeout", 5000)
            .setParam("http.connection.timeout", 5000));
    try {
      initConfig();
      builderMW.setBaseUri(setBaseUri);
      builderMW.setBody(generateBodyFromResource(body)).setContentType(ContentType.TEXT);
      builderMW.setAccept(ContentType.JSON);
      builderMW.setContentType(ContentType.JSON);
      builderMW.setConfig(config);
      request = RestAssured.given().spec(builderMW.build());
      response = request.put(new URI(path));
    } catch (URISyntaxException e) {
      log.info("* Error in putMethod *");
      e.printStackTrace();
    }
    return response;
  }
}
