package steps.proveedor.listado_ordenes;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.proveedor.listado_ordenes.HomeUltimasOperacionesPage;

import java.util.List;

public class HomeUltimasOperacionesSteps {
    HomeUltimasOperacionesPage homeUltimasOperacionesPage = new HomeUltimasOperacionesPage();

    @And("^El proveedor hace click en el boton (.*) del home$")
    public void clicOnButtonByNameHome(String buttonName) {
        homeUltimasOperacionesPage.clicOnButtonByNameHome(buttonName);
    }

    @Then("^Se muestran los elementos de la pantalla Nueva Orden$")
    public void verifyScreenNewOrder() {
        Assert.assertTrue(homeUltimasOperacionesPage.verifyScreenNewOrder());
    }

    @Then("^Se visualizan no más de (.*) órdenes$")
    public void verifyMaxNumberOrders(String orderQuantity) {
        Assert.assertTrue(homeUltimasOperacionesPage.verifyMaxNumberOrders(orderQuantity));
    }

    @When("^Validar datos de servicios api (.*) con ruta (.*) y parametros$")
    public void getDataFromApiServicesOrders(String sourceApi, String path, List<List<String>> t_table) {
        homeUltimasOperacionesPage.getDataFromApiServicesOrders(sourceApi, path, t_table);
    }

    @When("^Validar datos de servicio api (.*) con ruta (.*) y parámetros$")
    public void getDataFromApiServicesOrdersCounter(String sourceApi, String path, List<List<String>> t_table) {
        homeUltimasOperacionesPage.getDataFromApiServicesOrdersCounter(sourceApi, path, t_table);
    }

    @When("^Se viaualizan los elementos$")
    public void verifyVisibleElements(List<List<String>> t_table) {
        Assert.assertTrue(homeUltimasOperacionesPage.verifyVisibleElements(t_table));
    }

    @When("^Se visualiza el titulo (.*)")
    public void verifyVisibleTittle(String tittle) {
        Assert.assertTrue(homeUltimasOperacionesPage.verifyVisibleTittle(tittle));
    }

    @When("^Se visualiza el icono (.*)")
    public void verifyVisibleIcon(String iconName) {
        Assert.assertTrue(homeUltimasOperacionesPage.verifyVisibleIcon(iconName));
    }

    @And("^El botón (.*) funciona correctamente")
    public void checkuttonFuction(String buttonName) {
        Assert.assertTrue(homeUltimasOperacionesPage.checkuttonFuction(buttonName));
    }
}
