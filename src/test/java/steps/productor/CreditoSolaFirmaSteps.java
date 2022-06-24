package steps.productor;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.productor.CreditoSolaFirmaPage;

public class CreditoSolaFirmaSteps {

    CreditoSolaFirmaPage creditoSolaFirmaPage = new CreditoSolaFirmaPage();

    @When("^Selecciona el medio de pago Credito a sola firma$")
    public void seleccionaElMedioDePagoCreditoASolaFirma() {
        Assert.assertTrue(creditoSolaFirmaPage.verifyCreditoSolaFirmaIsdisplayed());
    }

    @And("^Se presiona sobre el (.*)$")
    public void clickOnElement(String elementName) {
        creditoSolaFirmaPage.clickOnElement(elementName);
    }

    @Then("^El sistema redirecciona a la pantalla de Confirmación con todos los detalles de la orden$")
    public void sistemaRedireccionaPantallaConfirmacion() {
        Assert.assertTrue(creditoSolaFirmaPage.verifyPaymentDetailIsdisplayed());
    }

    @And("^Se muestra el (.*)$")
    public void seMuestranLosValoresEnDetallePago(String valueDetail) {
        Assert.assertTrue(creditoSolaFirmaPage.verifyValuesOrderDetail(valueDetail));
    }


    @Then("^Se visualiza el ancho correcto del valor (.*) en la pantalla de Confirmación con todos los detalles de la orden$")
    public void seVisualizaElAnchoCorrectoDelValor(String typeValue) {
        Assert.assertTrue(creditoSolaFirmaPage.validateCorrectWidth(typeValue));
    }
}