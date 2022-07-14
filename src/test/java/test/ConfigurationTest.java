package test;

import config.RestAssuredPropertiesConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RestAssuredExtension;

public class ConfigurationTest {
   private RestAssuredPropertiesConfig restAssuredPropertiesConfig =
      new RestAssuredPropertiesConfig();
  public static RestAssuredExtension rest = new RestAssuredExtension("bff");

  @Test
  public void testConfigProperties() {
    Assert.assertEquals(restAssuredPropertiesConfig.getContentType(), "application/x-amz-json-1.1");
  }


  @Test
  public void testGenerateBearerToken() {
       rest.generateBearerToken();
  }
}
