import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.Duration;

public class RegistrationPage extends BasePage {

    private WebDriver driver=DriverSingleton.getDriverInstance();
    private WebElement webElement;
    public void register(){
        beginRegistration();
        login();
        //signup();
    }
    public void login(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        sendKeys(By.cssSelector("input[placeholder='מייל'"),"ozzy@ozzy.com");
        sendKeys(By.cssSelector("input[placeholder='סיסמה'"),"Ozzy1234");
        clickOnElement(By.cssSelector("button[type='submit']"));

    }
    public void beginRegistration() {clickOnElement(By.className("notSigned"));}
    public void signup(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        clickOnElement(By.cssSelector("span[class='text-link theme']"));

        //name
        sendKeys(By.cssSelector("input[placeholder='שם פרטי'"),"Ozzy");
        Assert.assertEquals(getWebElement(By.cssSelector("input[placeholder='שם פרטי'")).getAttribute("value"),"Ozzy");
        //email
        sendKeys(By.cssSelector("input[placeholder='מייל'"),"ozzy@ozzy.com");
          Assert.assertEquals(getWebElement(By.cssSelector("input[placeholder='מייל'")).getAttribute("value"),"ozzy@ozzy.com");
//        //password
       sendKeys(By.id("valPass"),"Ozzy1234");

//        //verify PW
       sendKeys(By.cssSelector("input[placeholder='אימות סיסמה'"),"Ozzy1234");
//        //Agreement
        clickOnElement(By.className("fill"));

//        //signup button
        clickOnElement(By.cssSelector("button[type='submit']"));
    }
}