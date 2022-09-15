package pages.proveedor.listado_ordenes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.proveedor.listado_ordenes.DetalleOrdenGeneradaPageObject;
import pages.BasePage;

import java.util.ArrayList;
import java.util.List;

public class DetalleOrdenGeneradaPage extends BasePage {
    public DetalleOrdenGeneradaPage() {
        super();
    }
    public static String sOrdenNumber;
    public void clickOnScreenButton(String sButton, String sPantalla) {
        By element = null;
        switch (sButton) {
            case "Cerrar":
                element = DetalleOrdenGeneradaPageObject.CERRAR_MODAL_FILTRAR_BUTTON;
                break;
            case "Aplicar Filtros":
                element = DetalleOrdenGeneradaPageObject.APLICAR_FILTROS_BUTTON;
                break;
            case "Filtrar":
                element = DetalleOrdenGeneradaPageObject.FILTRAR_BUTTON;
                break;
            case "Ver todas":
                element = DetalleOrdenGeneradaPageObject.VER_TODAS_BUTTON;
                break;
        }
        log.info(String.format("====>  Click en el botón: '%s', de la sección: '%s' ====", sButton, sPantalla));
        waitVisibility(element, "10");
        click(element);
    }

    public boolean verifyQuantityAndDetails(String sStatus, List<List<String>> t_table) {
        Boolean hayElementos = false;
        Boolean orderDetailIsOk = false;
        explicitWait(DetalleOrdenGeneradaPageObject.ORDER_TABLE_CONTAINER);
        List<WebElement> elementList = driver.findElements(DetalleOrdenGeneradaPageObject.ORDER_TABLE_CONTAINER);
        if(elementList.size() > 0){
            hayElementos = true;
            //Click en la primera fila de la tabla
            click(DetalleOrdenGeneradaPageObject.ORDER_DETAIL_ROW);
            waitVisibility(DetalleOrdenGeneradaPageObject.ORDER_DETAIL_CONTAINER, "5");
            //Se verifica el detalle de la orden
            orderDetailIsOk = validateFields(t_table);
        }
        log.info(String.format("====>  Verificando que existan órdenes en Status: <%s> ---> <%s>  ====", sStatus, hayElementos));
        return (orderDetailIsOk);
    }

    private boolean validateFields(List<List<String>> t_table){
        List<Boolean> resultList = new ArrayList<>();
        String elementName = "";
        Boolean finalResult = true;

        //Se verifica que cada label y su valor se encuentre en el contenedor de detalle y se insertan los valores en list resultList
        for (int i = 0; i < t_table.size(); i++) {
            elementName = t_table.get(i).get(0);
            log.info(String.format("Buscando el elemento '%s'", elementName));
            //Se busca en el cada item del contenedor
            resultList.add(searchOnContainer(elementName, DetalleOrdenGeneradaPageObject.ORDER_DETAIL_CONTAINER));
        }

        //Se verifica si hubo algún valor que no se encontrara en el contenedor de detalle
        for (int j = 0; j < resultList.size(); j++) {
            if (!resultList.get(j)) {
                finalResult = resultList.get(j);
                log.info(String.format("No se pudo encontrar el elemento '%s' '%s'", (j + 1), "de la tabla"));
                break;
            }
        }
        return finalResult;
    }

    public boolean searchOnContainer(String element, By byContainer) {
        boolean isVisibleButton = false;
        List<WebElement> elementList = driver.findElements(byContainer);
        for (int j = 0; j < elementList.size(); j++) {
            //Se obtiene el numero de la orden
            if(elementList.get(j).getText().contains("N° de orden:")){
                sOrdenNumber = elementList.get(j).getText().substring(0, elementList.get(j).getText().indexOf('\n')).replace("N° de orden: ", "");
            }

            if (elementList.get(j).getText().contains(element)) {
                isVisibleButton = elementList.get(j).isDisplayed();
                break;
            }
        }
        return isVisibleButton;
    }

}
