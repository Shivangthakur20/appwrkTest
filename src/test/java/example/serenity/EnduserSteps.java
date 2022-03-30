package example.serenity;


import example.test.Appwrk;
import net.thucydides.core.annotations.Step;

import java.io.IOException;

public class EnduserSteps {

    Appwrk appwrk;

    @Step
    public void userOpenTheUrl(String arg0) throws InterruptedException {
        appwrk.userOpenTheUrl(arg0);
    }

    @Step
    public void verifyThatAllTheLinksWithTagAreReturningStatusCode(String tagName, String statusCode) throws InterruptedException, IOException {
        appwrk.verifyThatAllTheLinksWithTagAreReturningStatusCode(tagName, statusCode);
    }
}
