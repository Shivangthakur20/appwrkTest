package example.stepdefinitions;


import example.serenity.EnduserSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;


import java.io.IOException;

public class DefinitionSteps {

    @Steps
    EnduserSteps steps;

    @Given("user open the Url {string}")
    public void userOpenTheUrl(String arg0) throws InterruptedException {
        steps.userOpenTheUrl(arg0);
    }

    @Then("verify that all the links with tag {string} are returning {string} status code")
    public void verifyThatAllTheLinksWithTagAreReturningStatusCode(String arg0, String arg1) throws InterruptedException, IOException {
        steps.verifyThatAllTheLinksWithTagAreReturningStatusCode(arg0, arg1);

    }
}
