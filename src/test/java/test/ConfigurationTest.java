package test;

import config.RestAssuredPropertiesConfig;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RestAssuredExtension;

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
        response = RestAssuredExtension.postMethod("bff", "simulation", "bff_simulation.txt","");
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
        response = RestAssuredExtension.getMethod("bff", "customer-validation/30597962793","");
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
        response = RestAssuredExtension.postMethod("bff", "simulation", "bff_simulation.txt","");
        try {
            response.getBody().prettyPrint();
            System.out.println(response.getBody().jsonPath().get("id").toString());

        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Path is invalid");
        }

        response = RestAssuredExtension.getMethod("bff", "customer-validation/30597962793","");
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
        response = RestAssuredExtension.postMethod("bff", "simulation", "bff_simulation.txt","");
        try {
            response.getBody().prettyPrint();
            System.out.println(response.getBody().jsonPath().get("installments[0].amount").toString());

        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Path is invalid");
        }
    }
}
