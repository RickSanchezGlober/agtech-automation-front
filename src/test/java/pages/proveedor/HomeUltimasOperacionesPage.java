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
        By element = null;
        switch (buttonName) {
            case "Ver todas":
                element = HomeUltimasOperacionesPageObject.VER_TODAS_BUTTON;
                break;
        }
        waitVisibility(element, "30");
        click(element);
    }


    public void clicOnButtonByNameOrders(String buttonName) {
        By element = null;
        switch (buttonName) {
            case "Crear orden":
                element = HomeUltimasOperacionesPageObject.CREAR_ORDEN_BUTTON;
                break;
        }
        waitVisibility(element, "30");
        click(element);
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
}
