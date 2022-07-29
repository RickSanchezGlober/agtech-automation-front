package pages;

import utils.RestAssuredExtension;

public class ManagePetsPage extends BasePage {
    public void findByPetId(String id, String path) {
        String idContext = getScenarioContextVariables(id);
        log.info(idContext);
        RestAssuredExtension.getMethod(path + idContext,"bff","342");
        RestAssuredExtension.response.getBody().prettyPrint();
    }

    public boolean verifyServiceStatusCode(String statusCode) {
        return RestAssuredExtension.response.statusCode() == Integer.parseInt(statusCode);
    }

    public void updateExistingPets(String path, String body) {
        RestAssuredExtension.putMethod(path, body,"bff","21312");
        RestAssuredExtension.response.getBody().prettyPrint();
    }
}
