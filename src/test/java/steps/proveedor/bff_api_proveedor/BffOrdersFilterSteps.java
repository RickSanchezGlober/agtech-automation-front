package steps.proveedor.bff_api_proveedor;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.proveedor.bff_api_proveedor.BffOrdersFilterPage;

import java.util.List;

public class BffOrdersFilterSteps {
    BffOrdersFilterPage bffOrdersFilterPage = new BffOrdersFilterPage();

    @Given("^Se obtiene el token con servicio (.*) con ruta (.*) y body (.*)$")
    public void getTokenFromLoginApi(String source, String path, String body) {
        bffOrdersFilterPage.getTokenFromLoginApi(source, path, body);
    }

    @Then("^El servicio Proveedor responde status (.*)$")
    public void verifyStatusCodeProveedor(String statusCode) {
        Assert.assertTrue(bffOrdersFilterPage.verifyServiceStatusCodeProveedor(statusCode));
    }

    @When("^Se obtienen ordenes con servicio (.*) con ruta (.*) y parametros$")
    public void getOrdersWhitFilter(String sourceApi, String path, List<List<String>> t_table) {
        bffOrdersFilterPage.getOrdersWhitFilter(sourceApi, path, t_table);
    }

    @Then("^Se verifica el response body del servicio (.*)$")
    public void verifyResponseApiOrders(String serviceName) {
        bffOrdersFilterPage.verifyResponseApiOrders(serviceName);
    }
}
