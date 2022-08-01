package pages.proveedor;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pageobjects.proveedor.GenerarOrdenCompraSolaFirmaContactoPageObject;
import pageobjects.proveedor.GenerarOrdenCompraSolaFirmaPageObject;
import pageobjects.proveedor.HomeUltimasOperacionesPageObject;
import pages.BasePage;
import utils.DataGenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class HomeUltimasOperacionesPage extends BasePage {


    public HomeUltimasOperacionesPage() {
        super();
    }

    public void clicOnButtonByNameHome(String buttonName) {
        explicitWait(HomeUltimasOperacionesPageObject.ULTIMAS_REALIZADAS_TITTLE);
        List<WebElement> elementList = driver.findElements(HomeUltimasOperacionesPageObject.HOME_BUTTON_CONTAINER);
        for (int i = 0; i < elementList.size(); i++) {
            if (elementList.get(i).getText().contains(buttonName)) {
                elementList.get(i).click();
                break;
            }
        }
    }


    public void clicOnButtonByNameOrders(String buttonName) {
        explicitWait(HomeUltimasOperacionesPageObject.ORDENES_TITTLE);
        List<WebElement> elementList = driver.findElements(HomeUltimasOperacionesPageObject.ORDERS_BUTTON_CONTAINER);
        for (int i = 0; i < elementList.size(); i++) {
            if (elementList.get(i).getText().contains(buttonName)) {
                elementList.get(i).click();
                break;
            }
        }
    }

    public boolean verifyScreenNewOrder() {
        By byElement = HomeUltimasOperacionesPageObject.INGRESA_CUIT_LABEL;
        waitVisibility(byElement, "10");
        log.info("Verificando que se visualise la pantalla de nueva orden");
        return verifyVisibleText(byElement, "Ingresá el CUIT") &&
                verifyVisibleText(HomeUltimasOperacionesPageObject.ESCRIBI_11_NUMEROS_TEXT, "Escribí 11 números") &&
                !isEnabled(HomeUltimasOperacionesPageObject.BUSCAR_BUTTON) &&
                isDisplayed(HomeUltimasOperacionesPageObject.LUPA_ICONO);
    }

    public boolean verifyMaxNumberOrders(String orderQuantity) {
        explicitWait(HomeUltimasOperacionesPageObject.ORDENES_CONTAINER);
        List<WebElement> elementList = driver.findElements(HomeUltimasOperacionesPageObject.ORDENES_CONTAINER);
        return (elementList.size() == Integer.parseInt(orderQuantity));
    }
}
