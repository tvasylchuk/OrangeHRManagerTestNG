package testng.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import pages.UserManagementPage;
import pages.pageComponents.MainMenuPageComponent;
import testng.tests.base.BaseTest;

public class PrepareConfiguration extends BaseTest {

    @Test
    public void CreateSystemAdmin(){
        var basePage = new BasePage(getDriver());
        basePage.waitTillPageLoaded();

        var loginPage = new LoginPage(getDriver());
        loginPage.login(getSuperAdmin());

        var mainMenu = new MainMenuPageComponent(getDriver());
        mainMenu.clickMenu("Admin");
        getDriver().waitPageToLoad();

        var umPage = new UserManagementPage(getDriver());
        umPage.addNewUser(getSystemAdmin());
    }
}
