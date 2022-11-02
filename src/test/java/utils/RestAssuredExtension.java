package utils;

import com.google.gson.JsonObject;
import config.RestAssuredPropertiesConfig;
import io.netty.util.internal.StringUtil;
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
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class RestAssuredExtension {
    public static RestAssuredPropertiesConfig configProperties = new RestAssuredPropertiesConfig();
    public static RequestSpecification request;
    public static ResponseOptions<Response> response = null;
    public static RequestSpecBuilder builder = new RequestSpecBuilder();
    public static RequestSpecBuilder builderMW;
    public static ContentType content;
    private static Properties prop = new Properties();
    public static String specificPath = "";
    public static Logger log = Logger.getLogger(String.valueOf(RestAssuredExtension.class));
    public static JsonObject collectionVariables = new JsonObject();
    public static String token;

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
    public static ResponseOptions<Response> postMethod(String sourceApi, String path, String body, String access_token) {
        builderMW = new RequestSpecBuilder();
        setDefaultHeaders();
        RestAssuredConfig config = RestAssured.config();
        config.httpClient(
                HttpClientConfig.httpClientConfig()
                        .setParam("http.socket.timeout", 5000)
                        .setParam("http.connection.timeout", 5000));
        try {
            configProperties.initConfig();
            builderMW.setBaseUri(getBaseUri(sourceApi));
            builderMW.addHeader("Authorization", "Bearer " + access_token);
            builderMW.setBody(generateBodyFromResource(body)).setContentType(ContentType.TEXT);
            builderMW.setAccept(ContentType.JSON);
            builderMW.setContentType(ContentType.JSON);
            builderMW.setConfig(config);
            request = RestAssured.given().spec(builderMW.build());
            request.log().all();
            response = request.post(new URI(path));
            log.info(response.getBody().prettyPrint());
        } catch (URISyntaxException e) {
            log.info("* Error in postMethod *");
            e.printStackTrace();
        }
        return response;
    }
    /**
     * get response from Microservices
     *
     * @param ambiente to get BaseUri.
     * @param body     a text file with body
     * @param path     to api resource.
     * @return Api responses
     */
    public static ResponseOptions<Response> postMethod(String ambiente, String path, String body) {
        specificPath = path;
        setDefaultHeaders();
        RestAssuredConfig config = RestAssured.config();
        config.httpClient(
                HttpClientConfig.httpClientConfig()
                        .setParam("http.socket.timeout", 5000)
                        .setParam("http.connection.timeout", 5000));

        try {
            configProperties.initConfig();
            builderMW.setBaseUri(getBaseUri(ambiente));
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

    public static ResponseOptions<Response> postMethodLogin(String sourceApi, String path, String body) {
        setDefaultHeaders();
        builderMW = new RequestSpecBuilder();
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
    public static ResponseOptions<Response> getMethod(String sourceApi, String path, String access_token) {
        specificPath = path;
        builderMW = new RequestSpecBuilder();
        setDefaultHeaders();
        try {
            configProperties.initConfig();
            builderMW.setBaseUri(getBaseUri(sourceApi));
            builderMW.addHeader("Authorization", "Bearer " + access_token);
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
    public static ResponseOptions<Response> putMethod(String sourceApi, String path, String body, String access_token) {
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
            builderMW.addHeader("Authorization", "Bearer " + access_token);
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

    /**
     * Get bearer token and put in header request
     */
    public static void generateBearerToken() {
        if (StringUtil.isNullOrEmpty(token)) {
            try {
                token = String.format("Bearer %s", "29tIiwicGVybWlzc2lvbnMiOlsicHJjY3IiLCJwZnZmbyIsInBndmFzdCIsInByY2FyYyIsInByY2NyYSIsInBlc2RjIiwibm90aSIsInBlc2RlIiwicHJjYWJtdSIsInByY21yIiwicGZzZiJdLCJpYXQiOjE2NTc3MzMwMzQsImV4cCI6MTY2MDMyNTAzNH0.Ukftvr32A2HJnLRhiaDp-IH88KrNBW7TVXDetTa3017tLIph8BmAewI2HXL1VusXmXOefaMFx1UXkF8uffNhTdl-fh_hdyZNWzfWZ9f71EzhiUXq8T8nKiKyL89gQ3IERU1GuDXEnx78zyLSPFjBywtwJJ7N35woFmtG28x9zjw");
                builderMW.addHeader("Authorization", token);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static ResponseOptions<Response> getMethodWithParams(String sourceApi, String path, List<List<String>> t_table, String access_token) {
        specificPath = path;
        builderMW = new RequestSpecBuilder();
        setDefaultHeaders();
        //Add params
        for (int i = 0; i < t_table.size(); i++) {
            String paramName = t_table.get(i).get(0);
            String paramValue = t_table.get(i).get(1);
            builderMW.addQueryParam(paramName, paramValue);
        }

        try {
            configProperties.initConfig();
            builderMW.setBaseUri(getBaseUri(sourceApi));
            builderMW.addHeader("Authorization", "Bearer " + access_token);
            request = RestAssured.given().spec(builderMW.build());
            response = request.get(new URI(path));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static ResponseOptions<Response> getMethodWithParamsHeaderOrdersCounter(String sourceApi, String path, List<List<String>> t_table, String access_token) {
        specificPath = path;
        builderMW = new RequestSpecBuilder();
        setDefaultHeaders();
        //Add params
        for (int i = 0; i < t_table.size(); i++) {
            String paramName = t_table.get(i).get(0);
            String paramValue = t_table.get(i).get(1);
            builderMW.addQueryParam(paramName, paramValue);
        }
        builderMW.addHeader("Host", "<calculated when request is sent>");
        builderMW.addHeader("provider", "2");
        try {
            configProperties.initConfig();
            builderMW.setBaseUri(getBaseUri(sourceApi));
            builderMW.addHeader("Authorization", "Bearer " + access_token);
            request = RestAssured.given().spec(builderMW.build());
            response = request.get(new URI(path));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return response;
    }
    public static ResponseOptions<Response> getMethodWithParamsHeader(String sourceApi, String path, List<List<String>> t_table, String access_token) {
        specificPath = path;
        builderMW = new RequestSpecBuilder();
        setDefaultHeaders();
        //Add params
        for (int i = 0; i < t_table.size(); i++) {
            String paramName = t_table.get(i).get(0);
            String paramValue = t_table.get(i).get(1);
            builderMW.addQueryParam(paramName, paramValue);
        }
        builderMW.addHeader("Host", "<calculated when request is sent>");
        builderMW.addHeader("provider", "2");
        try {
            configProperties.initConfig();
            builderMW.setBaseUri(getBaseUri(sourceApi));
            builderMW.addHeader("Authorization", "Bearer " + access_token);
            request = RestAssured.given().spec(builderMW.build());
            response = request.get(new URI(path));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return response;
    }
}