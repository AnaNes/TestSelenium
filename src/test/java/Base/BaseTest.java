package Base;

import Pages.HomepagePage;
import Pages.LoginPage;
import Pages.LogoutPage;
import Pages.PracticePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    public WebDriver driver;
    public WebDriverWait wdwait;
    public HomepagePage homepage;
    public LoginPage loginpage;
    public PracticePage practicepage;
    public LogoutPage logoutpage;

    public ExcelReader excelreader;
    public String homepageUrl;


    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wdwait =  new WebDriverWait(driver, Duration.ofSeconds(10));
        homepage = new HomepagePage(driver, wdwait);
        loginpage = new LoginPage(driver,wdwait);
        practicepage = new PracticePage(driver,wdwait);
        logoutpage = new LogoutPage(driver,wdwait);
        excelreader = new ExcelReader("C:\\Users\\BG HiL Computers\\OneDrive\\Desktop\\TestData.xlsx");
        homepageUrl = excelreader.getStringData("Login",1,4);

    }

    public void visibilityWait(WebElement element) {
        wdwait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickabilityWait ( WebElement element) {
        wdwait.until(ExpectedConditions.elementToBeClickable(element));

    }

    public void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @AfterClass
    public void tearDown(){
    // driver.close();
    // driver.quit();
    }
}
