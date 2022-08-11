package pages.proveedor;

import pageobjects.proveedor.HomePageObject;
import pages.BasePage;

public class HomePage extends BasePage {

    public HomePage () {
        super();
    }
    public  void enterActualPassword(String password){
        explicitWait(HomePageObject.ACTUAL_PASSWORD_INPUT);
        write(HomePageObject.ACTUAL_PASSWORD_INPUT, password);
        click(HomePageObject.VALIDATE_PASSWORD_BUTTON);
    }

    public  void enterNewPassword(String password){
        explicitWait(HomePageObject.NEW_PASSWORD_INPUTA);
        write(HomePageObject.NEW_PASSWORD_INPUTA, password);
        write(HomePageObject.NEW_PASSWORD_INPUTB, password);
        click(HomePageObject.CHANGE_PASSWORD_BUTTON);
    }

}
