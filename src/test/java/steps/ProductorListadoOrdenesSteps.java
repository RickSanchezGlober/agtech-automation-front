package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.testng.Assert;
import pages.ProductorListadoOrdenesPage;

public class ProductorListadoOrdenesSteps {
    public static ProductorListadoOrdenesPage productorListadoOrdenesPage = new ProductorListadoOrdenesPage();

    @Then("^Se visualiza (.*) en la pantalla Empty State$")
    public void seVisualizaElementEnLaPantallaDeEmptyState(String element) {
        Assert.assertTrue(productorListadoOrdenesPage.verifyElementEmptyStateScreen(element));
    }

    @Then("^Se muestra un listado de (.*) órdenes$")
    public void seMuestraListadoOrdenes(String orderQuantity) {
        Assert.assertTrue(productorListadoOrdenesPage.verifyOrderQuantity(orderQuantity));
    }
    @And("^Se muestra (.*) de las (.*) órdenes$")
    public void seMuestraDetalleDeOrdenes(String details, String orderQuantity) {
        Assert.assertTrue(productorListadoOrdenesPage.verifyOrderDetails(details,orderQuantity));
    }
    @And("^Se muestra un listado de órdenes (.*)")
    public void seMuestraListaDeOrdenesPorFecha( String sort) {
        Assert.assertTrue(productorListadoOrdenesPage.verifySortOrders(sort));
    }
}
