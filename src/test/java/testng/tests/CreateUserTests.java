package testng.tests;

import framework.PropertiesResourceManager;
import model.user.Role;
import model.user.SystemUser;
import model.user.UserStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.UserManagementPage;
import pages.pageComponents.ToolBarMenuPageComponent;
import testng.tests.base.BaseTestSystemUser;

@Test
public class CreateUserTests extends BaseTestSystemUser {

    @Test
    public void createAdminUserPositiveTest(){
        var user = getTestData();//new SystemUser(testData.getRole(), testData.getStatus(), testData.getName(), testData.getUserName(), testData.getPassword());
        var toolBar = new ToolBarMenuPageComponent(getDriver());
        toolBar.clickMenu("User Management", "Users");
        getDriver().waitPageToLoad();

        var userManagementPage = new UserManagementPage(getDriver());
        userManagementPage.addNewUser(user);
    }
}
