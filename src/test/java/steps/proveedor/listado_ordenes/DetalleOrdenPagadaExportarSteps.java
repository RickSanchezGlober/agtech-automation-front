package steps.proveedor.listado_ordenes;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.proveedor.listado_ordenes.DetalleOrdenPagadaExportarPage;

public class DetalleOrdenPagadaExportarSteps {
    DetalleOrdenPagadaExportarPage detalleOrdenPagadaExportarPage = new DetalleOrdenPagadaExportarPage();
    @Then("^Se valida que el boton Descargar detalle exista$")
    public void verifyButtonIsEnabled() {
        Assert.assertTrue(detalleOrdenPagadaExportarPage.verifyButtonIsEnabled());
    }
}
