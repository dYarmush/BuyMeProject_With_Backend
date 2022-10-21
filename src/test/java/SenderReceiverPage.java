import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.File;

public class SenderReceiverPage extends BasePage{
    private WebElement element;
    private WebDriver driver=DriverSingleton.getDriverInstance();
    public void pressForSomeoneElse(){}

    public void sendInfo(){
        enterReceiverName();
        pickEvent();
        enterBlessing();
        addPicture();
        pressContinue();
        pickEmailAndSMS();
        enterEmailAndPhone();
        enterSenderName();
        pressPayment();

    }
    public void enterReceiverName(){
        try {
            sendKeys(By.cssSelector("input[maxlength='25']"), ReadXML.getData("RECEIVER"));
            Assert.assertEquals(getWebElement(By.cssSelector("input[maxlength='25']")).getAttribute("value"), ReadXML.getData("RECEIVER"));
        }catch (Exception e){}
    }
    public void pickEvent(){
        clickOnElement(By.cssSelector("span[alt='לאיזה אירוע?']"));
        clickOnElement(By.cssSelector("li[value='32'"));
    }
    public void enterBlessing(){
        clickOnElement(By.tagName("textarea"));
       clearField(getWebElement(By.tagName("textarea")));
       sendKeys(By.tagName("textarea"),"May the odds be ever in your favor!");
    }
    public void addPicture(){
            sendKeys(By.cssSelector("input[type='file']"),"C:\\Users\\doviy\\Desktop\\ozzy.jpg");
    }
    public void pressContinue(){
        clickOnElement(By.cssSelector("button[type='submit']"));
    }
    public void pressNow(){}
    public void pickEmailAndSMS(){
        clickOnElement(By.cssSelector("svg[gtm='method-sms']"));
        clickOnElement(By.cssSelector("svg[gtm='method-email']"));
    }
    public void enterEmailAndPhone(){
        sendKeys(By.id("sms"),"55 062 0485");
        sendKeys(By.id("email"),"zakk@ozzy.com");
    }
    public void enterSenderName() {
        try {
            if (getWebElement(By.cssSelector("input[placeholder='שם שולח המתנה'")).getAttribute("value").isBlank()) {
                sendKeys(By.cssSelector("input[placeholder='שם שולח המתנה'"), ReadXML.getData("SENDER"));
            }
            if (getWebElement(By.cssSelector("input[placeholder='מספר נייד'")).getAttribute("value").isBlank()) {
                sendKeys(By.cssSelector("input[placeholder='מספר נייד'"), "55 276 8214");
            }
            Assert.assertEquals(getWebElement(By.cssSelector("input[placeholder='שם שולח המתנה'")).getAttribute("value"), ReadXML.getData("SENDER"));
        } catch(Exception e){
        }
    }
    public void pressPayment(){
        clickOnElement(By.cssSelector("button[type='submit']"));
    }
    public void assertions()throws Exception{

    }
}
