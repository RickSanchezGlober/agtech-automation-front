package steps.productor.listado_ordenes;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.productor.listado_ordenes.DetalleOrdenSolaFirmaPage;
import java.util.List;

public class DetalleOrdenSolaFirmaSteps {
    DetalleOrdenSolaFirmaPage detalleOrdenSolaFirmaPage = new DetalleOrdenSolaFirmaPage();
    @And("^Se verifica que existan ordenes en status (.*) y se verifica que el Detalle de la Orden esté correcta")
    public void verifyQuantity(String sStatus, List<List<String>> t_table) {
        Assert.assertTrue(detalleOrdenSolaFirmaPage.verifyQuantityAndDetails(sStatus, t_table));
    }

    @And("^Obtener datos de endpoint -GET PRODUCTOR- en (.*) con ruta (.*)$")
    public void getDataFromApiGetMethod(String sourceApi, String path) {
        Assert.assertTrue(detalleOrdenSolaFirmaPage.getDataFromApiGetMethod(sourceApi, path));
    }

    @And("^Botón (.*) se visualiza (.*) en detalle de orden")
    public void productorVerifyElement(String buttonName, String status) {
        switch (status) {
            case "Habilitado":
                Assert.assertTrue(detalleOrdenSolaFirmaPage.productorVerifyElement(buttonName));
                break;
            case "Deshabilitado":
                Assert.assertFalse(detalleOrdenSolaFirmaPage.productorVerifyElement(buttonName));
                break;
        }
    }

    @And("^El productor hace click sobre botón: (.*)$")
    public void clickOnButtonProductorDetalle(String buttonName) {
        detalleOrdenSolaFirmaPage.clickOnButtonProductorDetalle(buttonName);
    }
    @When("^Verificar datos de api productor que lista todas las ordenes (.*) con ruta (.*) y parámetros$")
    public void checkDataFromApiServicesGetOrdersWithFilters(String sourceApi, String path, List<List<String>> t_table) {
        detalleOrdenSolaFirmaPage.checkDataFromApiServicesGetOrdersWithFilters(sourceApi, path, t_table);
    }
}
