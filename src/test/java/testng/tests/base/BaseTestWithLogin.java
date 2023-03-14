package testng.tests;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ser.Serializers;
import model.Credentials;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import pages.BasePage;
import pages.LoginPage;

public class BaseTestWithLogin extends BaseTest {

    @BeforeMethod
    @Parameters({"user", "password"})
    public void login(String user, String password)
    {
        var basePage = new BasePage(getDriver());
        basePage.waitTillPageLoaded();
        var page = new LoginPage(getDriver());
        page.login(Credentials.Create(user, password));
    }
}
