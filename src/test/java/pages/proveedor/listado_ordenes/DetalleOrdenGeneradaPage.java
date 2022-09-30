package pages.proveedor.listado_ordenes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageobjects.productor.listado_ordenes.ListadoOrdenesPageObject;
import pageobjects.proveedor.generar_orden.GenerarOrdenCompraCesionForwardPageObject;
import pageobjects.proveedor.listado_ordenes.DetalleOrdenGeneradaPageObject;
import pages.BasePage;
import utils.RestAssuredExtension;

import java.text.DecimalFormat;
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
            case "Descargar detalle":
                element = DetalleOrdenGeneradaPageObject.DESCARGAR_DETALLE_BUTTON;
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
        List<WebElement> elementList = driver.findElements(DetalleOrdenGeneradaPageObject.ORDER_DETAIL_CONTAINER);
        String sElemento = elementList.get(0).getText();

        if (sElemento.contains(element)) {
            //Se obtiene el numero de la orden
            if(sElemento.contains("N° de orden:")){
                sOrdenNumber = sElemento.substring(0, sElemento.indexOf('\n')).replace("N° de orden: ", "");
            }
            //Se consulta si el elemento está desplegado en pantalla
            isVisibleButton = elementList.get(0).isDisplayed();
        }

        return isVisibleButton;
    }

    public Boolean getDataFromApiGetMethod(String sourceApi, String path, String sType, List<List<String>> table) {
        path = path + sOrdenNumber;
        log.info("===> Ejecutando MS en <" + sourceApi + "> y path: <" + path + "> ===");
        getDataFromApiServices(path, sourceApi, table);

        response = RestAssuredExtension.getMethod(sourceApi, path, getAccess_token());
        List<WebElement> elementList = null;

        switch (sType){
            case ("detalle"):
                elementList = driver.findElements(DetalleOrdenGeneradaPageObject.ORDER_DETAIL_CONTAINER);
                break;
            case ("reporte"):
                driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
                elementList = driver.findElements(DetalleOrdenGeneradaPageObject.ORDER_DETAIL_CONTAINER);
                break;
        }

        String sElemento = elementList.get(0).getText();
        Boolean isContained = true;

        //Se validan que estén todos los campos del detalle de la orden
        if (!response.getBody().prettyPrint().equals("")) {
            DecimalFormat formatValue = new DecimalFormat("###,###.##");
            String companyName = response.getBody().jsonPath().get("companyName").toString();
            String orderDesc = response.getBody().jsonPath().get("orderDesc").toString();
            String farmerCuit = response.getBody().jsonPath().get("farmerCuit").toString();
            String farmerMail = response.getBody().jsonPath().get("farmerMail").toString();
            String farmerName = response.getBody().jsonPath().get("farmerName").toString();
            String farmerNumber = response.getBody().jsonPath().get("farmerNumber").toString();
            String installmentCuantity = "0" + response.getBody().jsonPath().get("installmentCuantity").toString();
            String orderTna = response.getBody().jsonPath().get("orderTna").toString();
            String orderAmount = formatValue.format(response.getBody().jsonPath().get("orderAmount"));
            orderAmount = orderAmount.replace(".","&").replace(",", ".").replace("&", ",");
            String orderStatus = response.getBody().jsonPath().get("orderStatus").toString();
            String statusType = "";
            String statusTexto = "";

            //A sola firma = 1
            if(response.getBody().jsonPath().get("paymentLine").toString().equals("1")) {
                switch (orderStatus) {
                    case "Nueva":
                        statusType = "Pendiente";
                        statusTexto = "recibió la orden y está cerrando el negocio.";
                        break;
                    case "En Ejecución":
                        statusType = "Pendiente";
                        statusTexto = "El productor debe confirmar la orden";
                        break;
                    case "Pagada":
                        statusType = "Pagada";
                        break;
                }
            }

            if (!sElemento.contains(companyName) && isContained) { isContained = false; }
            if (!sElemento.contains(farmerMail) && isContained) { isContained = false; }
            if (!sElemento.contains(farmerCuit) && isContained) { isContained = false; }
            if (!sElemento.contains(farmerName) && isContained) { isContained = false; }
            if (!sElemento.contains(farmerNumber) && isContained) { isContained = false; }
            if (!sElemento.contains(orderTna) && isContained) { isContained = false; }
            if (!sElemento.contains(orderDesc) && isContained) { isContained = false; }
            if (!sElemento.contains(orderAmount) && isContained) { isContained = false; }
            if (!sElemento.contains(installmentCuantity) && isContained) { isContained = false; }
            if (!sElemento.contains(statusType) && isContained) { isContained = false; }
            if (!sElemento.contains(statusTexto) && isContained) { isContained = false; }
        }else {
            isContained = false;
        }
        return isContained;
    }

//    {
//        "orderId": "00012708",
//            "companyName": "FORTIN VEGA SOCIEDAD ANONIMA",
//            "farmerCuit": "30568143120",
//            "orderDesc": "SolaFirma-Automation",
//            "farmerName": "dalia cortes",
//            "farmerNumber": "90040230630",
//            "farmerMail": "dalia.cortes@globant.com",
//            "paymentLine": "1",
//            "bankEntity": "Banco Galicia",
//            "orderStatus": "Pagada",
//            "paymentStatus": "Ejecutada Con Éxito",
//            "installmentCuantity": 1,
//            "dueDate": "2023-04-03T23:59:59.999",
//            "orderAmount": 1001.05,
//            "orderTna": 90,
//            "businessType": null,
//            "brokerName": null,
//            "providerName": "MONSANTO ARGENTINA S.R.L."
//    }


//    N° de orden: 00012708
//    Cliente
//    FORTIN VEGA SOCIEDAD ANONIMA
//30568143120
//    Descripción
//    SolaFirma-Automation
//    Información de contacto
//    Nombre y Apellido
//    dalia cortes
//    Correo electrónico
//    dalia.cortes@globant.com
//    Número de celular
//90040230630
//    A sola firma
//            Pagada
//    Banco Galicia
//    Cantidad de cuotas
//01
//    Vencimiento
//03/04/2023
//    TNA del crédito
//90,00 %
//    Monto
//    $ 1.001,05

}
