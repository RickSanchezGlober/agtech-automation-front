package pages;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
//import java.util.concurrent.atomic.AtomicInteger;

import config.WebPropertiesConfig;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;
import io.qameta.allure.Attachment;
import pageobjects.productor.autenticacion.LoginPageObject;
import pageobjects.proveedor.autenticacion.HomePageObject;
import steps.Hook;
import utils.RestAssuredExtension;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public String access_token;
    public static DataTable data;
    public static Map<String, String> scenarioData = new HashMap<>();
    protected Logger log;
    public static WebPropertiesConfig webPropertiesConfig = new WebPropertiesConfig();
    public static ResponseOptions<Response> response = null;

    public BasePage() {
        driver = Hook.driver;
        wait = Hook.wait;
        log = Hook.log;
        PageFactory.initElements(driver, this);
        access_token = "";
    }

    public void navigateTo(String rol) {
        String url = "";
        By element = null;
        switch (rol) {
            case "productor":
                url = webPropertiesConfig.getBaseUriProductor();
                element = pageobjects.productor.autenticacion.LoginPageObject.INGRESAR;
                break;
            case "proveedor":
                url = webPropertiesConfig.getBaseUriProveedor();
                element = pageobjects.proveedor.autenticacion.LoginPageObject.INGRESAR;
                break;
        }
        driver.get(url);
        explicitWait(element);

    }

    public void navigateToError(String rol, String proceso) {
        String url = "";

        switch (rol) {
            case "productor":

                if (proceso.equals("login")) {
                    url = webPropertiesConfig.getBaseLoginErrorUriProductor();
                    break;
                }
                if (proceso.equals("signup")) {
                    url = webPropertiesConfig.getBaseSignupErrorUriProductor();
                    break;
                }
            case "proveedor":
                if (proceso.equals("login")) {
                    url = webPropertiesConfig.getBaseLoginErrorUriProveedor();
                    break;
                }
                if (proceso.equals("signup")) {
                    url = webPropertiesConfig.getBaseSignupErrorUriProveedor();
                    break;
                }
        }
        driver.get(url);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void setText(By locator, String text) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
        tab(locator);
    }

    public void tab(By locator) {
        driver.findElement(locator).sendKeys(Keys.TAB);
    }

    public void sendBackSpace(By locator) {
        driver.findElement(locator).sendKeys(Keys.BACK_SPACE);
    }

    public String getText(By locator) {
        String displayedText = driver.findElement(locator).getText();
        if (displayedText.isEmpty()) {
            return driver.findElement(locator).getAttribute("value");
        } else {
            return displayedText;
        }
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void goBack() {
        driver.navigate().back();
    }

    /**
     * Performs a drag-n-drop operation on a given element by a given x,y offset.
     *
     * @param locator The element to be interacted with
     * @param x       x-coordinate
     * @param y       Y-coordinate
     */
    public void dragAndDropByOffset(By locator, int x, int y) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(locator);
        actions.dragAndDropBy(element, x, y).perform();
    }

    public void dismissPopup() {
        driver.switchTo().alert().dismiss();
    }

    public void acceptPopup() {
        driver.switchTo().alert().accept();
    }

    public void setAlertText(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    public void waitForElementText(By locator, String text) {
        // This is an explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBe(locator, text));

        // This is a FluentWait. It does the same as the above wait, but it is more
        // customizable
        // Wait<WebDriver> wait = new FluentWait<>(driver)
        // .withTimeout(Duration.ofSeconds(3))
        // .pollingEvery(Duration.ofMillis(250))
        // .ignoring(NoSuchElementException.class);
        // wait.until(ExpectedConditions.textToBe(locator, text));
    }

    public void hoverOverElement(By locator) {
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void scrollElementIntoView(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * Scrolls the document by the specified number of pixels.
     *
     * @param x How many pixels to scroll by, along the x-axis (horizontal).
     * @param y How many pixels to scroll by, along the y-axis (vertical).
     */
    public void scrollPage(int x, int y) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(" + x + "," + y + ");");
    }

    /**
     * Takes screenshot of whole page and uses the current date/time as the file
     * name
     */
    public void takeScreenshot() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMYYYY_HHmmss");
        LocalDateTime dateTime = LocalDateTime.now();
        takeScreenshot(dateTime.format(formatter));
    }

    /**
     * Takes screenshot of whole page and allows you to name the screenshot
     *
     * @param screenshotName The screenshot file name
     */
    public void takeScreenshot(String screenshotName) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("./failed_tests/" + screenshotName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes screenshot of single WebElement
     */
    public void takeElementScreenshot(By locator) {
        WebElement element = driver.findElement(locator);
        File file = element.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("./screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Attachment(value = "screenshot_error", type = "image/png")
    public byte[] getByteScreenshot() throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        byte[] fileContent = FileUtils.readFileToByteArray(src);

        return fileContent;
    }

    /**
     * @param frame The index of the frame to switch to (first frame has index 0)
     */
    public void switchFrames(int frame) {
        driver.switchTo().frame(frame);
    }

    public void switchToDefaultFrame() {
        driver.switchTo().defaultContent();
    }

    public void setCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        driver.manage().addCookie(cookie);
    }

    public Cookie getCookie(String name) {
        return driver.manage().getCookieNamed(name);
    }

    public void getDataFromApiServices(String path, String body, String sourceApi, List<List<String>> t_table) {
        getAcessTokenFromApiServices("bff", "provider/auth/login");
        response = RestAssuredExtension.postMethod(sourceApi, path, body, getAccess_token());

        DataTable data = createDataTable(t_table);
        if (data != null) {
            AtomicInteger i = new AtomicInteger(1);
            data.cells()
                    .forEach(
                            value -> {

                                List<String> rField = Collections.singletonList(value.get(0));
                                List<String> rValue = Collections.singletonList(value.get(1));
                                String VALUES = null;
                                try {
                                    String KEY = rField.get(0);
                                    VALUES = response
                                            .getBody()
                                            .jsonPath()
                                            .get(rValue.get(0))
                                            .toString();
                                    // SAVE
                                    saveInScenarioContext(KEY, VALUES);
                                } catch (NullPointerException e) {
                                    log.info(String.format("Path specified doesn't exist: %s", VALUES));
                                }
                            });
        }
    }

    public void getDataFromApiServices(String path, String sourceApi, List<List<String>> t_table) {

        getAcessTokenFromApiServices(sourceApi, "provider/auth/login");
        response = RestAssuredExtension.getMethod(sourceApi, path, getAccess_token());
        DataTable data = createDataTable(t_table);
        if (data != null) {
            AtomicInteger i = new AtomicInteger(1);
            data.cells()
                    .forEach(
                            value -> {
                                List<String> rField = Collections.singletonList(value.get(0));
                                String KEY = rField.get(0);
                                // SAVE
                                try {
                                    saveInScenarioContext(KEY, response.getBody().jsonPath().get(KEY).toString());
                                } catch (NullPointerException e) {
                                }
                            });
        }
    }

    public void getAcessTokenFromApiServices(String sourceApi, String path) {
        log.info("Consumiendo API " + sourceApi + " y path: " + path);
        String bodySource = "";
        if (path.contains("farmer")) {
            bodySource = "bff_productor_login.txt";
        } else if (path.contains("producer") || path.contains("provider")) {
            bodySource = "bff_proveedor_login.txt";
        }
        RestAssuredExtension.response = RestAssuredExtension.postMethodLogin(sourceApi, path, bodySource);
        setAccess_token(RestAssuredExtension.response.getBody().jsonPath().getString("id_token"));

    }

    public DataTable createDataTable(List<List<String>> table) {
        data = DataTable.create(table);
        log.info("===> DataTable: " + String.valueOf(data) + " ===");
        return data;
    }

    public void saveInScenarioContext(String key, String text) {
        try {
            if (!scenarioData.containsKey(key)) {
                scenarioData.put(key, text);
                log.info(String.format("Save as Scenario Context key: %s with value: %s ", key, text));
            } else {
                scenarioData.replace(key, text);
                log.info(String.format("Update Scenario Context key: %s with value: %s ", key, text));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getScenarioContextVariables(String key) {
        try {
            return scenarioData.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    public void write(By locator, String textToWrite) {
        Find(locator).clear();
        Find(locator).sendKeys(textToWrite);
    }

    public void clear(By locator) {
        Find(locator).clear();
    }

    protected WebElement Find(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void explicitWait(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public boolean waitVisibility(By by, String wt) {
        int time;
        if (wt != null | wt != "") {
            time = Integer.valueOf(wt);
        } else {
            time = 10;
        }
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            log.info("===> Element: " + by + " is not present ===");
            return false;
        }
    }

    public boolean verifyVisibleText(By locator, String textToCompare) {
        explicitWait(locator);
        log.info(textToCompare);
        log.info(Find(locator).getText());
        return Find(locator).getText().equals(textToCompare);
    }

    public boolean verifyMessageIsdisplayed(String action, String message, String rol) {
        switch (rol) {
            case "proveedor":
                switch (action) {
                    case "logueo":
                        return verifyVisibleText(pageobjects.proveedor.autenticacion.LoginPageObject.MENSAJE_BIENVENIDA_TEXT, message);

                    case "deslogueo":
                        return verifyVisibleText(pageobjects.proveedor.autenticacion.LoginPageObject.MENSAJE_INICIO_SESION_TEXT, message);

                    case "login":
                        message = "Inicio de sesión";
                        return verifyVisibleText(pageobjects.proveedor.autenticacion.LoginPageObject.MENSAJE_INICIO_SESION_TEXT, message);

                    case "signup":
                        message = "Creá tu cuenta";
                        return verifyVisibleText(pageobjects.proveedor.autenticacion.LoginPageObject.MENSAJE_REGISTRO_TEXT, message);

                    case "cambio":

                        return verifyVisibleText(HomePageObject.MENSAJE_CAMBIO_CONTRASENA, message);
                }
                break;
            case "productor":
                switch (action) {
                    case "logueo":
                        return verifyVisibleText(pageobjects.productor.autenticacion.LoginPageObject.MENSAJE_BIENVENIDA_TEXT, message);

                    case "deslogueo":
                        return verifyVisibleText(pageobjects.productor.autenticacion.LoginPageObject.MENSAJE_INICIO_SESION_TEXT, message);

                    case "login":
                        message = "Inicio de sesión";
                        return verifyVisibleText(pageobjects.productor.autenticacion.LoginPageObject.MENSAJE_INICIO_SESION_TEXT, message);

                    case "signup":
                        message = "Creá tu cuenta";
                        return verifyVisibleText(pageobjects.productor.autenticacion.LoginPageObject.MENSAJE_REGISTRO_TEXT, message);

                    case "cambio":

                        return verifyVisibleText(HomePageObject.MENSAJE_CAMBIO_CONTRASENA, message);
                }
                break;
        }

        return false;
    }

    public boolean isDisplayed(By locator) {
        return Find(locator).isDisplayed();
    }

    public boolean isEnabled(By locator) {
        return Find(locator).isEnabled();
    }

    public boolean isAttributePresent(By locator, String attribute) {
        Boolean result = false;
        try {
            String value = getAttribute(locator, attribute);
            if (value != null) {
                result = true;
            }
        } catch (Exception e) {
        }

        return result;
    }

    public String getCuitWithFormat(String cuit) {
        return cuit.substring(0, 2) + "-" + cuit.substring(2, 10) + "-" + cuit.substring(10, 11);
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAttribute(By locator, String attribute) {
        return Find(locator).getAttribute(attribute);
    }

    public void sleep(int halfSecond) {
        try {
            Thread.sleep(500 * halfSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String parseFromDoubleToString(String doubleNumber, int numbersAfterDot) {
        BigDecimal bd = new BigDecimal(doubleNumber).setScale(numbersAfterDot, RoundingMode.HALF_UP);
        return String.valueOf(bd).replace(".", ",");
    }

    public String getDateStringFormat(String stringDate, String format) {
        LocalDateTime ldt = LocalDateTime.parse(stringDate);
        return ldt.format(DateTimeFormatter.ofPattern(format));
    }
}