package pages;
import utils.ReadXML;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * SenderReceiverPage extends BasePage
 * fills out sender and receiver info before payment page.
 */
public class SenderReceiverPage extends BasePage {


    /**
     * Method that calls all the other functions.
     */
    public void sendInfo(){
        pressForSomeoneElse();
        enterReceiverName();
        pickEvent();
        enterBlessing();
        addPicture();
        pressContinue();
        pressNow();
        pickEmailAndSMS();
        enterEmailAndPhone();
        enterSenderName();
        pressPayment();

    }

    /**
     * Clicks on the for someone else option, which is clicked by default.
     */
    public void pressForSomeoneElse(){
        clickOnElement(By.cssSelector("div[gtm='למישהו אחר']"));
    }

    /**
     * Enters the receiver name from the data.xml file. (I Used this as the receiver name is also asserted later)
     */
    public void enterReceiverName(){
        try {
            sendKeys(By.cssSelector("input[maxlength='25']"), ReadXML.getData("RECEIVER"));
            Assert.assertEquals(getWebElement(By.cssSelector("input[data-parsley-required-message='מי הזוכה המאושר? יש להשלים את שם המקבל/ת']")).getAttribute("value"), ReadXML.getData("RECEIVER"));
        }catch (Exception e){}
    }

    /**
     * Chooses the type of event the giftcard is for.
     */
    public void pickEvent(){
        clickOnElement(By.cssSelector("span[alt='לאיזה אירוע?']"));
        clickOnElement(By.cssSelector("li[value='32'"));
    }

    /**
     * Clears the text area from the default blessing and adds a custom one.
     */
    public void enterBlessing(){
        clickOnElement(By.tagName("textarea"));
       clearField(getWebElement(By.tagName("textarea")));
       sendKeys(By.tagName("textarea"),"May the odds be ever in your favor!");
    }

    /**
     * Uploads a picture.
     */
    public void addPicture(){
            sendKeys(By.cssSelector("input[type='file']"),"C:\\Users\\doviy\\Desktop\\ozzy.jpg");
    }

    /**
     * Clicks continue, brings us to part 2 of sender/receiver info
     */
    public void pressContinue(){
        clickOnElement(By.cssSelector("button[type='submit']"));
    }

    /**
     * Clicks on the now option, which is clicked by default.
     */

    public void pressNow(){
        clickOnElement(By.cssSelector("div[gtm='עכשיו']"));
    }

    /**
     * Chooses the option to send via email and sms
     */
    public void pickEmailAndSMS(){
        clickOnElement(By.cssSelector("svg[gtm='method-sms']"));
        clickOnElement(By.cssSelector("svg[gtm='method-email']"));
    }

    /**
     * Enters email and SMS information
     */
    public void enterEmailAndPhone(){
        sendKeys(By.id("sms"),"55 062 0485");
        sendKeys(By.id("email"),"zakk@ozzy.com");
    }

    /**
     * Enters the sender name and telephone.
     * Only adds info if the field is blank. System by default will add the logged in users info.
     * Asserts the senders name.
     */
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

    /**
     * Clicks the payment button
     * causes and alert to show since the phone humber is invalid.
     */
    public void pressPayment(){
        clickOnElement(By.cssSelector("button[type='submit']"));
    }

}
