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
    public void deleteSystemUser(){
        var page = new UserManagementPage(getDriver());
        page.setUsernameSearchParameters(getTestData().getUserName());
        page.searchUser();

        page.removeUser(getTestData(), true);
        page.waitTillElementsDeleted();

        page.setUsernameSearchParameters(getTestData().getUserName());
        page.searchUser();

        var result = page.getUsers();
        Assert.assertEquals(result.size(), 0);
    }
}
