package test;


import config.RestAssuredPropertiesConfig;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.testng.annotations.Test;
import pages.BasePage;
import utils.RestAssuredExtension;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConfigurationTest {
    private RestAssuredPropertiesConfig restAssuredPropertiesConfig =
            new RestAssuredPropertiesConfig();
    public static RestAssuredExtension rest = new RestAssuredExtension("bff");
    public static ResponseOptions<Response> response = null;

    @Test
    public void testGenerateBearerToken() {

    }

    @Test
    public void testPostSimulationSolaFirma() {
        response = RestAssuredExtension.postMethod("bff", "simulation", "bff_simulation.txt", "");
        try {
            response.getBody().prettyPrint();
            System.out.println(response.getBody().jsonPath().get("id").toString());

        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Path is invalid");
        }
    }

    @Test
    public void testGetValidationCustomerCuit() {
        response = RestAssuredExtension.getMethod("bff", "customer-validation/30597962793", "");
        try {
            response.getBody().prettyPrint();
            System.out.println(response.getBody().jsonPath().get("name").toString());
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Path is invalid");
        }
    }

    @Test
    public void testGetValidationCustomerCuitPostSimulationSolaFirma() {
        response = RestAssuredExtension.postMethod("bff", "simulation", "bff_simulation.txt", "");
        try {
            response.getBody().prettyPrint();
            System.out.println(response.getBody().jsonPath().get("id").toString());

        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Path is invalid");
        }

        response = RestAssuredExtension.getMethod("bff", "customer-validation/30597962793", "");
        try {
            response.getBody().prettyPrint();
            System.out.println(response.getBody().jsonPath().get("name").toString());
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Path is invalid");
        }

    }

    @Test
    public void testPostSimulationSolaFirmaValidationOrder() {
        response = RestAssuredExtension.postMethod("bff", "simulation", "bff_simulation.txt", "");
        try {
            response.getBody().prettyPrint();
            System.out.println(response.getBody().jsonPath().get("installments[0].amount").toString());

        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Path is invalid");
        }
    }

    @Test
    public void testGetOrders() {
        BasePage basePage = new BasePage();
        basePage.getAcessTokenFromApiServices("bff", "provider/auth/login");
        List<List<String>> t_table = null;
        //Para probar agregar paramatros a la lista
        response = RestAssuredExtension.getMethodWithParams("bff", "orders", t_table, basePage.getAccess_token());
        try {
            ArrayList list = new ArrayList<>(response.getBody().jsonPath().get("result"));
            list.stream().forEach(dataEntry -> getObjectOrder(dataEntry));
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Path is invalid");
        }
    }

    public void getObjectOrder(Object dataEntry) {
        JSONObject data = null;
        try {
            data = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(dataEntry));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String STATUS = data.get("status").toString();
        System.out.println(STATUS);

        String CREATE_DATE = data.get("create_date").toString();
        System.out.println(CREATE_DATE);

        JSONObject PRODUCERS = (JSONObject) data.get("producer");
        String PRODUCER_CUIT = PRODUCERS.get("cuit").toString();
        System.out.println(PRODUCER_CUIT);

        String PRODUCER_NAME = PRODUCERS.get("name").toString();
        System.out.println(PRODUCER_NAME);

//        JSONArray PAYMENT_METHODS = (JSONArray) data.get("payment_methods");
//        String PAYMENT_METHOD_FINANCIAL_ENTITY = ((JSONObject)PAYMENT_METHODS.get(0)).get("financial_entity").toString();
        try {
            ArrayList list = new ArrayList<>(response.getBody().jsonPath().get("result[0].payment_methods"));
            list.stream().forEach(dataEntry1 -> getObjectPaymentMethods(dataEntry1));
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Path is invalid");
        }
    }

    public void getObjectPaymentMethods(Object dataEntry) {
        JSONObject data = null;
        try {
            data = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(dataEntry));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String financial_entity = data.get("financial_entity").toString();
        System.out.println(financial_entity);

        String financial_line = data.get("financial_line").toString();
        System.out.println(financial_line);
    }

}
