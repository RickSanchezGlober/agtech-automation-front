package steps.proveedor.listado_ordenes;

import io.cucumber.java.en.And;
import org.testng.Assert;
import pages.proveedor.listado_ordenes.DetalleOrdenGeneradaPage;

import java.util.List;

public class DetalleOrdenGeneradaSteps {
    DetalleOrdenGeneradaPage detalleOrdenGeneradaPage = new DetalleOrdenGeneradaPage();

    @And("^El proveedor hace click en el boton (.*) de la sección (.*)$")
    public void clickEnElBoton(String sButton, String sPantalla) {
        detalleOrdenGeneradaPage.clickOnScreenButton(sButton, sPantalla);
    }

    @And("^Se verifica que existan ordenes en status (.*) y se verifica el Detalle de la Orden Enviada$")
    public void verifyQuantity(String sStatus, List<List<String>> t_table) {
        Assert.assertTrue(detalleOrdenGeneradaPage.verifyQuantityAndDetails(sStatus, t_table));
    }

    @And("^Obtener datos de endpoint -GET- en (.*) con ruta (.*)$")
    public void getDataFromApiGetMethod(String sourceApi, String path) {
        Assert.assertTrue(detalleOrdenGeneradaPage.getDataFromApiGetMethod(sourceApi, path));
    }


}
