package steps.productor.listado_ordenes;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.productor.listado_ordenes.ListadoOrdenesFiltrarPage;

import java.util.List;

public class ListadoOrdenesFiltrarSteps {
    ListadoOrdenesFiltrarPage listadoOrdenesFiltrarPage = new ListadoOrdenesFiltrarPage();

    @And("^El productor hace click en el boton (.*) de la pantalla ordenes$")
    public void clicOnButtonByNameOrders(String buttonName) {
        listadoOrdenesFiltrarPage.clicOnButtonByNameOrders(buttonName);
    }

    @And("^Comprobar datos de servicio api productor que filtra todas las ordenes (.*) con ruta (.*) y parámetros$")
    public void productorValidateDataFromOrderFilters   (String sourceApi, String path, List<List<String>> t_table) {
        listadoOrdenesFiltrarPage.validateDataFromOrderWithFilters(sourceApi, path, t_table);
    }

    @And("^El productor selecciona el filtro (.*)$")
    public void selecctFilter(String filterName) {
        listadoOrdenesFiltrarPage.selecctFilter(filterName);
    }

    @And("^El productor visualiza los elementos de la pantalla de Filtros de órdenes$")
    public void checkElementsFiltersScreen(List<List<String>> t_table) {
        Assert.assertTrue(listadoOrdenesFiltrarPage.checkElementsFiltersScreen(t_table));
    }

    @And("^El productor visualiza el boton (.*) (.*)")
    public void checkButtonStatus(String buttonName, String buttonStatus) {
        switch (buttonStatus) {
            case "Habilitado":
                Assert.assertTrue(listadoOrdenesFiltrarPage.verifyButtonState(buttonName));
                break;
            case "Deshabilitado":
                Assert.assertFalse(listadoOrdenesFiltrarPage.verifyButtonState(buttonName));
                break;
        }
    }

//    @And("^El proveedor hace click en el boton (.*) del menu lateral$")
//    public void clicOnButtonSideMenu(String buttonName) {
//        listadoOrdenesFiltrarPage.clicOnButtonByNameSideMenu(buttonName);
//    }

    @Then("^Se muestra listado de órdenes en el rango seleccionado en productor$")
    public void verifyListOrdersOnRange() {
        listadoOrdenesFiltrarPage.verifyListOrdersInRange();
    }

//    @Then("^Se visualiza el campo (.*) con la (.*) por defecto$")
//    public void verifyDefaultValueFields(String field, String date) {
//        Assert.assertTrue(listadoOrdenesFiltrarPage.verifyDefaultValue(field, date));
//    }

    @And("^El productor hace click en el boton (.*) del home$")
    public void clicOnButtonByNameHome(String buttonName) {
        listadoOrdenesFiltrarPage.clicOnButtonByNameHome(buttonName);
    }
    @When("^Consumir api productor que lista todas las ordenes (.*) con ruta (.*) y parámetros$")
    public void getDataFromApiServicesAllOrders(String sourceApi, String path, List<List<String>> t_table) {
        listadoOrdenesFiltrarPage.getDataFromApiServicesAllOrders(sourceApi, path, t_table);
    }
    @And("^El productor hace una busqueda por (.*) (.*)$")
    public void doSearchByCuitName(String searchCriteria, String criteriaStatus) {
        listadoOrdenesFiltrarPage.doSearchByCuitName(searchCriteria, criteriaStatus);
    }
    @And("^El productor visualiza los resultados correctos$")
    public void checkCorrectResultDisplayed() {
        listadoOrdenesFiltrarPage.checkCorrectResultDisplayed();
    }
    @And("^El productor visualiza la pantalla de órdenes vacias$")
    public void verifyEmptyStateOrdersScrenn() {
        Assert.assertTrue(listadoOrdenesFiltrarPage.verifyEmptyStateOrdersScrenn());
    }
}
