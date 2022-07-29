package steps.proveedor;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.proveedor.GenerarOrdenCompraSolaFirmaPage;
import pages.proveedor.HomeUltimasOperacionesPage;

import java.util.List;

public class HomeUltimasOperacionesSteps {
    HomeUltimasOperacionesPage homeUltimasOperacionesPage = new HomeUltimasOperacionesPage();

    @And("^El proveedor hace click en el boton (.*) del home$")
    public void clicOnButtonByNameHome(String buttonName) {
        homeUltimasOperacionesPage.clicOnButtonByNameHome(buttonName);
    }

    @And("^El proveedor hace click en el boton (.*) de la pantalla ordenes$")
    public void clicOnButtonByNameOrders(String buttonName) {
        homeUltimasOperacionesPage.clicOnButtonByNameOrders(buttonName);
    }

    @Then("^Se muestran los elementos de la pantalla Nueva Orden$")
    public void verifyErrorPageDisplayed() {
        Assert.assertTrue(homeUltimasOperacionesPage.verifyScreenNewOrder());
    }
}
