package pages.proveedor.bff_api_proveedor;


import org.testng.Assert;
import pages.BasePage;
import utils.RestAssuredExtension;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class BffOrdersFilterPage extends BasePage {
    public static int pos;
    public static String filterStatus;
    public static String filterCount;

    public BffOrdersFilterPage() {
        super();
        pos = 0;
    }


    public void getTokenFromLoginApi(String source, String path, String body) {
        log.info(String.format("Consumiendo API: '%s' '%s' con body '%s'", source, path, body));
        getAcessTokenFromApiServices(source, path);
    }

    public boolean verifyServiceStatusCodeProveedor(String statusCode) {
        System.out.println("=== The response proveedor status is  === >  " + RestAssuredExtension.response.statusCode());
        return RestAssuredExtension.response.statusCode() == Integer.parseInt(statusCode);
    }

    public void getOrdersWhitFilter(String sourceApi, String path, List<List<String>> t_table) {
        log.info(String.format("Consumiendo API: '%s' '%s' y filtros", sourceApi, path));
        filterCount = t_table.get(1).get(1);
        filterStatus = t_table.get(2).get(1).replace("status:", "");
        getAcessTokenFromApiServices(sourceApi, "provider/auth/login");
        RestAssuredExtension.getMethodWithParamsHeaderOrdersCounter(sourceApi, path, t_table, getAccess_token());
    }

    public void verifyResponseApiOrders(String serviceName) {
        switch (serviceName) {
            case "orders/filter":
                validateSchemaPagination(RestAssuredExtension.response.getBody().jsonPath().get("_pagination"));
                try {
                    ArrayList list = new ArrayList<>(RestAssuredExtension.response.getBody().jsonPath().get("result.status"));
                    list.stream().forEach(dataEntry -> getStatus(dataEntry));

                } catch (NullPointerException e) {
                    e.printStackTrace();
                    log.info("Path is invalid");
                }
                pos = 0;
                try {
                    ArrayList list = new ArrayList<>(RestAssuredExtension.response.getBody().jsonPath().get("result.provider"));
                    list.stream().forEach(dataEntry -> getProvider(dataEntry));

                } catch (NullPointerException e) {
                    e.printStackTrace();
                    log.info("Path is invalid");
                }
                break;

        }
    }

    private void validateSchemaPagination(Object pagination) {
        log.info(String.format("Validando que 'pagination.record_count' sea menor o igual a '%s'", filterCount));
        Assert.assertTrue((Integer) (((LinkedHashMap) pagination).get("record_count")) <= (Integer.parseInt(filterCount)));

        log.info("Validando que 'pagination.total_count' sea Numerico");
        Assert.assertTrue(((LinkedHashMap) pagination).get("total_count") instanceof Integer);
    }

    private void getStatus(Object dataEntry) {
        //Verificando estado
        log.info(String.format("Validando que 'result.status' sea '%s' en la orden '%s'", filterStatus, (pos + 1)));
        Assert.assertTrue(filterStatus.contains((CharSequence) dataEntry));
        pos++;
    }

    private void getProvider(Object dataEntry) {
        //Verificando provider_id=2
        log.info(String.format("Validando que 'result.provider._id' sea 2 en la orden '%s'", (pos + 1)));
        Assert.assertTrue(((LinkedHashMap) dataEntry).get("_id").equals("2"));
        pos++;
    }
}
