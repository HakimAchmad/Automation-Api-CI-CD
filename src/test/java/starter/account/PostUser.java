package starter.account;

import Utils.RandomNumber;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import static org.hamcrest.Matchers.equalTo;

public class PostUser {

    RandomNumber randomNumber = new RandomNumber();
    String username;

    private static String base_url = "https://demoqa.com/Account/v1/";

    //User (DDT)
    @Step("I set an endpoint for POST new user")
    public String setPostEndpointUser() {
        return base_url + "User";
    }

    @Step("I set an endpoint for POST new user")
    public String getUsername() {
        return username;
    }

    @Step("I request POST detail user")
    public void setPostEndpointUser(String username, String password) {
        JSONObject requestData = new JSONObject();
        if (username.equals("new")) {
            this.username = randomNumber.randomUsername();
            try (FileWriter file = new FileWriter("src//test//resources//filejson//username.json")) {
                file.write(this.username);
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (username.equals("same")) {
            this.username = "hakim1710";
        }

        requestData.put("userName", this.username);
        requestData.put("password", password);

        SerenityRest.given().header("Content-Type", "application/json").body(requestData.toJSONString());
        SerenityRest.when().post(setPostEndpointUser());
    }

    @Step("validate the data detail user")
    public void validateDataDetailUser(String message) {
        if (message.equals("success")) {
            SerenityRest.then().body("username", equalTo(this.username));
        } else {
            SerenityRest.then().body("username", equalTo(null));
        }
    }

    @Step("Get userId from the response")
    public String getUserId() {
        Response response = SerenityRest.lastResponse();
        String userId = response.body().path("userID");
        try (FileWriter file = new FileWriter("src//test//resources//filejson//userId.json")) {
            file.write(userId);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(userId);
        return userId;
    }
}


