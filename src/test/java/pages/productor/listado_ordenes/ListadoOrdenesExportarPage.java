package pages.productor.listado_ordenes;


import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageobjects.proveedor.listado_ordenes.ListadoOrdenesProveedorPageObject;
import pages.BasePage;
import pages.proveedor.listado_ordenes.ListadoOrdenesFiltrarPage;
import utils.RestAssuredExtension;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ListadoOrdenesExportarPage extends BasePage {


    public ListadoOrdenesExportarPage() {
        super();
    }


    public void deleteIfExistFile() {
        File file = createFile();
        if (file.delete()) {
            log.info(String.format("El archivo '%s' ha sido borrado satisfactoriamente", file.getName()));
        } else {
            log.info("No hubo archivo '%s' para eliminar", file.getName());
        }
    }

    public void verifyDownloadFile() {
        //WEB-ELEMETS
        waitVisibility(ListadoOrdenesProveedorPageObject.ORDENES_CONTAINER, "10");
        List<WebElement> elementList = driver.findElements(ListadoOrdenesProveedorPageObject.ORDENES_CONTAINER);
        //Valores de las filas
        List<String> stringCellValueList = new ArrayList<>();
        //Creando el archivo
        File file = createFile();

        sleep(10);
        log.info(String.format("Verificando que exista el archivo '%s'", file.getName()));
        Assert.assertTrue(file.exists());

        int iRow = 0;
        if (file.exists()) {
            try {
                InputStream inputStream = new FileInputStream(file);
                Workbook workbook = WorkbookFactory.create(inputStream);
                Sheet sheet = workbook.getSheetAt(iRow);
                Row row = sheet.getRow(iRow);
                while (row != null) {
                    //La cuarta columna tiene el estado de la orden
                    Cell cell = row.getCell(4);
                    String value = cell.getStringCellValue();
                    log.info(String.format("Valor de la celda es %s", value));

                    //llenando la lista de valores de las filas de estado de la orden
                    stringCellValueList.add(value);
                    iRow++;
                    row = sheet.getRow(iRow);
                }
                inputStream.close();
            } catch (FileNotFoundException e) {
                log.info(e.getMessage());
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        }
        //Verificando que el estado de la orden sea el estado del excel
        if (ListadoOrdenesFiltrarPage.filterSelected != null) {
            for (int i = 1; i < stringCellValueList.size(); i++) {
                String value = stringCellValueList.get(i);
                log.info(String.format("El estado de la orden en excel es '%s' y el filtro seleccionado es '%s'", value, ListadoOrdenesFiltrarPage.filterSelected));
                Assert.assertTrue(value.equals(ListadoOrdenesFiltrarPage.filterSelected));
            }
        }
        //Verifico la cantidad de filas(cantidad de ordenes) más el HEADER
        //Esto va a cambiar por bug reportado, debe decargar todas las ordenes.
        log.info(String.format("La cantidad de órdenes en la UI es '%s' y el en el excel '%s'", elementList.size(), iRow - 1));
        Assert.assertTrue(iRow == elementList.size() + 1);
    }

    public static File createFile() {
        String dayOfMonth = String.format("%02d", LocalDateTime.now().getDayOfMonth());
        String month = String.format("%02d", LocalDateTime.now().getMonth().getValue());
        int year = LocalDateTime.now().getYear();
        String fileName = String.format("C:\\Users\\yailin.valdivia\\Downloads\\Tusordenes_Nera_%s_%s_%s.xlsx", dayOfMonth, month, year);
        return new File(fileName);
    }

    public void consumeApiFilterOrders(String sourceApi, String path, List<List<String>> t_table) {
        log.info(String.format("Consumiendo API: '%s' '%s'", sourceApi, path));
        getAcessTokenFromApiServices(sourceApi, "provider/auth/login");
        response = RestAssuredExtension.getMethodWithParamsHeader(sourceApi, path, t_table, getAccess_token());
    }
}
