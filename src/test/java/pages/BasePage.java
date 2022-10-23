package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.DriverSingleton;
import java.util.List;

public class BasePage {
    public static WebElement getWebElement(By locator){
        return DriverSingleton.getDriverInstance().findElement(locator);
    }

    public List<WebElement> getListOfWebElements(By locator){
        return DriverSingleton.getDriverInstance().findElements(locator);
    }

    public void sendKeys(By locator,String keysToSend){
        getWebElement(locator).sendKeys(keysToSend);
    }

    public void clickOnElement(By locator) {
          getWebElement(locator).click();

    }
        public void clickOnElement (WebElement element){
             element.click();
          }
    public static void clearField(WebElement e){
        e.clear();
    }
    }


