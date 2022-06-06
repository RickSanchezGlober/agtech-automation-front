package steps;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.ProductorListadoOrdenesPage;

public class ProductorListadoOrdenesSteps {
    public static ProductorListadoOrdenesPage productorListadoOrdenesPage = new ProductorListadoOrdenesPage();

    @Then("^Se visualiza (.*) en la pantalla Empty State$")
    public void seVisualizaElementEnLaPantallaDeEmptyState(String element) {
        Assert.assertTrue(productorListadoOrdenesPage.verifyElementEmptyStateScreen(element));
    }
}
