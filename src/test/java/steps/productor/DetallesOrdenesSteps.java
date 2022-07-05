package steps.productor;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.productor.DetallesOrdenesPage;

public class DetallesOrdenesSteps {
    DetallesOrdenesPage detallesOrdenesPage = new DetallesOrdenesPage();

    @And("^Se hace click en el botón (.*)$")
    public void clicOnButtonByName(String buttonName) {
        detallesOrdenesPage.clickOnButtonByName(buttonName);
    }
    @And("^Se hace click sobre el detalle de una orden$")
    public void seHaceClickSocbreElDetalleDeUnaOrden() {
        detallesOrdenesPage.clickOnDetailOrder();
    }
    @Then("^Se visualiza (.*) del detalle en Sidesheet$")
    public void seVisualizaFechaYHoraDelDetalleEnSidesheet(String detail) {
        Assert.assertTrue(detallesOrdenesPage.verifyDetailsOrder(detail));
    }
}
