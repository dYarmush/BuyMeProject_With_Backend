package utils;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.sql.SQLException;

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
     * Checks and gets the instance of the driver, will set the driver based off of json
     * the data.xml "browserType" field
     * ChromeOptions are there for the Extras.printBallSize(); which needs the page to stop. Need to change
     * PageLoadStrategy.EAGER to PageLoadStrategy.None in order for it to work.
     * @return the driver instance.
     */
    public static WebDriver getDriverInstance() {


        //by default will read from JSON

        try {
          String type=Extras.getURLAndDriverFromJSON("driver");
          String URL=Extras.getURLAndDriverFromJSON("URL");
//
//
//        if (DBConnectionSingleton.getInstance() != null) {
//            try {
//                type = SQLUtilities.readBrowserFromDB();
//                URL =  SQLUtilities.readURLFromDB();
//            } catch (SQLException e) {
//            }
//        } else
//            try {
//                type = ReadXML.getData("browserType");
//                URL = ReadXML.getData("URL");
//            } catch (Exception e) {
//            }

        if (driver==null){
            if(type.equalsIgnoreCase("chrome")){
                    System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVER_PATH);
                    chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                    driver = new ChromeDriver(chromeOptions);
                    driver.get(URL);
                }else if(type.equals("FF")){
                    System.setProperty("webdriver.firefox.driver", Constants.GECKODRIVER_PATH);
                    driver = new FirefoxDriver();
                    driver.get(URL);
                }
            }
        } catch (IOException e) {e.printStackTrace();}
            return driver;
            }

}

