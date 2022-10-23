package utils;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {

    private static WebDriver driver;
    private static ChromeOptions chromeOptions = new ChromeOptions();

    public DriverSingleton() throws Exception {
    }

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

