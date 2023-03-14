package testng.tests;

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
        var basePage = new BasePage(getDriver());
        basePage.waitTillPageLoaded();
        var page = new LoginPage(getDriver());
        Assert.assertEquals(getDriver().getBrowserUri(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        page.login(getSuperAdmin());
        Assert.assertEquals(getDriver().getBrowserUri(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    }

    @Test
    public void emptyUserLoginTest()
    {
        var basePage = new BasePage(getDriver());
        basePage.waitTillPageLoaded();
        var page = new LoginPage(getDriver());
        page.login(Credentials.Create("", getSuperAdmin().getPassword()));

        Assert.assertEquals(getDriver().getBrowserUri(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Assert.assertTrue(page.isUserNameInvalid());
        Assert.assertFalse(page.isPasswordInvalid());
    }

    @Test
    public void emptyPasswordLoginTest()
    {
        var basePage = new BasePage(getDriver());
        basePage.waitTillPageLoaded();
        var page = new LoginPage(getDriver());
        page.login(Credentials.Create(getSuperAdmin().getUserName(), ""));

        Assert.assertEquals(getDriver().getBrowserUri(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Assert.assertFalse(page.isUserNameInvalid());
        Assert.assertTrue(page.isPasswordInvalid());

    }

    @Test
    public void invalidCredentialsLoginTest() {
        var basePage = new BasePage(getDriver());
        basePage.waitTillPageLoaded();
        var page = new LoginPage(getDriver());
        page.login(Credentials.Create(getSuperAdmin().getUserName(), getSuperAdmin().getPassword()+"wrong!"));

        Assert.assertEquals(getDriver().getBrowserUri(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Assert.assertTrue(page.isCredInvalid());
    }
}
