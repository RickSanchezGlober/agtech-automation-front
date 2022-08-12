package steps.proveedor.autenticacion;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.proveedor.autenticacion.LoginPage;

import static java.lang.Thread.sleep;

public class LoginSteps {
   LoginPage loginPage = new LoginPage();
   @Then("^El proveedor visualiza el mensaje (.*)$")
   public void verifyMessageWellcome(String message) {
      Assert.assertTrue(loginPage.verifyMessageWellcome(message));
   }
}
