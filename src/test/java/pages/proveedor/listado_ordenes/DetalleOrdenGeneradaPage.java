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
                log.info(String.format("No se pudo encontrar el elemento '%s' '%s'", (j + 1), "de la tabla"));
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

    public Boolean getDataFromApiGetMethod(String sourceApi, String path) {
        path = path + sOrdenNumber;
        log.info("===> Ejecutando MS en <" + sourceApi + "> y path: <" + path + "> ===");
        getAcessTokenFromApiServices("bff", "auth/login");
        response = RestAssuredExtension.getMethod(sourceApi, path, getAccess_token());
        List<WebElement> elementList = driver.findElements(DetalleOrdenGeneradaPageObject.ORDER_DETAIL_CONTAINER);

        String sElemento = elementList.get(0).getText();
        Boolean isContained = true;


        if (!response.getBody().prettyPrint().equals("")) {

//            N° de orden: 00010767
//            Cliente
//            Fortin Vega CB
//            30568143120
//            Descripción
//            Orden Generada Vía API
//            Información de contacto
//            Correo electrónico
//            yailin.valdivia@globant.com
//            A sola firma
//                    Pendiente
//            Banco Galicia
//            El productor debe confirmar la orden
//            Cantidad de cuotas
//            01
//            Vencimiento
//            15/09/2022
//            TNA del crédito
//            52,00 %
//                    Monto
//            $ 2.000,00


//            {
//                "orderId": "00010767",
//                    "companyName": "Fortin Vega CB",
//                    "farmerCuit": "30568143120",
//                    "orderDesc": "Orden Generada Vía API",
//                    "farmerName": "",
//                    "farmerNumber": "",
//                    "farmerMail": "yailin.valdivia@globant.com",
//                    "paymentLine": "1",
//                    "bankEntity": "Banco Galicia",
//                    "orderStatus": "Nueva",
//                    "paymentStatus": "Pendiente Productor",
//                    "fees": 1,
//                    "dueDate": "2022-12-01T23:59:59.999",
//                    "orderAmount": 2000,
//                    "orderTna": 52,
//                    "businessType": null,
//                    "brokerName": null,
//                    "providerName": "MONSANTO ARGENTINA S.R.L."
//            }

            DecimalFormat formatValue = new DecimalFormat("###,###.##");
            String companyName = response.getBody().jsonPath().get("companyName").toString();
            String farmerMail = response.getBody().jsonPath().get("farmerMail").toString();
            String orderTna = response.getBody().jsonPath().get("orderTna").toString();
            String orderAmount = formatValue.format(response.getBody().jsonPath().get("orderAmount"));
            orderAmount = orderAmount.replace(".","&").replace(",", ".").replace("&", ",");
            String orderDesc = response.getBody().jsonPath().get("orderDesc").toString();
            String paymentStatus = response.getBody().jsonPath().get("paymentStatus").toString();
            String statusDetails = "";
            String statusTextoPendiente = "El productor debe confirmar la orden";

            //VALIDAR CONFIRMAR TEXTO - SI ES PENDIENTE O EN EJECUCION
            switch (paymentStatus) {
                case "Pendiente Productor":
                    statusDetails = "Pendiente";
                    break;
                case "En Ejecución":
                    statusDetails = "Pendiente";
                    break;
            }

            if (!sElemento.contains(companyName) && isContained) { isContained = false; }
            if (!sElemento.contains(farmerMail) && isContained) { isContained = false; }
            if (!sElemento.contains(orderTna) && isContained) { isContained = false; }
            if (!sElemento.contains(orderDesc) && isContained) { isContained = false; }
            if (!sElemento.contains(statusDetails) && isContained) { isContained = false; }
            if (!sElemento.contains(orderAmount) && isContained) { isContained = false; }
        }else {
            isContained = false;
        }
        return isContained;
    }

}
