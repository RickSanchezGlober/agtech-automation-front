package pages.productor.listado_ordenes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.productor.listado_ordenes.DetalleOrdenSolaFirmaPageObject;
import pages.BasePage;
import utils.RestAssuredExtension;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DetalleOrdenSolaFirmaPage extends BasePage {
    public DetalleOrdenSolaFirmaPage()  {
        super();
    }
    public static String sOrdenNumber;

    public boolean verifyQuantityAndDetails(String sStatus, List<List<String>> t_table) {
        Boolean hayElementos = false;
        Boolean orderDetailIsOk = false;
        explicitWait(DetalleOrdenSolaFirmaPageObject.PRODUCTOR_ORDER_TABLE_CONTAINER);
        List<WebElement> elementList = driver.findElements(DetalleOrdenSolaFirmaPageObject.PRODUCTOR_ORDER_TABLE_CONTAINER);
        if(elementList.size() > 0){
            hayElementos = true;
            //Click en la primera fila de la tabla
            click(DetalleOrdenSolaFirmaPageObject.PRODUCTOR_ORDER_DETAIL_ROW);
            waitVisibility(DetalleOrdenSolaFirmaPageObject.PRODUCTOR_ORDER_DETAIL_CONTAINER, "5");
            //Click en Desplegable Detalles el crédito
            click(DetalleOrdenSolaFirmaPageObject.PRODUCTOR_ORDER_COLLAPSIBLE_ARROW);

            //Se verifica el detalle de la orden
            orderDetailIsOk = validateFields(t_table);
        }
        log.info(String.format("====>  Verificando que existan órdenes en Status: <%s> ---> <%s>  ====", sStatus, hayElementos));
        return (orderDetailIsOk);
    }

    private boolean validateFields(List<List<String>> t_table){
        List<Boolean> resultList = new ArrayList<>();
        String elementToSearch = "";
        Boolean finalResult = true;

        //Se verifica que cada label y su valor se encuentre en el contenedor de detalle y se insertan los valores en list resultList
        for (int i = 0; i < t_table.size(); i++) {
            elementToSearch = t_table.get(i).get(0);
            log.info(String.format("Buscando el elemento '%s'", elementToSearch));
            //Se busca el elemento en el contenedor
            resultList.add(searchOnContainer(elementToSearch));
        }

        //Se verifica si hubo algún valor que no se encontrara en el contenedor de detalle
        for (int j = 0; j < resultList.size(); j++) {
            if (!resultList.get(j)) {
                finalResult = resultList.get(j);
                log.info(String.format("No se pudo encontrar el elemento '%s' de la tabla de datos", (j + 1)));
                break;
            }
        }
        return finalResult;
    }

    public boolean searchOnContainer(String element) {
        boolean isVisibleButton = false;
        List<WebElement> elementList = driver.findElements(DetalleOrdenSolaFirmaPageObject.PRODUCTOR_ORDER_DETAIL_CONTAINER);
        String sElemento = elementList.get(0).getText();

        if (sElemento.contains(element)) {
            //Se obtiene el numero de la orden
            if(sElemento.contains("Orden:")){
                sOrdenNumber = sElemento.substring(0, sElemento.indexOf('\n', 20)).replace("Detalle de la orden\nOrden: ", "");
            }
            //Se consulta si el elemento está desplegado en pantalla
            isVisibleButton = elementList.get(0).isDisplayed();
        }

        return isVisibleButton;
    }

    public Boolean getDataFromApiGetMethod(String sourceApi, String path) {
        path = path + sOrdenNumber;
        log.info("===> Ejecutando MS en <" + sourceApi + "> y path: <" + path + "> ===");

        //Se obtiene el token y se ejecuta getMethod del bff
        getAcessTokenFromApiServices(sourceApi, "provider/auth/login");
        response = RestAssuredExtension.getMethod(sourceApi, path, getAccess_token());

        List<WebElement> elementList = driver.findElements(DetalleOrdenSolaFirmaPageObject.PRODUCTOR_ORDER_DETAIL_CONTAINER);

        String sElemento = elementList.get(0).getText();
        Boolean isContained = true;

        //Se validan que estén todos los campos del detalle de la orden y que no contenga el response de error
        if (!response.getBody().prettyPrint().equals("") && !response.getBody().prettyPrint().contains("detail_message")) {
            DecimalFormat formatValue = new DecimalFormat("###,###.##");
            String provider_name = response.getBody().jsonPath().get("provider_name").toString();
            String orderDesc = response.getBody().jsonPath().get("title").toString();
            String orderTna = response.getBody().jsonPath().get("tna_farmer").toString();
            String orderAmount = formatValue.format(response.getBody().jsonPath().get("amount"));
            orderAmount = orderAmount.replace(".","&").replace(",", ".").replace("&", ",");
            String orderStatus = response.getBody().jsonPath().get("order_status").toString();
            String paymentStatus = response.getBody().jsonPath().get("payment_status").toString();
            String financial_line_name = response.getBody().jsonPath().get("financial_line_name").toString();
            String financial_entity = response.getBody().jsonPath().get("financial_entity").toString();

            String date = response.getBody().jsonPath().get("due_date").toString();
            String dueDate = date.substring(8,10)+"/"+date.substring(5,7)+"/"+date.substring(0,4);
            String interest = formatValue.format(response.getBody().jsonPath().get("interest"));
            interest = interest.replace(".","&").replace(",", ".").replace("&", ",");
            String interest_iva = formatValue.format(response.getBody().jsonPath().get("interest_iva"));
            interest_iva = interest_iva.replace(".","&").replace(",", ".").replace("&", ",");
            String stamp_taxes = formatValue.format(response.getBody().jsonPath().get("stamp_taxes"));
            stamp_taxes = stamp_taxes.replace(".","&").replace(",", ".").replace("&", ",");
            String total_amount = formatValue.format(response.getBody().jsonPath().get("total_amount"));
            total_amount = total_amount.replace(".","&").replace(",", ".").replace("&", ",");
            String capital_cost = formatValue.format(response.getBody().jsonPath().get("capital_cost"));
            capital_cost = capital_cost.replace(".","&").replace(",", ".").replace("&", ",");
            String statusText = "check status error";
            if(orderStatus.equals("Nueva") && paymentStatus.equals("Pendiente Productor")){
                statusText = "Autorizá el crédito";
            }
            if (!sElemento.contains(provider_name) && isContained) { isContained = false; }
            if (!sElemento.contains(orderTna) && isContained) { isContained = false; }
            if (!sElemento.contains(orderDesc) && isContained) { isContained = false; }
            if (!sElemento.contains(orderAmount) && isContained) { isContained = false; }
            if (!sElemento.contains(financial_line_name) && isContained) { isContained = false; }
            if (!sElemento.contains(financial_entity) && isContained) { isContained = false; }
            if (!sElemento.contains(interest) && isContained) { isContained = false; }
            if (!sElemento.contains(interest_iva) && isContained) { isContained = false; }
            if (!sElemento.contains(stamp_taxes) && isContained) { isContained = false; }
            if (!sElemento.contains(total_amount) && isContained) { isContained = false; }
            if (!sElemento.contains(capital_cost) && isContained) { isContained = false; }
            if (!sElemento.contains(statusText) && isContained) { isContained = false; }
            if (!sElemento.contains(dueDate) && isContained) { isContained = false; }
        }else {
            isContained = false;
        }
        return isContained;
    }
    public boolean productorVerifyElement(String buttonName) {
        By element = null;
        boolean isEnabled = false;
        switch (buttonName) {
            case "Continuar":
            case "Confirmar Pago":
                element = DetalleOrdenSolaFirmaPageObject.CONTINUAR_BUTTON;
                break;
            case "Filtrar":
                element = DetalleOrdenSolaFirmaPageObject.FILTRAR_BUTTON;
                break;
            case "Tooltip":
                element = DetalleOrdenSolaFirmaPageObject.COSTO_CAPITAL_TOOLTIP;
                break;
        }
        waitVisibility(element, "10");
        isEnabled = isEnabled(element);

        //Si es Tooltip --> Se verifica también que contenga el texto en el atributo
        if(buttonName.equals("Tooltip")){
            if(!getAttribute(element, "data-tip").equalsIgnoreCase("Es la suma de los costos (incluyendo impuestos) dividido el capital del préstamo.")){
                isEnabled = false;
            }
        }
        return isEnabled;
    }

    public void clickOnButtonProductorDetalle(String buttonName) {
        By element = null;
        switch (buttonName) {
            case "Volver-Detalle de la Orden":
            case "Volver-Ordenes":
                element = DetalleOrdenSolaFirmaPageObject.VOLVER_BUTTON;
                break;
            case "Continuar":
                element = DetalleOrdenSolaFirmaPageObject.CONTINUAR_BUTTON;
                break;
        }
        waitVisibility(element, "5");
        click(element);
    }
}
