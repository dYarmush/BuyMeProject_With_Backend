import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class BuyMeTesting extends Reporting{
    private WebDriver driver = DriverSingleton.getDriverInstance();

    @BeforeClass
    public void runBefore() throws Exception {

        DriverSingleton.getDriverInstance().get(ReadXML.getData("URL"));
    }
    @Test
    public void test01RegisterNewAccount(){
        RegistrationPage regPage = new RegistrationPage();
        regPage.register();
        test = extent.createTest("test01RegisterNewAccount","Test registering new account/login in " +
                "to exsiting account");
    }

    @Test
    public void test02ChooseGift() throws InterruptedException {
       HomeScreen home = new HomeScreen();
       home.chooseGift();
       test = extent.createTest("test02ChooseGift");
    }
    @Test
    public void test03ChooseBusiness(){

        PickBusinessPage pb=new PickBusinessPage();
        pb.chooseBusiness();
        test = extent.createTest("test03ChooseBusiness");

    }
    @Test
    public void test04SenderReceiverInfo() {
        SenderReceiverPage sRPage = new SenderReceiverPage();
        sRPage.sendInfo();
        test = extent.createTest("test04SenderReceiverInfo");

    }
    @AfterClass
    public void tearDown(){
        extent.flush();
        driver.quit();
    }

}