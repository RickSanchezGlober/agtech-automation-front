package pages;

import org.openqa.selenium.By;
import pageobjects.ProductorLoginPageObject;

public class ProductorLoginPage extends BasePage {

    public ProductorLoginPage() {
        super();
    }

    public void clickOnButtonByName(String buttonName) {
        By element = null;
        explicitWait(ProductorLoginPageObject.NEW_LOGO);
        switch (buttonName) {
            case "Logueate":
                element = ProductorLoginPageObject.LOGUEATE_BUTTON;
                break;
            case "Iniciar sesión":
                element = ProductorLoginPageObject.INICIAR_SESION_BUTTON;
                break;
        }
        click(element);
    }

    public void loginWithUserPasswd(String user, String password) {
        explicitWait(ProductorLoginPageObject.USERNAME_INPUT);
        write(ProductorLoginPageObject.USERNAME_INPUT, user);
        write(ProductorLoginPageObject.PASSWORD_INPUT, password);
    }

    public boolean verifyMessageIsdisplayed(String message) {
        explicitWait(ProductorLoginPageObject.NEW_LOGO);
        return verifyVisibleText(ProductorLoginPageObject.MENSAJE_BIENVENIDA_TEXT, message);
    }
}
