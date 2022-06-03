package steps;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import pages.GooglePage;

public class GoogleSteps {
    GooglePage googlePage = new GooglePage();

    @Given("^I go to google$")
    public void iGoToGoogle() {
        googlePage.navigateTo();
        String actualPageTitle = googlePage.getPageTitle();
        Assert.assertEquals(actualPageTitle, "AgTech");
    }
}
