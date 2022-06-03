package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.ProductorDetallesOrdenesPage;

public class ProductorDetallesOrdenesSteps {
    public static ProductorDetallesOrdenesPage productorDetallesOrdenesPage = new ProductorDetallesOrdenesPage();


    @And("^Se hace click en el boton (.*)$")
    public void clicnOnButtonName(String buttonName) {
        productorDetallesOrdenesPage.clickOnButton(buttonName);
    }

    @And("^Se hace click sobre el detalle de una orden$")
    public void seHaceClickSocbreElDetalleDeUnaOrden() {
        productorDetallesOrdenesPage.clickOnDetailOrder();
    }

    @Then("^Se visualiza (.*) del detalle en Sidesheet$")
    public void seVisualizaFechaYHoraDelDetalleEnSidesheet(String detail) {
        Assert.assertTrue(productorDetallesOrdenesPage.verifyDetailsOrder(detail));
    }
}
