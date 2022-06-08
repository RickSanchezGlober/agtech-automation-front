package pageobjects;

import org.openqa.selenium.By;

public class ProductorListadoOrdenesPageObject {
    public static final By.ByTagName EMPTY_STATE_ICON = new By.ByTagName("i");
    public static final By.ByClassName SIN_ORDENES_COMPRA_TEXT = new By.ByClassName("confirmation_page__container__title");
    public static final By.ByClassName CUANDO_TENGAS_TEXT = new By.ByClassName("confirmation_page__container__subtitle");
    public static final By.ByTagName VOLVER_BUTTON = new By.ByTagName("a");
    public static final By.ByClassName ORDENES_CONTAINER = new By.ByClassName("card__content");
    public static final By.ByClassName NOMBRE_PRODUCTOR_CONTAINER = new By.ByClassName("card__content_title");
    public static final By.ByClassName NUMERO_ORDEN_CONTAINER = new By.ByClassName("card__content_ordernumber");
    public static final By.ByClassName FECHA_HORA_GENERACION_CONTAINER = new By.ByClassName("card__content_date");
    public static final By.ByClassName DESCRIPCION_CONTAINER = new By.ByClassName("card__content_description");
}