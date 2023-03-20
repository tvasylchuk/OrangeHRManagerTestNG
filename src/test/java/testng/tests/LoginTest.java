package testng.tests;

import framework.Logger;
import model.Credentials;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import pages.UserManagementPage;
import pages.pageComponents.MainMenuPageComponent;
import testng.tests.base.BaseTest;

@Test
public class LoginTest extends BaseTest {

    @Test
    public void adminLoginTest()
    {
        try{
            var basePage = new BasePage(getDriver());
            basePage.waitTillPageLoaded();
            var page = new LoginPage(getDriver());
            Assert.assertEquals(getDriver().getBrowserUri(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

            page.login(getSuperAdmin());
            Assert.assertEquals(getDriver().getBrowserUri(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        }
        catch(Exception e)
        {
            Logger.getInstance().error(e.getMessage());
            Logger.getInstance().error(e.getStackTrace().toString());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void emptyUserLoginTest()
    {
        try{
            var basePage = new BasePage(getDriver());
            basePage.waitTillPageLoaded();
            var page = new LoginPage(getDriver());
            page.login(Credentials.Create("", getSuperAdmin().getPassword()));

            Assert.assertEquals(getDriver().getBrowserUri(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            Assert.assertTrue(page.isUserNameInvalid());
            Assert.assertFalse(page.isPasswordInvalid());
        }
        catch(Exception e)
        {
            Logger.getInstance().error(e.getMessage());
            Logger.getInstance().error(e.getStackTrace().toString());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void emptyPasswordLoginTest()
    {
        try{
            var basePage = new BasePage(getDriver());
            basePage.waitTillPageLoaded();
            var page = new LoginPage(getDriver());
            page.login(Credentials.Create(getSuperAdmin().getUserName(), ""));

            Assert.assertEquals(getDriver().getBrowserUri(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            Assert.assertFalse(page.isUserNameInvalid());
            Assert.assertTrue(page.isPasswordInvalid());
        }
        catch(Exception e)
        {
            Logger.getInstance().error(e.getMessage());
            Logger.getInstance().error(e.getStackTrace().toString());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void invalidCredentialsLoginTest() {
        try{
            var basePage = new BasePage(getDriver());
            basePage.waitTillPageLoaded();
            var page = new LoginPage(getDriver());
            page.login(Credentials.Create(getSuperAdmin().getUserName(), getSuperAdmin().getPassword()+"wrong!"));

            Assert.assertEquals(getDriver().getBrowserUri(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            Assert.assertTrue(page.isCredInvalid());
        }
        catch(Exception e)
        {
            Logger.getInstance().error(e.getMessage());
            Logger.getInstance().error(e.getStackTrace().toString());
            throw new RuntimeException(e);
        }
    }
}
