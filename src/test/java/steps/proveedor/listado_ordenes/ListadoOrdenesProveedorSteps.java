package steps.proveedor.listado_ordenes;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.proveedor.listado_ordenes.ListadoOrdenesProveedorPage;

import java.util.List;

public class ListadoOrdenesProveedorSteps {
    ListadoOrdenesProveedorPage listadoOrdenesProveedorPage = new ListadoOrdenesProveedorPage();
    public static String countOrders;

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
    public void doSearchByCuitName(String searchCriteria, String criteriaStatus) {
        listadoOrdenesProveedorPage.doSearchByCuitName(searchCriteria, criteriaStatus);
    }

    @And("^Se muestra la pantalla de órdenes vacias$")
    public void verifyEmptyStateOrdersScrenn() {
        Assert.assertTrue(listadoOrdenesProveedorPage.verifyEmptyStateOrdersScrenn());
    }

    @And("^Se verifica que se muestren resultados correctos$")
    public void checkCorrectResultDisplayed() {
        listadoOrdenesProveedorPage.checkCorrectResultDisplayed();
    }

    @And("^Se visualiza el botón (.*) (.*)")
    public void checkButtonStatus(String buttonName, String buttonStatus) {
        Assert.assertTrue(listadoOrdenesProveedorPage.verifyButtonState(buttonName, buttonStatus));
    }

    @And("^Se puede seleccionar el paginado")
    public void checkPagingOptions() {
        Assert.assertTrue(listadoOrdenesProveedorPage.checkPagingOptions());
    }

    @And("^Se selecciona el paginado (.*)")
    public void selectPaging(String option) {
        listadoOrdenesProveedorPage.selectPaging(option);
    }

    @And("^Si el proveedor tiene mas de (.*) ordenes se visualiza el boton (.*) Habilitado")
    public void checkButtonStatusMoreThanXOrders(String countOrders, String buttonName) {
        String buttonStatus = "";
        this.countOrders = countOrders;
        if (listadoOrdenesProveedorPage.moreThanXOrders(countOrders) && buttonName.equals(">")) {
            buttonStatus = "Habilitado";
        } else {
            buttonStatus = "Deshabilitado";
        }
        Assert.assertTrue(listadoOrdenesProveedorPage.verifyButtonState(buttonName, buttonStatus));
    }

    @And("^Si el proveedor tiene mas de (.*) ordenes se hace click en el boton (.*)")
    public void clickOnButtonPaging(String countOrders, String buttonName) {
        this.countOrders = countOrders;
        if (listadoOrdenesProveedorPage.moreThanXOrders(countOrders)) {
            listadoOrdenesProveedorPage.clickOnButtonPaging(buttonName);
        }
    }

    @And("^Se visualizan el resto de las ordenes")
    public void checkRestOrders() {
        if (listadoOrdenesProveedorPage.moreThanXOrders(countOrders)) {
            Assert.assertTrue(listadoOrdenesProveedorPage.checkRestOrders());
        }
    }

    @And("^Se visualizan las primeras (.*) ordenes")
    public void verifyFirstOrders(String orderQuantity) {
        if (listadoOrdenesProveedorPage.moreThanXOrders(countOrders)) {
            Assert.assertTrue(listadoOrdenesProveedorPage.verifyFirstOrders(orderQuantity));
        }
    }
}
