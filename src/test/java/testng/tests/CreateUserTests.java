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
    private final String FILE_NAME = "TestData\\PersonalInfo\\NewSystemUser.properties";

    private SystemUser testData;

    @BeforeClass
    public SystemUser initTestData(){
        PropertiesResourceManager rm= new PropertiesResourceManager(FILE_NAME);
        return(new SystemUser( Role.valueOf(rm.getPropertyValueByKey("UserRole")),
                UserStatus.valueOf(rm.getPropertyValueByKey("UserStatus")),
                rm.getPropertyValueByKey("EmployeeName"),
                rm.getPropertyValueByKey("UserName"),
                rm.getPropertyValueByKey("Password")));
    }
    @Test
    public void createAdminUserPositiveTest(){
        testData = initTestData();
        var user = new SystemUser(testData.getRole(), testData.getStatus(), testData.getName(), testData.getUserName(), testData.getPassword());
        var toolBar = new ToolBarMenuPageComponent(getDriver());
        toolBar.clickMenu("User Management", "Users");
        getDriver().waitPageToLoad();

        var userManagementPage = new UserManagementPage(getDriver());
        userManagementPage.addNewUser(user);
    }
}
