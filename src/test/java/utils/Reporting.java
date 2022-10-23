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

public class Reporting {
    private static WebDriver driver  = DriverSingleton.getDriverInstance();
    public static ExtentReports extent= new ExtentReports();
    public static ExtentTest test;
@BeforeSuite
    public static void setup() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\extent.html");
        extent.attachReporter(htmlReporter);
    extent.setSystemInfo("Host Name", "Dovi");
    htmlReporter.config().setReportName( "BuyMe.co.il Automation-Automation testing of buyme.co.il");
    }
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
