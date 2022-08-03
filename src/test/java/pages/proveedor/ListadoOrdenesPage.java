package pages.proveedor;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.proveedor.HomeUltimasOperacionesPageObject;
import pageobjects.proveedor.ListadoOrdenesPageObject;
import pages.BasePage;
import utils.RestAssuredExtension;

import java.util.ArrayList;
import java.util.List;


public class ListadoOrdenesPage extends BasePage {
    public ListadoOrdenesPage() {
        super();
    }

    public boolean verifyVisibleElementsOrdersScreen(List<List<String>> t_table) {
        By byElement = null;
        explicitWait(ListadoOrdenesPageObject.BUSCAR_CUIT_NOMBRE_INPUT);
        List<Boolean> resultList = new ArrayList<>();
        for (int i = 0; i < t_table.size(); i++) {
            String elementName = t_table.get(i).get(0);
            log.info(String.format("Buscando el elemento '%s'", elementName));
            switch (elementName) {
                case "el buscador Buscar CUIT o nombre de cliente":
                    byElement = ListadoOrdenesPageObject.BUSCAR_CUIT_NOMBRE_INPUT;
                    resultList.add(getAttribute(byElement, "placeholder").equalsIgnoreCase("Buscar CUIT o nombre de cliente"));
                    break;
                case "el boton Exportar":
                case "el boton Filtrar":
                case "el boton Crear Orden":
                    resultList.add(findElementOrdesScreen(elementName.replace("el boton ", ""), ListadoOrdenesPageObject.BUTTON_CONTAINER_PANTALLA_ORDENES));
                    break;
                case "la columna Creación":
                case "la columna Cliente":
                case "la columna Entidad":
                case "la columna Medio de Pago":
                case "la columna Monto total":
                case "la columna Estado":
                    resultList.add(findElementOrdesScreen(elementName.replace("la columna ", ""), ListadoOrdenesPageObject.ENCABEZADO_TABLA_CONTAINER_PANTALLA_ORDENES));
                    break;
                case "los botones >":
                    //Cambiar esto cuando en Ver todas se vean mas de 4 ordenes
                    List<WebElement> elementList = driver.findElements(ListadoOrdenesPageObject.FLECHA_DERECHA_ICONO_CONTAINER);
                    resultList.add(elementList.size() > 0);
                    break;
            }
        }
        Boolean finalResult = true;
        for (int j = 0; j < resultList.size(); j++) {
            if (!resultList.get(j)) {
                finalResult = resultList.get(j);
                log.info(String.format("No se pudo encontrar el elemento '%s' '%s'", (j + 1), "de la tabla"));
                break;
            }
        }
        return finalResult;
    }

    public boolean findElementOrdesScreen(String element, By byContainer) {
        boolean isVisibleButton = false;
        List<WebElement> elementList = driver.findElements(byContainer);
        for (int j = 0; j < elementList.size(); j++) {
            if (elementList.get(j).getText().equalsIgnoreCase(element)) {
                isVisibleButton = elementList.get(j).isDisplayed();
                break;
            }
        }
        return isVisibleButton;
    }

    public void getDataFromApiServicesAllOrders(String sourceApi, String path, List<List<String>> t_table) {
        log.info(String.format("Consumiendo API: '%s' '%s'", sourceApi, path));
        getAcessTokenFromApiServices("bff", "auth/login");
        response = RestAssuredExtension.getMethodWithParams(sourceApi, path, t_table, getAccess_token());
        explicitWait(HomeUltimasOperacionesPageObject.ORDENES_CONTAINER);

    }
}
