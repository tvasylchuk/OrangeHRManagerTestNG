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
public class SearchSystemUserTests extends BaseTestSystemUser {
    private final String FILE_NAME = "TestData\\PersonalInfo\\SearchSystemUser.properties";

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
    @Test
    public void SearchUserByUserName(){
        testData = initTestData();
        var page = new UserManagementPage(getDriver());
        page.setUsernameSearchParameters(testData.getUserName());
        page.searchUser();

        var result = page.getUsers();
        for (SystemUser user:result) {
            Assert.assertEquals(user.getUserName(), testData.getUserName());
        }
    }

    @Test
    public void SearchUserByRole(){
        testData = initTestData();
        var page = new UserManagementPage(getDriver());
        page.setRoleSearchParameters(testData.getRole());
        page.searchUser();

        var result = page.getUsers();
        for (SystemUser user:result) {
            Assert.assertEquals(user.getRole(), testData.getRole());
        }
    }

    @Test
    public void SearchUserByStatus(){
        testData = initTestData();
        var page = new UserManagementPage(getDriver());
        page.setStatusSearchParameters(testData.getStatus());
        page.searchUser();

        var result = page.getUsers();
        for (SystemUser user:result) {
            Assert.assertEquals(user.getStatus(), testData.getStatus());
        }
    }

    @Test
    public void SearchUserByEmployeeName(){
        testData = initTestData();
        var page = new UserManagementPage(getDriver());
        page.setEmployeeNameSearchParameters(testData.getName());
        page.searchUser();

        var result = page.getUsers();
        for (SystemUser user:result) {
            Assert.assertEquals(user.getName(), testData.getName());
        }
    }
}
