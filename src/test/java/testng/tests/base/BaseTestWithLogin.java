package testng.tests.base;

import framework.Logger;
import model.Credentials;
import org.testng.annotations.BeforeMethod;
import pages.BasePage;
import pages.LoginPage;

public class BaseTestWithLogin extends BaseTest {

    @BeforeMethod
    public void login()
    {
        try {
            var basePage = new BasePage(getDriver());
            basePage.waitTillPageLoaded();
            var page = new LoginPage(getDriver());
            page.login(Credentials.Create(getSystemAdmin().getUserName(), getSystemAdmin().getPassword()));
        }
        catch(Exception e){
            Logger.getInstance().error(e.getMessage());
            Logger.getInstance().error(e.fillInStackTrace().toString());
            throw new RuntimeException(e);
        }
    }
}
