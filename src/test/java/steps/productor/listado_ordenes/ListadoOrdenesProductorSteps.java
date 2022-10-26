package steps.productor.listado_ordenes;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.productor.listado_ordenes.ListadoOrdenesProductorPage;

import java.util.List;

public class ListadoOrdenesProductorSteps {
    ListadoOrdenesProductorPage listadoOrdenesProductorPage = new ListadoOrdenesProductorPage();
    public static String countOrders;

    @Then("^El productor viaualiza los elementos de la pantalla de órdenes$")
    public void productorVerifyVisibleElementsOrdersScreen(List<List<String>> t_table) {
        Assert.assertTrue(listadoOrdenesProductorPage.verifyVisibleElementsOrdersScreen(t_table));
    }

    @When("^Comprobar datos de servicio api productor que lista todas las ordenes (.*) con ruta (.*) y parámetros$")
    public void productorCheckDataFromApiServicesAllOrders(String sourceApi, String path, List<List<String>> t_table) {
        listadoOrdenesProductorPage.checkDataFromApiServicesAllOrders(sourceApi, path, t_table);
    }

    @And("^El productor visualiza un máximo de (.*) órdenes$")
    public void productorPerifyMaxNumberOrders(String orderQuantity) {
        Assert.assertTrue(listadoOrdenesProductorPage.verifyMaxNumberOrders(orderQuantity));
    }

//    @And("^Se hace una busqueda por (.*) (.*)$")
//    public void productorDoSearchByCuitName(String searchCriteria, String criteriaStatus) {
//        listadoOrdenesProductorPage.doSearchByCuitName(searchCriteria, criteriaStatus);
//    }

//    @And("^Se muestra la pantalla de órdenes vacias$")
//    public void productorVerifyEmptyStateOrdersScrenn() {
//        Assert.assertTrue(listadoOrdenesProductorPage.verifyEmptyStateOrdersScrenn());
//    }

//    @And("^Se verifica que se muestren resultados correctos$")
//    public void productorCheckCorrectResultDisplayed() {
//        listadoOrdenesProductorPage.checkCorrectResultDisplayed();
//    }

    @And("^El productor visualiza el botón (.*) (.*)")
    public void productorCheckButtonStatus(String buttonName, String buttonStatus) {
        Assert.assertTrue(listadoOrdenesProductorPage.verifyButtonState(buttonName, buttonStatus));
    }

    @And("^El productor puede seleccionar el paginado")
    public void productorcheckPagingOptions() {
        Assert.assertTrue(listadoOrdenesProductorPage.checkPagingOptions());
    }

    @And("^El productor selecciona el paginado (.*)")
    public void productorPaginSelect(String option) {
        listadoOrdenesProductorPage.selectPaging(option);
    }

    @And("^Si el productor tiene mas de (.*) ordenes se visualiza el boton (.*) Habilitado")
    public void productorCheckButtonStatusMoreThanXOrders(String countOrders, String buttonName) {
        String buttonStatus = "";
        this.countOrders = countOrders;
        if (listadoOrdenesProductorPage.moreThanXOrders(countOrders) && buttonName.equals(">")) {
            buttonStatus = "Habilitado";
        } else {
            buttonStatus = "Deshabilitado";
        }
        Assert.assertTrue(listadoOrdenesProductorPage.verifyButtonState(buttonName, buttonStatus));
    }

    @And("^Si el productor tiene mas de (.*) ordenes se hace click en el boton (.*)")
    public void productorClickOnButtonPaging(String countOrders, String buttonName) {
        if (listadoOrdenesProductorPage.moreThanXOrders(countOrders)) {
            listadoOrdenesProductorPage.clickOnButtonPaging(buttonName);
        }
    }

    @And("^El productor visualiza el resto de las ordenes")
    public void productorCheckRestOrders() {
        if (listadoOrdenesProductorPage.moreThanXOrders(countOrders)) {
            Assert.assertTrue(listadoOrdenesProductorPage.checkRestOrders());

        }
    }

    @And("^El productor visualiza las primeras (.*) ordenes")
    public void productorVerifyFirstOrders(String orderQuantity) {
        Assert.assertTrue(listadoOrdenesProductorPage.verifyFirstOrders(orderQuantity));
    }

    @When("^El productor visualiza el titulo (.*)")
    public void productorVerifyVisibleTittle(String tittle) {
        Assert.assertTrue(listadoOrdenesProductorPage.verifyVisibleTittle(tittle));
    }
}
