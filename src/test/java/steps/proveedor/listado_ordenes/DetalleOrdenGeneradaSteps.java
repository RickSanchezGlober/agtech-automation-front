package steps.proveedor.listado_ordenes;

import io.cucumber.java.en.And;
import pages.proveedor.listado_ordenes.DetalleOrdenGeneradaPage;

public class DetalleOrdenGeneradaSteps {
    DetalleOrdenGeneradaPage detalleOrdenGeneradaPage = new DetalleOrdenGeneradaPage();
    @And("El proveedor hace click en el boton Cerrar del modal Filtros de ordenes")
    public void ClickEnElBotonCerrar() {
        detalleOrdenGeneradaPage.clickOnCloseButton();
    }
}
