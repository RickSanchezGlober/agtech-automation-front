package steps.proveedor.listado_ordenes;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.proveedor.listado_ordenes.ListadoOrdenesExportarPage;

import java.util.List;

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

    @And("^Consumir api que filtra las ordenes (.*) con ruta (.*) y parámetros$")
    public void validateDataFromOrderWithFilters(String sourceApi, String path, List<List<String>> t_table) {
        listadoOrdenesExportarPage.consumeApiFilterOrders(sourceApi, path, t_table);
    }
}
