package pages.proveedor.autenticacion;

import pageobjects.proveedor.autenticacion.LoginPageObject;
import pages.BasePage;

public class LoginPage extends BasePage {

    public LoginPage() {
        super();
    }


    public boolean verifyMessageWellcome(String message) {
        explicitWait(LoginPageObject.NEW_LOGO);
        return verifyVisibleText(LoginPageObject.MENSAJE_BIENVENIDA_TEXT, message);
    }
}
