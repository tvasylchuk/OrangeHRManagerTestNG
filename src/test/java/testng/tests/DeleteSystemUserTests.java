package testng.tests;

import framework.PropertiesResourceManager;
import model.user.Role;
import model.user.SystemUser;
import model.user.UserStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.UserManagementPage;
import testng.tests.base.BaseTestSystemUser;

@Test
public class DeleteSystemUserTests extends BaseTestSystemUser {
    private final String FILE_NAME = "TestData\\PersonalInfo\\NewSystemUser.properties";

    private SystemUser testData;

    @BeforeClass
    public SystemUser initTestData(){
        PropertiesResourceManager rm= new PropertiesResourceManager(FILE_NAME);
        return(new SystemUser( Role.valueOf(rm.getPropertyValueByKey("UserRole")),
                UserStatus.valueOf(rm.getPropertyValueByKey("UserStatus")),
                rm.getPropertyValueByKey("EmployeeName"),
                rm.getPropertyValueByKey("UserName"),
                ""));
    }
    public void deleteSystemUser(){
        testData = initTestData();
        var page = new UserManagementPage(getDriver());
        page.setUsernameSearchParameters(testData.getUserName());
        page.searchUser();

        page.removeUser(testData, true);
        page.waitTillElementsDeleted();

        page.setUsernameSearchParameters(testData.getUserName());
        page.searchUser();

        var result = page.getUsers();
        Assert.assertEquals(result.size(), 0);
    }
}
