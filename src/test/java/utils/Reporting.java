package utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import java.io.File;
import java.io.IOException;

/**
 * Reporting file creates and manages an Extent Report
 */
public class Reporting {
    private static WebDriver driver  = DriverSingleton.getDriverInstance();
    public static ExtentReports extent= new ExtentReports();
    public static ExtentTest test;

    /**
     * Sets up the Extent Report and htmlReporter
     */
    @BeforeSuite
    public static void setup() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\extent.html");
        extent.attachReporter(htmlReporter);
    extent.setSystemInfo("Host Name", "Dovi");
    htmlReporter.config().setReportName( "BuyMe.co.il Automation-Automation testing of buyme.co.il");
    }

    /**
     * gets the results of each test performed and assigns the appropriate test case based on the outcome.
     * @param result the test result
     */
    @AfterMethod
    public void getResult(ITestResult result)
    {
        if(result.getStatus() == ITestResult.FAILURE)
        {
            test.fail("Test case FAILED due to below issues:", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver, "picName")).build());
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            test.pass("Test Case PASSED");
        }
        else
        {
            test.skip(" Test Case SKIPPED");
            test.skip(result.getThrowable());
        }
    }

    /**
     * Method used to take a screenshot
     * @param driver driver being used.
     * @param ImagesPath where should the image be saved.
     * @return the image path + .png
     */
    public String takeScreenShot(WebDriver driver, String ImagesPath) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath + ".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return ImagesPath + ".png";
    }
}
