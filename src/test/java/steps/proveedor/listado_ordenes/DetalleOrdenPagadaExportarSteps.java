package steps.proveedor.listado_ordenes;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.proveedor.listado_ordenes.DetalleOrdenPagadaExportarPage;

import java.util.List;

public class DetalleOrdenPagadaExportarSteps {
    DetalleOrdenPagadaExportarPage detalleOrdenPagadaExportarPage = new DetalleOrdenPagadaExportarPage();
    @Then("^Se valida que el boton (.*) exista$")
    public void verifyButtonIsEnabled(String sButton) {
        Assert.assertTrue(detalleOrdenPagadaExportarPage.verifyButtonIsEnabled(sButton));
    }

}
