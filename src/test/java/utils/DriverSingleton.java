package utils;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Driver singelton class maintains one instance of the driver throughout the various classes.
 */
public class DriverSingleton {

    private static WebDriver driver;
    private static ChromeOptions chromeOptions = new ChromeOptions();

    /**
     * Constructor for the Driver Singleton
     */
    public DriverSingleton()  {
    }

    /**
     * Checks and gets the instance of the driver, will set the driver based off of the data.xml "browserType" field
     * ChromeOptions are there for the Extras.printBallSize(); which needs the page to stop. Need to change
     * PageLoadStrategy.EAGER to PageLoadStrategy.None in order for it to work.
     * @return the driver instance.
     */
    public static WebDriver getDriverInstance(){
        String type;
        try {
            type = ReadXML.getData("browserType");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (driver==null){
                if(type.equals("Chrome")){
                    System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVER_PATH);
                    chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                    driver = new ChromeDriver(chromeOptions);
                }else if(type.equals("FF")){
                    System.setProperty("webdriver.firefox.driver", Constants.GECKODRIVER_PATH);
                    driver = new FirefoxDriver();
                }
            }
            return driver;
            }

}

