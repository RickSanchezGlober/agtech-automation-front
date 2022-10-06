package steps.productor.listado_ordenes;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.productor.listado_ordenes.ListadoOrdenesPage;

import java.util.List;

public class ListadoOrdenesSteps {
    ListadoOrdenesPage listadoOrdenesPage = new ListadoOrdenesPage();

    @Then("^Se visualiza (.*) en la pantalla Empty State$")
    public void seVisualizaElementEnLaPantallaDeEmptyState(String element) {
        Assert.assertTrue(listadoOrdenesPage.verifyElementEmptyStateScreen(element));
    }

    @And("^Se muestra un listado de órdenes (.*)$")
    public void seMuestraListaDeOrdenesPorFecha(String sort) {
        Assert.assertTrue(listadoOrdenesPage.verifySortOrders(sort));
    }

    @And("^La conexion con el MS orders/producer se realiza correctamente$")
    public void laConexionConElMSOrdersProducerSeRealizaCorrectamente() {
        listadoOrdenesPage.msOrdersProducerVolver();
    }

    @And("^El productor visualiza los elementos de la HomePage$")
    public void checkHomePageElements() {
        Assert.assertTrue(listadoOrdenesPage.checkHomePageElements());
    }

    @Then("^Verificar datos de servicio api que lista todas las ordenes (.*) con ruta (.*) y parámetros$")
    public void getDataFromApiServicesAllOrders(String sourceApi, String path, List<List<String>> t_table) {
        listadoOrdenesPage.getDataFromApiServicesAllOrders(sourceApi, path, t_table);
    }
}
