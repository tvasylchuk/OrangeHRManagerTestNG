package testng.tests.base;

import model.Credentials;
import org.testng.annotations.BeforeMethod;
import pages.BasePage;
import pages.LoginPage;

public class BaseTestWithLogin extends BaseTest {

    @BeforeMethod
    public void login()
    {
        var basePage = new BasePage(getDriver());
        basePage.waitTillPageLoaded();
        var page = new LoginPage(getDriver());
        page.login(Credentials.Create(getSystemAdmin().getUserName(), getSystemAdmin().getPassword()));
    }
}
