package steps.proveedor;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.proveedor.ListadoOrdenesPage;

import java.util.List;

public class ListadoOrdenesSteps {
    ListadoOrdenesPage listadoOrdenesPage = new ListadoOrdenesPage();

    @Then("^Se viaualizan los elementos de la pantalla de órdenes$")
    public void verifyVisibleElementsOrdersScreen(List<List<String>> t_table) {
        Assert.assertTrue(listadoOrdenesPage.verifyVisibleElementsOrdersScreen(t_table));
    }
    @When("^Verificar datos de servicio api que lista todas las ordenes (.*) con ruta (.*) y parámetros$")
    public void getDataFromApiServicesAllOrders(String sourceApi, String path, List<List<String>> t_table) {
        listadoOrdenesPage.getDataFromApiServicesAllOrders(sourceApi, path, t_table);
    }
    @And("^Se visualizan un máximo de (.*) órdenes$")
    public void verifyMaxNumberOrders(String orderQuantity) {
        Assert.assertTrue(listadoOrdenesPage.verifyMaxNumberOrders(orderQuantity));
    }
}
