package starter.account;

import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.nio.charset.StandardCharsets;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.Matchers.equalTo;

public class PostAuthorized {

    private String username, token;
    private static String base_url = "https://demoqa.com/Account/v1/";

    //Authorized(+)
    @Step("I set an endpoint for POST authorized user")
    public String setPostEndpointAuthorized(){
        return base_url + "Authorized";
    }

    @Step("I request POST user authorized")
    public void requestPostUserAuthorized() throws Exception{
        username = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//username.json"), StandardCharsets.UTF_8);
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//token.json"), StandardCharsets.UTF_8);
        JSONObject requestData = new JSONObject();
        requestData.put("userName", username);
        requestData.put("password", "Valid!@312");

        given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestData.toJSONString())
                .when().post(setPostEndpointAuthorized());
    }
    @Step("validate the data detail after generate token")
    public void validateDataDetailAuthorized(){
        then().body(equalTo("true"));
    }

    //Authorized(-)
    @Step("I request POST user authorized with unregistered account")
    public void requestPostUserAuthorizedUnregistered () {
        JSONObject requestData = new JSONObject();
        requestData.put("userName", "hakim09999999");
        requestData.put("password", "Valid@30");

        given().header("Content-Type", "application/json")
                .body(requestData.toJSONString())
                .when().post(setPostEndpointAuthorized());
    }

    @Step("validate the data detail after authorized failed with user unregistered")
    public void validate_DataDetail_AuthorizedFailed_Unregistered () {
        then().body("code", equalTo("1207"));
    }

    @Step("I request POST user authorized with empty field")
    public void requestPOSTAuthorizedEmptyField () {
        JSONObject requestData = new JSONObject();
        requestData.put("userName", "");
        requestData.put("password", "");

        given().header("Content-Type", "application/json")
                .body(requestData.toJSONString())
                .when().post(setPostEndpointAuthorized());
    }
    @Step("validate the data detail after  authorized failed with empty field")
    public void validateAuthorizedFailedEmptyField () {
        then().body("code", equalTo("1200"));
    }
}
