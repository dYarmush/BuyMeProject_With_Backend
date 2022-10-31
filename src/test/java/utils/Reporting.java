package utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.opencsv.CSVWriter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Reporting file creates and manages an Extent Report
 */
public class Reporting {
    public static Connection con=DBConnectionSingleton.getInstance();
    private static WebDriver driver  = DriverSingleton.getDriverInstance();
    public static ExtentReports extent= new ExtentReports();
    public static ExtentTest test= extent.createTest("Testing BuyME.co.il");
    private static String status;
    /**
     * Sets up the Extent Report and htmlReporter
     */
    @BeforeSuite
    private static void setup() {
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
    private void getResult(ITestResult result) throws SQLException, IOException {
        if(result.getStatus() == ITestResult.FAILURE)
        {   status ="Test case FAILED due to below issues:";
            test.fail(status, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver, "picName")).build());
            test.fail(result.getThrowable());
           saveHistory();
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {   status ="Test Case PASSED";
            test.pass(status);
            test.pass(result.getThrowable());
            saveHistory();
        }
        else
        {
            status = "Test case SKIPPED";
            test.skip(status);
            test.skip(result.getThrowable());
           saveHistory();
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

    /**
     * Method that saves the tet history using id, date and status.
     * Checks for database connection being null and if so saves to csv instead
     * @throws SQLException in case of SQL error
     */
    private static void saveHistory() throws SQLException {

        if (con != null) {
            SQLUtilities.writeTestResultsToDB(con, status);
        } else {

            saveHistoryCSV(SQLUtilities.test_id,SQLUtilities.getDate(),status);
            SQLUtilities.test_id++;
        }
    }

    /**
     * Saves the history to a text file
     * @param test_id the test Id from SQLUtilities
     * @param test_date test dateTime
     * @param status test status
     * @throws IOException because of File class
     */
    private static void saveHistoryText(int test_id, String test_date, String status) throws IOException {
        String filePath = System.getProperty("user.dir")+"\\history.txt";

        Files.writeString(Paths.get(filePath), test_id+","+test_date+","+status);
    }

    /**
     * Method to save the history to a csv file
     * @param test_id-test ID from SQLUtilities
     * @param test_date test dateTime
     * @param status test status
     */
    private static void saveHistoryCSV(int test_id, String test_date, String status)  {
try {
    FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "history.csv", true);
    CSVWriter writer = new CSVWriter(fileWriter);

    String[] header = {"Test id", "Test Date", "Status"};
    writer.writeNext(header);

    String[] data = {String.valueOf(SQLUtilities.test_id), SQLUtilities.getDate(), status};
    writer.writeNext(data);
    writer.close();
}
catch (IOException i){i.printStackTrace();}

    }
}
