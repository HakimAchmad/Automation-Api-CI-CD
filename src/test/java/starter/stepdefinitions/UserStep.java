package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.account.Get;
import starter.account.PostAuthorized;
import starter.account.PostGenerateToken;
import starter.account.PostUser;

public class UserStep {

    public String userId, token, username;

    @Steps
    Get get;

    @Steps
    PostUser postUser;

    @Steps
    PostGenerateToken postGenerateToken;

    @Steps
    PostAuthorized postAuthorized;

    @Given("I set an endpoint for GET detail user")
    public void iSetAnEndpointForGETDetailUser() {
        get.setAnEndpointForGet(this.userId);
    }

    @When("I request GET detail user")
    public void iRequestGETDetailUser() throws Exception{
        get.requestGetDetailUser();
    }

    @And("validate the data detail userId")
    public void validateTheDataDetailUserId() {
        get.validateDataDetailUserId(this.userId);
    }

    @Given("I set an endpoint for POST new user")
    public void iSetAnEndpointForPOSTNewUser() {
        postUser.setPostEndpointUser();
    }
    @When("I request POST detail user")
    public void i_Request_POSTDetailUser() {
        this.username = postUser.getUsername();
    }

    @Given("I set an endpoint for POST new {string} with {string}")
    public void i_Set_AnEndpoint_ForPOST_NewUser(String username, String password) {
        postUser.setPostEndpointUser(username, password);
    }

    @Then("I validate the status code is {int}")
    public void iValidateTheStatusCodeIs(int arg0) {
        get.validateStatusCode(arg0);
    }
    @And("validate the {string} data detail user")
    public void validateTheDataDetailUser(String message) {
        postUser.validateDataDetailUser(message);
    }
    @And("get userId if {string} for other request")
    public void getUserIdForOtherRequest(String message) {
        if (message.equals("success")){
            postUser.getUserId();
        }
    }

    @Given("I set an endpoint for POST generate token")
    public void iSetAnEndpointForPOSTGenerateToken() {
        postGenerateToken.setEndpointForGenerate();
    }
    @When("I request POST generate token")
    public void iRequestPOSTGenerateToken() throws Exception{
        postGenerateToken.requestPostGenerateToken();
    }
    @And("validate the data detail after generate token")
    public void validateTheDataDetailAfterGenerateToken() {
        postGenerateToken.validateDataDetailGenerateToken();
    }
    @And("get token for other request")
    public void getTokenForOtherRequest() {
        this.token = postGenerateToken.getToken();
    }

    @When("I request POST generate token with invalid password")
    public void iRequestPOSTGenerateTokenWithInvalidPassword() {
          postGenerateToken.requestPOSTGenerateTokenInvalid();
    }

    @And("validate the data detail after generate token failed with invalid password")
    public void validate_DataDetail_GenerateTokenFailed_InvalidPassword() {
        postGenerateToken.validate_DataDetail_GenerateTokenFailed_InvalidPassword();
    }

    @When("I request POST generate token with empty password")
    public void iRequestPOSTGenerateTokenWithEmptyPassword() {
        postGenerateToken.requestPOSTGenerateTokenEmpty();
    }

    @And("validate the data detail after generate token failed with empty password")
    public void validateTokenFailedEmptyPassword() {
        postGenerateToken.validateTokenFailedEmptyPassword();
    }

    @Given("I set an endpoint for POST authorized")
    public void iSetAnEndpointForPOSTAuthorized() {
        postAuthorized.setPostEndpointAuthorized();
    }
    @When("I request POST user authorized")
    public void iRequestPOSTUserAuthorized() throws Exception{
        postAuthorized.requestPostUserAuthorized();
    }

    @And("validate the data detail for authorized")
    public void validateTheDataDetailForAuthorized() {
        postAuthorized.validateDataDetailAuthorized();
    }

    @When("I request POST user authorized with unregistered account")
    public void iRequestPOSTUserAuthorizedWithUnregisteredAccount() {
        postAuthorized.requestPostUserAuthorizedUnregistered();
    }

    @And("validate the data detail after authorized failed with user unregistered")
    public void validateTheDataDetailAfterAuthorizedFailedWithUserUnregistered() {
        postAuthorized.validate_DataDetail_AuthorizedFailed_Unregistered();
    }

    @When("I request POST user authorized with empty field")
    public void iRequestPOSTUserAuthorizedWithEmptyField() {
        postAuthorized.requestPOSTAuthorizedEmptyField();
    }

    @And("validate the data detail after  authorized failed with empty field")
    public void validateTheDataDetailAfterAuthorizedFailedWithEmptyField() {
        postAuthorized.validateAuthorizedFailedEmptyField();
    }




}


