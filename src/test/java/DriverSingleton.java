import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import org.w3c.dom.Document;
import java.time.Duration;

public class DriverSingleton {

    private static WebDriver driver;

    public DriverSingleton() throws Exception {
    }

    public static WebDriver getDriverInstance(){
        String type = null;
        try {
            type = ReadXML.getData("browserType");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (driver==null){
                if(type.equals("Chrome")){
                    System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVER_PATH);
                    driver = new ChromeDriver();
                }else if(type.equals("FF")){
                    System.setProperty("webdriver.firefox.driver", Constants.GECKODRIVER_PATH);
                    driver = new FirefoxDriver();
                }

            }
            return driver;
            }
            public static void clearField(WebElement e){
        e.clear();
            }


}

