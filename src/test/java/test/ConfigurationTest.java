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
  public void testConfigProperties() {
    Assert.assertEquals(restAssuredPropertiesConfig.getContentType(), "application/x-amz-json-1.1");
  }


  @Test
  public void testGenerateBearerToken() {
       rest.generateBearerToken();
  }
    @Test
    public void testGetSimulationSolaFirma() {
        response = RestAssuredExtension.postMethod("bff", "simulation" , "bff_simulation.txt");
        try {
            response.getBody().prettyPrint();
            System.out.println(response.getBody().jsonPath().get("id").toString());

        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Path is invalid");
        }
    }
}
