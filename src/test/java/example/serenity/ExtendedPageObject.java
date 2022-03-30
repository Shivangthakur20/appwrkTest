package example.serenity;

import net.serenitybdd.core.pages.PageObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ExtendedPageObject extends PageObject {

    static Logger log = LoggerFactory.getLogger(ExtendedPageObject.class);

    public void waitForPageLoaded() throws InterruptedException {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        Thread.sleep(1999);
        System.out.println("Waiting for Page Load");

    }

    public String getURL() throws InterruptedException {
        waitForPageLoaded();
        Thread.sleep(2999);
        return getDriver().getCurrentUrl();
    }

    private WebElement findTheVisibleOne(By locator) {
        List<WebElement> allFound = this.getDriver().findElements(locator);
        log.info("Method: findTheVisibleOne() - Total found: " + allFound.size());
        WebElement visibleOne = null;
        for (WebElement webElement : allFound) {
            if (webElement.isDisplayed()) {
                log.info("visible one found: " + webElement.toString());
                visibleOne = webElement;
                break;
            }
        }
        return visibleOne;
    }

    public WebElement returnTheVisibleOne(By locator) throws InterruptedException {
        waitForPageLoaded();
        WebElement theVisibleOne = findTheVisibleOne(locator);
        if (theVisibleOne == null) {
            //try again after 2 second
            log.info("Trying again after 2 sec...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
//				System.err.println("Thread is interrupted when sleeping!!!");
                log.error("Thread is interrupted when sleeping!!!\n" + ex.toString(), ex);
            }
            theVisibleOne = findTheVisibleOne(locator);
            if (theVisibleOne == null)
                throw new InterruptedException("No visible element found");
        }
        log.info("Returning the visible one");
        return theVisibleOne;
    }

    public void refreshBrowser() {
        getDriver().navigate().refresh();
    }

    public void sendValueByPlaceHolder(String placeHolder,String value)throws InterruptedException{
        waitForPageLoaded();
        String xpathExpression = "(//input[@placeholder='" + placeHolder + "'])";
        int xpathCount = getDriver().findElements(By.xpath(xpathExpression)).size();
        System.out.println("number of field found "+xpathCount);
        if(xpathCount>1){
            String xpathExpression1 = "(//input[@placeholder='" + placeHolder + "'])[2]";
            returnTheVisibleOne(By.xpath(xpathExpression1)).sendKeys(value);
            Thread.sleep(2000);
        }
        else {
            returnTheVisibleOne(By.xpath(xpathExpression)).sendKeys(value);
            Thread.sleep(2000);
        }
    }
    public void sendValueByPlaceHolder1(String placeHolder,String value)throws InterruptedException{
        waitForPageLoaded();
        String xpathExpression = "(//input[@placeholder='" + placeHolder + "'])";
        int xpathCount = getDriver().findElements(By.xpath(xpathExpression)).size();
        System.out.println("number of field found "+xpathCount);
        if(xpathCount>1){
            String xpathExpression1 = "(//input[@placeholder='" + placeHolder + "'])[1]";
            returnTheVisibleOne(By.xpath(xpathExpression1)).sendKeys(value);
            Thread.sleep(2000);
        }
        else {
            returnTheVisibleOne(By.xpath(xpathExpression)).sendKeys(value);
            Thread.sleep(2000);
        }
    }
    public void clickOnGivenDropDown(String opt) throws InterruptedException{
        waitForPageLoaded();
        int len=getDriver().findElements(By.className("ui-dropdown-item")).size();
        /*if(len>1){
            String name;
            for(int i=0;i<len;i++){
                name=getDriver().findElements(By.className("ui-dropdown-item")).get(i).getText();
                System.out.println("text is: "+name);
                if(name.equalsIgnoreCase(opt)){
                    getDriver().findElements(By.className("ui-dropdown-item")).get(i).click();
                    System.out.println("text is with in loop: "+name);
                    Thread.sleep(999);
                    break;
                }
            }
        }
        else{
            System.out.println("drop down option not available");
        }*/
        String xpathExpression="//p-dropdown//li//span[text()='"+opt+"']";
        withTimeoutOf(20, TimeUnit.SECONDS).waitForPresenceOf(net.serenitybdd.core.annotations.findby.By.xpath(xpathExpression));
        getDriver().findElement(By.xpath(xpathExpression)).click();
        Thread.sleep(1999);

    }
    public String generateRandomName(String name,int min,int max){
        Random random=new Random();
        int randomNumber = random.nextInt(max + 1 - min) + min;
        return name+randomNumber;

    }
    public String getCurrentDateOnlydd(){
        String startdateenter = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
        int startdateint=Integer.parseInt(startdateenter);
        String realStartDate="1";
        if(startdateint<10){
            realStartDate=startdateenter.substring(1);
        }
        else{
            realStartDate=startdateenter;
        }

        System.out.println("current date is==="+startdateenter);
        return realStartDate;
    }

    public Integer generateRandomNumber(int min,int max){
        Random random=new Random();
        String number= String.valueOf(random.nextInt(max + 1 - min) + min);
        int randomNum= Integer.parseInt(number);
        return randomNum;

    }

    public String generateRandomNumberWithLength(int min,int max){
        return RandomStringUtils.randomNumeric(min,max);
    }

    public String getcurrentddMMyyyy(){
        String startdateenter = new SimpleDateFormat("ddMMyyyy").format(Calendar.getInstance().getTime());
        System.out.println("Current Date is =="+startdateenter);
        return startdateenter;
    }


    public static int comparator(String s1, String s2) {
        String[] pt1 = s1.split("((?<=[0-9])(?=[a-z]))");
        String[] pt2 = s2.split("((?<=[0-9])(?=[a-z]))");
        int i=0;
        System.out.println("Comp pt1"+pt1.toString());
        System.out.println("Comp pt2"+pt2.toString());
        System.out.println("Comp s1"+s1);
        System.out.println("Comp s2"+s2);
        if(Arrays.equals(pt1, pt2))
            return 0;
        else{
            for(i=0;i<Math.min(pt1.length, pt2.length);i++)
                if(!pt1[i].equals(pt2[i])) {
                    if(!isNumber(pt1[i],pt2[i])) {
                        if(pt1[i].compareTo(pt2[i])>0)
                            return 1;
                        else
                            return -1;
                    }
                    else {
                        int nu1 = Integer.parseInt(pt1[i]);
                        int nu2 = Integer.parseInt(pt2[i]);
                        if(nu1>nu2)
                            return 1;
                        else
                            return -1;
                    }
                }
        }

        if(pt1.length>i)
            return 1;
        else
            return -1;
    }

    private static Boolean isNumber(String n1, String n2) {

        try {
            int nu1 = Integer.parseInt(n1);
            int nu2 = Integer.parseInt(n2);
            return true;
        }
        catch(Exception x) {
            return false;
        }

    }

    public static int comparatorCurrency(String s1, String s2) {


        int number1= Integer.parseInt(s1);
        int number2= Integer.parseInt(s2);

        if (number1>number2) {
            return 1;
        }
        else if (number2==number1){
            return 0;
        }

        return -1;
    }

    public void scrollElementIntoViewID(String id) throws InterruptedException {
        JavascriptExecutor js=(JavascriptExecutor) getDriver();
        WebElement elName=getDriver().findElement(By.id(id));

           js.executeScript("arguments[0].scrollIntoView(false);", elName);
           Thread.sleep(1999);

    }

    public void scrollElementIntoViewXpath(String xpath) throws InterruptedException {
        JavascriptExecutor js=(JavascriptExecutor) getDriver();
        WebElement elName=getDriver().findElement(By.xpath(xpath));
        js.executeScript("arguments[0].scrollIntoView(false);", elName);
        js.executeScript("window.scrollBy(0,50)");
    }

    public String generateImagePath(String string){
        String location= System.getProperty("user.dir");
        String image="/src/test/resources/images/"+string;
        location=location.replace("\\","/");
        System.out.println(location+image);
        return location+image;
    }

    public String getcurrentMMddyyyy1(){
        String startdateenter = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        System.out.println("Current Date is =="+startdateenter);
        return startdateenter;
    }

    public String getdateyyyyMMdd(){
        String startdateenter = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        System.out.println("Current Date is =="+startdateenter);
        return startdateenter;
    }

    public void scrollElementIntoViewWebelement(WebElement webElement) throws InterruptedException {
        JavascriptExecutor js=(JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", webElement);
        js.executeScript("window.scrollBy(0,50)");
    }

    public String generatefutureRandomDatemmddyyyy(){
        String currentMMddyyyy=getcurrentMMddyyyy1();

        String[] mmddyyyy=currentMMddyyyy.split("/");
        int mm= Integer.parseInt(mmddyyyy[0]);
        int dd= Integer.parseInt(mmddyyyy[1]);
        int yyyy= Integer.parseInt(mmddyyyy[2]);
        if (mm<12){
            mm=mm+1;
        }else{
            mm=1;
            yyyy=yyyy+1;
        }
        if (dd>28){
            dd=dd-3;
        }

        String dateis=mm+"/"+dd+"/"+yyyy;
        return dateis;
    }


    public void readJsonInNewTab(){

        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(1));
        getDriver().get("https://fgref.freightgate.com/fgref/pltx2/api/fgref/623ddaf15b4d0000/menu?_dc=1648220914968&mode=tarifftrek&node=root");
    }


    public Integer getresponseCode(String url1) throws IOException {
//        HttpURLConnection c=
//                (HttpURLConnection)new
//                        URL(url)
//                        .openConnection();
//        // set the HEAD request with setRequestMethod
////        c.setRequestMethod("HEAD");
//        // connection started and get response code
//        c.connect();
//        int r = c.getResponseCode();
//        System.out.println("Http response code: " + r);

//        URL url=new URL(url1);
//        HttpURLConnection huc=(HttpURLConnection)url.openConnection();
//        huc.connect();
//       int r= huc.getResponseCode();
//
//       System.out.println("Response Code is=="+r);


        URL url = new URL(url1);
        java.net.HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        int r= conn.getResponseCode();

        if (r==404){
            System.out.println("Respnse code is " +r+ "for link "+url1);
        }
       System.out.println("Response Code is=="+r);

        return r;
    }

}
