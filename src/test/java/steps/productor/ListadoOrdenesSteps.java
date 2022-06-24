package steps.productor;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.productor.ListadoOrdenesPage;

public class ListadoOrdenesSteps {
    ListadoOrdenesPage listadoOrdenesPage = new ListadoOrdenesPage();

    @Then("^Se visualiza (.*) en la pantalla Empty State$")
    public void seVisualizaElementEnLaPantallaDeEmptyState(String element) {
        Assert.assertTrue(listadoOrdenesPage.verifyElementEmptyStateScreen(element));
    }

    @Then("^Se muestra un listado de (.*) órdenes$")
    public void seMuestraListadoOrdenes(String orderQuantity) {
        Assert.assertTrue(listadoOrdenesPage.verifyOrderQuantity(orderQuantity));
    }

    @And("^Se muestra (.*) de las (.*) órdenes$")
    public void seMuestraDetalleDeOrdenes(String details, String orderQuantity) {
        Assert.assertTrue(listadoOrdenesPage.verifyOrderDetails(details, orderQuantity));
    }

    @And("^Se muestra un listado de órdenes (.*)$")
    public void seMuestraListaDeOrdenesPorFecha(String sort) {
        Assert.assertTrue(listadoOrdenesPage.verifySortOrders(sort));
    }

    @And("^La conexion con el MS orders/producer se realiza correctamente$")
    public void laConexionConElMSOrdersProducerSeRealizaCorrectamente() {
        listadoOrdenesPage.msOrdersProducerVolver();
    }
}
