import pages.HomeScreen;
import pages.PickBusinessPage;
import pages.RegistrationPage;
import pages.SenderReceiverPage;
import utils.DriverSingleton;
import utils.ReadXML;
import utils.Reporting;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class BuyMeTesting extends Reporting {
    private WebDriver driver = DriverSingleton.getDriverInstance();

    @BeforeClass
    public void runBefore() {
        try{DriverSingleton.getDriverInstance().get(ReadXML.getData("URL"));}
        catch(Exception e){}
    }

    /**
     * tests the registration or login to BuyMe.co.il
     */
    @Test
    public void test01RegisterNewAccount(){
        RegistrationPage regPage = new RegistrationPage();
        regPage.register();
        test = extent.createTest("test01RegisterNewAccount","Test registering new account/login in " +
                "to existing account");
    }
    /**
     * Tests the choosing of a gift
     */
    @Test
    public void test02ChooseGift() {
       HomeScreen home = new HomeScreen();
       home.chooseGift();
       test = extent.createTest("test02ChooseGift","Testing choosing a gift.");
    }

    /**
     * Tests the choosing of a business.
     */
    @Test
    public void test03ChooseBusiness(){
        PickBusinessPage pb=new PickBusinessPage();
        pb.chooseBusiness();
        test = extent.createTest("test03ChooseBusiness", "Testing choosing a business.");
    }
    /**
     * Tests the senderReceiverPage
     */
    @Test
    public void test04SenderReceiverInfo() {
        SenderReceiverPage sRPage = new SenderReceiverPage();
        sRPage.sendInfo();
        test = extent.createTest("test04SenderReceiverInfo", "Test sender and receiver information input.");
    }

    /**
     * Ends the testing by pushing the Extent report data to the HTML report and quits the driver.
     */
    @AfterClass
    public void tearDown(){
        extent.flush();
        driver.quit();
    }

}