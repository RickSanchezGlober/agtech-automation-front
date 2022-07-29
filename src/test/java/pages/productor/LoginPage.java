package pages.productor;

import org.openqa.selenium.By;
import pageobjects.productor.LoginPageObject;
import pages.BasePage;
import pages.proveedor.GenerarOrdenCompraSolaFirmaPage;

import java.util.List;

public class LoginPage extends BasePage {

    public LoginPage() {
        super();
    }

    public static String accessToken;

    public void clickOnButtonByName(String buttonName) {
        By element = null;
        explicitWait(LoginPageObject.NEW_LOGO);
        switch (buttonName) {
            case "Logueate":
                element = LoginPageObject.LOGUEATE_BUTTON;
                break;
            case "Iniciar sesion":
                element = LoginPageObject.INICIAR_SESION_BUTTON;
                break;
            case "Cerrar sesión":
                element = LoginPageObject.CERRAR_SESION_BUTTON;
                break;
            case "Registrarse":
                element = LoginPageObject.REGISTRARSE_BUTTON;
                break;
        }
        explicitWait(element);
        click(element);
    }

    public void loginWithUserPasswd(String user, String password) {
        explicitWait(LoginPageObject.LOGUEATE_BUTTON);
        clickOnButtonByName("Logueate");
        explicitWait(LoginPageObject.USERNAME_INPUT);
        write(LoginPageObject.USERNAME_INPUT, user);
        write(LoginPageObject.PASSWORD_INPUT, password);
        click(LoginPageObject.INICIAR_SESION_BUTTON);

    }



    public boolean verifyMessageIsdisplayed(String action,String message) {
        log.info("***********************************************");
        log.info(action) ;
        switch (action) {
            case "logueo":
                return verifyVisibleText(LoginPageObject.MENSAJE_BIENVENIDA_TEXT, message);

            case "deslogueo":
                return verifyVisibleText(LoginPageObject.MENSAJE_INICIO_SESION_TEXT, message);

            case "login":
                message = "Inicio de sesión";
                return verifyVisibleText(LoginPageObject.MENSAJE_INICIO_SESION_TEXT, message);

            case "signup":
                message = "Creá tu cuenta";
                log.info("***********************************************");
                log.info(message);
                log.info(LoginPageObject.MENSAJE_REGISTRO_TEXT);
                return verifyVisibleText(LoginPageObject.MENSAJE_REGISTRO_TEXT, message);

        }
        return false;
    }

    public void getAccesTokenFromApiServicesValidation(String sourceApi, String path) {
        getAcessTokenFromApiServices(sourceApi,path);
    }
    public boolean verifyAccesDenied(String reason) {
      switch (reason) {
          case "credenciales_incorrectas":
              String message = "Algunos de los datos que escribiste no coincide.\n" +
                      "Intentá nuevamente.";
              return verifyVisibleText(LoginPageObject.MENSAJE_ERROR_LOGIN,message);

          case "boton_deshabilitado":
              String attribute = "disabled";
              return isAttributePresent(LoginPageObject.INICIAR_SESION_BUTTON,attribute);
      }
        return false;
    }

    public boolean verifyErrorPageDisplayed(String process) {
        String message ="";
        switch (process) {
            case "login":
                message = "Algo sucedió y no pudimos completar tu login";
                return verifyVisibleText(LoginPageObject.PAGINA_ERROR,message);

            case "signup":
                message = "Algo sucedió y no pudimos completar tu registro";
                return verifyVisibleText(LoginPageObject.PAGINA_ERROR,message);
        }
        return false;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAcessToken(String acessToken) {
        this.accessToken=accessToken;

    }

}
