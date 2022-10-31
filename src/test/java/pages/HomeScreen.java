package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverSingleton;
import java.util.List;

/**
 * HomeScreen page extends BasePage
 * finds elements and interacts with them on the BuyMe.co.il home screen/search page/
 */
public class HomeScreen extends BasePage{
    private WebDriver driver = DriverSingleton.getDriverInstance();
    private List <WebElement> dropdowns;

    /**
     * Method that calls all the other functions
     */
    public void chooseGift(){
        choosePricePoint();
        chooseRegion();
        chooseCategory();
        pressFindMeAGift();
    }

    /**
     * Selects the price point for the gift card.
     * I had issues with a popup when not registering a new user/logging in, used .refresh() to remove the popup
     * Uses dropdowns List
     */
    private void choosePricePoint()  {
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       // wait.until(ExpectedConditions.elementToBeClickable(By.className("selected-text")));
        try {Thread.sleep(4000);//couldn't get implicit or explicit wait to work correctly...
        }catch (Exception c){}
        //driver.navigate().refresh();  //clears pop up if not logging in
        dropdowns = getListOfWebElements(By.className("selected-text"));
        clickOnElement(dropdowns.get(0));
       clickOnElement(By.cssSelector("li[value='2']"));

    }

    /**
     * Method that chooses the region for the giftcard.
     * Uses dropdowns List
     */
    private void chooseRegion(){
        clickOnElement(dropdowns.get(1));
        clickOnElement(By.cssSelector("li[value='11']"));

    }

    /**
     * Method that chooses the category for the gift card
     * Uses dropdowns List
     */
    private void chooseCategory(){
        clickOnElement(dropdowns.get(2));
        clickOnElement(By.cssSelector("li[value='75']"));
    }

    /**
     * Presses the find Me a Gift button.
     */
    private void pressFindMeAGift(){
        clickOnElement(By.cssSelector("a[rel='nofollow']"));
    }
}
