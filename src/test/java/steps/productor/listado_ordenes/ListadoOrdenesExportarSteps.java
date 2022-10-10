package steps.productor.listado_ordenes;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.proveedor.listado_ordenes.ListadoOrdenesExportarPage;

public class ListadoOrdenesExportarSteps {
    ListadoOrdenesExportarPage listadoOrdenesExportarPage = new ListadoOrdenesExportarPage();

    @And("^Se elimina el file Tusordenes_Nera.xlsx si existe$")
    public void deleteIfExistFile() {
        listadoOrdenesExportarPage.deleteIfExistFile();
    }

    @Then("^Se descarga el file Tusordenes_Nera.xlsx exitosamente$")
    public void verifyDownloadFile() {
        listadoOrdenesExportarPage.verifyDownloadFile();
    }

}
