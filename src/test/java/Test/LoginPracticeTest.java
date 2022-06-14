package Test;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPracticeTest extends BaseTest {


    String invalideUsername = "pogresan username";
    String invalidePassword = "pogresan password";



    @BeforeMethod
    public void setUpPage(){

        driver.manage().window().maximize();
        driver.navigate().to(homepageUrl);

    }

    @Test
    public void successfulLogin(){

        String validUsername = excelreader.getStringData("Login",1,0);
        String validPassword = excelreader.getStringData("Login",1,1);

        homepage.clickOnPracticeButton();
        practicepage.clickOnTestLoginPageButton();
        loginpage.insertUsername(validUsername);
        loginpage.insertPassword(validPassword);
        scrollIntoView(loginpage.getSubmitButton());
        loginpage.clickOnSubmitButton();
        visibilityWait(logoutpage.getLogoutButton());

        Assert.assertTrue(logoutpage.getLogoutButton().isDisplayed());

    }

    @Test
    public void invalidUsername(){
        homepage.clickOnPracticeButton();
        practicepage.clickOnTestLoginPageButton();
        loginpage.insertUsername(invalideUsername);
        loginpage.insertPassword(invalidePassword);
        scrollIntoView(loginpage.getSubmitButton());
        loginpage.clickOnSubmitButton();

        Assert.assertEquals(loginpage.getErrorNotification().getText(),"Your username is invalid!");
    }

    @Test
    public void invalidPassword(){
        homepage.clickOnPracticeButton();
        practicepage.clickOnTestLoginPageButton();
        loginpage.insertUsername(invalideUsername);
        loginpage.insertPassword(invalidePassword);
        scrollIntoView(loginpage.getSubmitButton());
        loginpage.clickOnSubmitButton();

        Assert.assertEquals(loginpage.getErrorNotification().getText(),"Your password is invalid!");
    }


}
