package testng.tests;

import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import pages.UserManagementPage;
import pages.pageComponents.MainMenuPageComponent;
import testng.tests.base.BaseTest;

public class CleanConfiguration  extends BaseTest {

    @Test
    public void removeSystemAdmin(){
        var basePage = new BasePage(getDriver());
        basePage.waitTillPageLoaded();
        var loginPage = new LoginPage(getDriver());
        loginPage.login(getSuperAdmin());
        getDriver().waitPageToLoad();

        var mainMenu = new MainMenuPageComponent(getDriver());
        mainMenu.clickMenu("Admin");
        getDriver().waitPageToLoad();

        var page = new UserManagementPage(getDriver());
        page.setUsernameSearchParameters(getSystemAdmin().getUserName());
        page.searchUser();

        page.removeUser(getSystemAdmin(), true);
        page.waitTillElementsDeleted();
    }
}
