package testng.tests;

import framework.Logger;
import model.user.SystemUser;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.UserManagementPage;
import testng.tests.base.BaseTestSystemUser;

@Test
public class SearchSystemUserTests extends BaseTestSystemUser {
    @Test
    public void searchUserByUserName(){
        try{
            var page = new UserManagementPage(getDriver());
            page.setUsernameSearchParameters(getTestData().getUserName());
            page.searchUser();

            var result = page.getUsers();
            Assert.assertTrue(result.size()>0);

            for (SystemUser user:result) {
                Assert.assertEquals(user.getUserName(), getTestData().getUserName());
            }
        }
        catch(Exception e)
        {
            Logger.getInstance().error(e.getMessage());
            Logger.getInstance().error(e.getStackTrace().toString());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void searchUserByRole(){
        try {
            var page = new UserManagementPage(getDriver());
            page.setRoleSearchParameters(getTestData().getRole());
            page.searchUser();

            var result = page.getUsers();
            Assert.assertTrue(result.size()>0);

            for (SystemUser user:result) {
                Assert.assertEquals(user.getRole(), getTestData().getRole());
            }
        }
        catch(Exception e)
        {
            Logger.getInstance().error(e.getMessage());
            Logger.getInstance().error(e.getStackTrace().toString());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void searchUserByStatus(){
        try{
            var page = new UserManagementPage(getDriver());
            page.setStatusSearchParameters(getTestData().getStatus());
            page.searchUser();

            var result = page.getUsers();
            Assert.assertTrue(result.size()>0);

            for (SystemUser user:result) {
                Assert.assertEquals(user.getStatus(), getTestData().getStatus());
            }
        }
        catch(Exception e)
        {
            Logger.getInstance().error(e.getMessage());
            Logger.getInstance().error(e.getStackTrace().toString());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void searchUserByEmployeeName(){
        try{
            var page = new UserManagementPage(getDriver());
            page.setEmployeeNameSearchParameters(getTestData().getName());
            page.searchUser();

            var result = page.getUsers();
            Assert.assertTrue(result.size()>0);

            for (SystemUser user:result) {
                Assert.assertEquals(user.getName(), getTestData().getName());
            }
        }
        catch(Exception e)
        {
            Logger.getInstance().error(e.getMessage());
            Logger.getInstance().error(e.getStackTrace().toString());
            throw new RuntimeException(e);
        }
    }
}
