import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasePage {
    public WebElement getWebElement(By locator){
        return DriverSingleton.getDriverInstance().findElement(locator);
    }

    public List<WebElement> getListOfWebElements(By locator){
        return DriverSingleton.getDriverInstance().findElements(locator);
    }

    public void sendKeys(By locator,String keysToSend){
        getWebElement(locator).sendKeys(keysToSend);
    }

    public void clickOnElement(By locator) {
        try {
            getWebElement(locator).click();
        } catch (Exception e) {
            //test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver, "picName")).build());
        }
    }
        public void clickOnElement (WebElement element){
            try {
                element.click();
            } catch (Exception e) {
                //test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver, "picName")).build());
            }
        }
    public static void clearField(WebElement e){
        e.clear();
    }
    }


