package pages.proveedor.generar_orden;

import org.openqa.selenium.WebElement;
import pageobjects.proveedor.generar_orden.GenerarOrdenCompraCFConFirmaPageObject;
import pages.BasePage;

import java.util.List;


public class GenerarOrdenCompraCFConFirmaPage extends BasePage {

    public GenerarOrdenCompraCFConFirmaPage() {
        super();
    }

    public void selecctProvider(String provider) {
        waitVisibility(GenerarOrdenCompraCFConFirmaPageObject.PROVEEDOR_SELECT, "5");
        click(GenerarOrdenCompraCFConFirmaPageObject.PROVEEDOR_SELECT);
        List<WebElement> elementList = driver.findElements(GenerarOrdenCompraCFConFirmaPageObject.PROVEEDOR_CONTAINER);
        for (int i = 0; i < elementList.size(); i++) {
            if (elementList.get(i).getText().contains(provider)) {
                elementList.get(i).click();
                break;
            }
        }
    }
}
