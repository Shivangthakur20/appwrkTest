package example.test;

import example.serenity.ExtendedPageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Appwrk extends ExtendedPageObject {


    public void userOpenTheUrl(String arg0) throws InterruptedException {
        waitForPageLoaded();
        getDriver().get(arg0);
    }


    public void verifyThatAllTheLinksWithTagAreReturningStatusCode(String tagName, String statusCode) throws InterruptedException, IOException {
        waitForPageLoaded();
        List<WebElement> linksList = getDriver().findElements(By.xpath("//a[contains(@href,'ht')]"));
        String link = null;
        List<Integer> responsecode = new ArrayList<>();
        int totalLinks = linksList.size();
        System.out.println("Total Links on the page" + totalLinks);
        for (int i = 0; i <= totalLinks; i++) {
            try {
                link = linksList.get(i).getAttribute("href");
            } catch (Exception e) {
                System.out.println("Exception="+e);
            }


            if (link.equals("null") || link == null) {

            } else {
                getresponseCode(link);
                responsecode.add(getresponseCode(link));
            }

        }
        for (int j = 0; j <= responsecode.size(); j++) {
            Assert.assertEquals("200", responsecode.get(j));
        }

    }

}
