package runners;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = {
        "steps" }, monochrome = true, tags = "@testing and not @ignore", plugin = { "pretty" })
public class RunnerTest extends AbstractTestNGCucumberTests {
    public final static ThreadLocal<String> BROWSER = new ThreadLocal<>();

    @BeforeTest
    @Parameters("browser")
    public void defineBrowser(String browser) {
        RunnerTest.BROWSER.set(browser);
    }
}