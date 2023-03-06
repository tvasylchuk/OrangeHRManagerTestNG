package tests;

import model.Credentials;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;

@Test
public class LoginTest extends BaseTest{

    @Test
    public void adminLoginTest()
    {
        var basePage = new BasePage(getDriver());
        basePage.waitTillPageLoaded();
        var page = new LoginPage(getDriver());

        Assert.assertEquals(getDriver().getBrowserUri(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        page.login(Credentials.Create("admin", "admin123"));
        Assert.assertEquals(getDriver().getBrowserUri(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    }

    @Test
    public void emptyUserLoginTest()
    {
        var basePage = new BasePage(getDriver());
        basePage.waitTillPageLoaded();
        var page = new LoginPage(getDriver());
        page.login(Credentials.Create("", "admin123"));

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
        page.login(Credentials.Create("admin", ""));

        Assert.assertEquals(getDriver().getBrowserUri(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Assert.assertFalse(page.isUserNameInvalid());
        Assert.assertTrue(page.isPasswordInvalid());

    }

    @Test
    public void invalidCredentialsLoginTest() {
        var basePage = new BasePage(getDriver());
        basePage.waitTillPageLoaded();
        var page = new LoginPage(getDriver());
        page.login(Credentials.Create("admin", "wrongPassword"));

        Assert.assertEquals(getDriver().getBrowserUri(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Assert.assertTrue(page.isCredInvalid());
    }

}
