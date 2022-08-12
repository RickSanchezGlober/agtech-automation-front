package steps.proveedor.listado_ordenes;

import io.cucumber.java.en.And;
import org.testng.Assert;
import pages.proveedor.listado_ordenes.ListadoOrdenesFiltrarPage;

import java.util.List;

public class ListadoOrdenesFiltrarSteps {
    ListadoOrdenesFiltrarPage listadoOrdenesFiltrarPage = new ListadoOrdenesFiltrarPage();

    @And("^El proveedor hace click en el boton (.*) de la pantalla ordenes$")
    public void clicOnButtonByNameOrders(String buttonName) {
        listadoOrdenesFiltrarPage.clicOnButtonByNameOrders(buttonName);
    }

    @And("^Comprobar datos de servicio api que filtra todas las ordenes (.*) con ruta (.*) y parámetros$")
    public void validateDataFromOrderWithFilters(String sourceApi, String path, List<List<String>> t_table) {
        listadoOrdenesFiltrarPage.validateDataFromOrderWithFilters(sourceApi, path, t_table);
    }

    @And("^Se selecciona el filtro (.*)$")
    public void selecctFilter(String filterName) {
        listadoOrdenesFiltrarPage.selecctFilter(filterName);
    }

    @And("^Se viaualizan los elementos de la pantalla de Filtros de órdenes$")
    public void checkElementsFiltersScreen(List<List<String>> t_table) {
        Assert.assertTrue(listadoOrdenesFiltrarPage.checkElementsFiltersScreen(t_table));
    }

    @And("^Se visualiza el boton (.*) (.*)")
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
    @And("^El proveedor hace click en el boton (.*) del menu lateral$")
    public void clicOnButtonByNameSideMenu(String buttonName) {
        listadoOrdenesFiltrarPage.clicOnButtonByNameSideMenu(buttonName);
    }
}
