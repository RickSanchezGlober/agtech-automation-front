package steps.proveedor.listado_ordenes;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.proveedor.listado_ordenes.ListadoOrdenesExportarPage;

public class ListadoOrdenesExportarSteps {
    ListadoOrdenesExportarPage listadoOrdenesExportarPage = new ListadoOrdenesExportarPage();

    @And("^Se elimina el archivo Tusordenes_Nera.xlsx si existe$")
    public void deleteIfExistFile() {
        listadoOrdenesExportarPage.deleteIfExistFile();
    }

    @Then("^Se descarga Tusordenes_Nera.xlsx exitosamente$")
    public void verifyDownloadFile() {
        listadoOrdenesExportarPage.verifyDownloadFile();
    }

}
