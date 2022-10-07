package steps.proveedor.generar_orden;

import io.cucumber.java.en.And;
import pages.proveedor.generar_orden.GenerarOrdenCompraCFConFirmaPage;

public class GenerarOrdenCompraCFConFirmaSteps {
    GenerarOrdenCompraCFConFirmaPage generarOrdenCompraCFConFirmaPage = new GenerarOrdenCompraCFConFirmaPage();

    @And("^Se selecciona el proveedor (.*)$")
    public void selecctProvider(String provider) {
        generarOrdenCompraCFConFirmaPage.selecctProvider(provider);
    }
}
