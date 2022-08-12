package steps.proveedor.listado_ordenes;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.proveedor.listado_ordenes.ListadoOrdenesProveedorPage;

import java.util.List;

public class ListadoOrdenesProveedorSteps {
    ListadoOrdenesProveedorPage listadoOrdenesProveedorPage = new ListadoOrdenesProveedorPage();

    @Then("^Se viaualizan los elementos de la pantalla de órdenes$")
    public void verifyVisibleElementsOrdersScreen(List<List<String>> t_table) {
        Assert.assertTrue(listadoOrdenesProveedorPage.verifyVisibleElementsOrdersScreen(t_table));
    }
    @When("^Comprobar datos de servicio api que lista todas las ordenes (.*) con ruta (.*) y parámetros$")
    public void checkDataFromApiServicesAllOrders(String sourceApi, String path, List<List<String>> t_table) {
        listadoOrdenesProveedorPage.checkDataFromApiServicesAllOrders(sourceApi, path, t_table);
    }
    @When("^Consumir api que lista todas las ordenes (.*) con ruta (.*) y parámetros$")
    public void getDataFromApiServicesAllOrders(String sourceApi, String path, List<List<String>> t_table) {
        listadoOrdenesProveedorPage.getDataFromApiServicesAllOrders(sourceApi, path, t_table);
    }
    @And("^Se visualizan un máximo de (.*) órdenes$")
    public void verifyMaxNumberOrders(String orderQuantity) {
        Assert.assertTrue(listadoOrdenesProveedorPage.verifyMaxNumberOrders(orderQuantity));
    }
    @And("^Se hace una busqueda por (.*) (.*)$")
    public void doSearchByCuitName(String searchCriteria,String criteriaStatus) {
        listadoOrdenesProveedorPage.doSearchByCuitName(searchCriteria,criteriaStatus);
    }
    @And("^Se verifica que se muestren resultados correctos$")
    public void checkCorrectResultDisplayed() {
        listadoOrdenesProveedorPage.checkCorrectResultDisplayed();
    }
}
