package utils;

import config.RestAssuredPropertiesConfig;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Logger;

public class RestAssuredExtension {
    public static RestAssuredPropertiesConfig configProperties = new RestAssuredPropertiesConfig();
    public static RequestSpecification request;
    public static ResponseOptions<Response> response = null;
    public static RequestSpecBuilder builder = new RequestSpecBuilder();
    public static RequestSpecBuilder builderMW = new RequestSpecBuilder();
    public static ContentType content;
    private static Properties prop = new Properties();
    public static String specificPath = "";
    public static Logger log = Logger.getLogger(String.valueOf(RestAssuredExtension.class));

    public RestAssuredExtension(String sourceApi) {
        try {
            builder.setBaseUri(getBaseUri(sourceApi));
            builder.setContentType(ContentType.JSON);
        } catch (IllegalArgumentException e) {
            log.info("Base URI cannot be null, check configProperties");
        }
    }

    /**
     * return string value of a text in a file
     *
     * @param path path to file at src/test/resources/data/body
     */
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
        return Paths.get(configProperties.getBodyData() + path);
    }

    /**
     * get response from Microservices
     *
     * @param body a text file with body
     * @param path to api resource.
     * @return Api responses
     */
    public static ResponseOptions<Response> postMethod(String sourceApi, String path, String body) {
        response = null;
        setDefaultHeaders();
        RestAssuredConfig config = RestAssured.config();
        config.httpClient(
                HttpClientConfig.httpClientConfig()
                        .setParam("http.socket.timeout", 5000)
                        .setParam("http.connection.timeout", 5000));

        try {
            configProperties.initConfig();
            builderMW.setBaseUri(getBaseUri(sourceApi));
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

    /**
     * put default header to request
     */
    private static void setDefaultHeaders() {
        builder.addHeader("Content-Type", "application/json; charset=utf-8");
    }

    /**
     * get baseUri from application.properties
     *
     * @param sourceApi from feature.
     * @return baseUri String
     */
    private static String getBaseUri(String sourceApi) {
        String baseUri = "";
        switch (sourceApi) {
            case "bff":
                baseUri = configProperties.getSetBaseUriBff();
                break;
            case "agtech":
                baseUri = configProperties.getSetBaseUriAgtech();
                break;
        }
        return baseUri;
    }

    /**
     * get response from Api
     *
     * @param path to api resource.
     * @return Api responses
     */
    public static ResponseOptions<Response> getMethod(String sourceApi, String path) {
        specificPath = path;
        response = null;
        setDefaultHeaders();
        try {
            configProperties.initConfig();
            builderMW.setBaseUri(getBaseUri(sourceApi));
            request = RestAssured.given().spec(builderMW.build());
            response = request.get(new URI(path));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * get response from Microservices
     *
     * @param body a text file with body
     * @param path to api resource.
     * @return Api responses
     */
    public static ResponseOptions<Response> putMethod(String sourceApi, String path, String body) {
        specificPath = path;
        response = null;
        setDefaultHeaders();
        RestAssuredConfig config = RestAssured.config();
        config.httpClient(
                HttpClientConfig.httpClientConfig()
                        .setParam("http.socket.timeout", 5000)
                        .setParam("http.connection.timeout", 5000));
        try {
            configProperties.initConfig();
            builderMW.setBaseUri(getBaseUri(sourceApi));
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

    /**
     * set a content type
     *
     * @param type on format that you needed, text, json, html...
     */
    private static ContentType setContentType(String type) {
        switch (type) {
            case "TEXT":
                content = ContentType.TEXT;
                break;
            case "JSON":
                content = ContentType.JSON;
                break;
            case "HTML":
                content = ContentType.HTML;
                break;
            case "ANY":
                content = ContentType.ANY;
                break;
            case "XML":
                content = ContentType.XML;
                break;
        }
        return content;
    }
}