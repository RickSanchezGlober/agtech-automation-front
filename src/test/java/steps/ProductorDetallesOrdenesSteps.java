package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.ProductorDetallesOrdenesPage;

public class ProductorDetallesOrdenesSteps {
    ProductorDetallesOrdenesPage productorDetallesOrdenesPage = new ProductorDetallesOrdenesPage();

    @And("^Se hace click en el botón (.*)$")
    public void clicOnButtonByName(String buttonName) {
        productorDetallesOrdenesPage.clickOnButtonByName(buttonName);
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
