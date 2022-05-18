package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import java.util.logging.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Hooks {
  public static WebDriver driver;
  public static WebDriverWait wait;
  public static Logger log = Logger.getLogger(String.valueOf(Hooks.class));
  Scenario scenario = null;

  @Before
  public void initDriver(Scenario scenario) throws IOException {
    log.info(
        "***********************************************************************************************************");
    log.info("[ Configuration ] - Initializing driver configuration");
    log.info(
        "***********************************************************************************************************");
    //    WebDriverManager.chromedriver().setup();
    //    ChromeOptions options = new ChromeOptions();
    //    options.addArguments("--start-maximized");
    //    options.addArguments("--window-size=1920, 1080");
    //    options.addArguments("--disable-infobars");
    //    driver = new ChromeDriver(options);

    WebDriverManager.firefoxdriver().setup();
    FirefoxOptions options = new FirefoxOptions();
    options.setHeadless(true);
    driver = new FirefoxDriver(options);

    wait = new WebDriverWait(driver, 10);
    this.scenario = scenario;
    log.info(
        "***********************************************************************************************************");
    log.info("[ Scenario ] - " + scenario.getName());
    log.info(
        "***********************************************************************************************************");
  }

  @After
  /** Embed a screenshot in test report if test is marked as failed */
  public void embedScreenshot(Scenario scenario) throws IOException {

    if (scenario.isFailed()) {
      try {
        scenario.write("The scenario failed.");
        scenario.write("Current Page URL is " + driver.getCurrentUrl());
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        String scenarioName = scenario.getName();
        String screenShotPath = String.format("target/screenshots/%s/failed", scenarioName);
        scenario.embed(screenshot, screenShotPath);
      } catch (WebDriverException somePlatformsDontSupportScreenshots) {
        System.err.println(somePlatformsDontSupportScreenshots.getMessage());
      }
    }

    log.info(
        "***********************************************************************************************************");
    log.info("[ Driver Status ] - Clean and close the intance of the driver");
    log.info(
        "***********************************************************************************************************");
    driver.quit();
  }
}
