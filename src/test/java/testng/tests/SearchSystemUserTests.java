package testng.tests;

import model.user.SystemUser;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.UserManagementPage;
import testng.tests.base.BaseTestSystemUser;

@Test
public class SearchSystemUserTests extends BaseTestSystemUser {
    @Test
    public void SearchUserByUserName(){
        var page = new UserManagementPage(getDriver());
        page.setUsernameSearchParameters(getTestData().getUserName());
        page.searchUser();

        var result = page.getUsers();
        Assert.assertTrue(result.size()>0);

        for (SystemUser user:result) {
            Assert.assertEquals(user.getUserName(), getTestData().getUserName());
        }
    }

    @Test
    public void SearchUserByRole(){
        var page = new UserManagementPage(getDriver());
        page.setRoleSearchParameters(getTestData().getRole());
        page.searchUser();

        var result = page.getUsers();
        Assert.assertTrue(result.size()>0);

        for (SystemUser user:result) {
            Assert.assertEquals(user.getRole(), getTestData().getRole());
        }
    }

    @Test
    public void SearchUserByStatus(){
        var page = new UserManagementPage(getDriver());
        page.setStatusSearchParameters(getTestData().getStatus());
        page.searchUser();

        var result = page.getUsers();
        Assert.assertTrue(result.size()>0);

        for (SystemUser user:result) {
            Assert.assertEquals(user.getStatus(), getTestData().getStatus());
        }
    }

    @Test
    public void SearchUserByEmployeeName(){
        var page = new UserManagementPage(getDriver());
        page.setEmployeeNameSearchParameters(getTestData().getName());
        page.searchUser();

        var result = page.getUsers();
        Assert.assertTrue(result.size()>0);

        for (SystemUser user:result) {
            Assert.assertEquals(user.getName(), getTestData().getName());
        }
    }
}
