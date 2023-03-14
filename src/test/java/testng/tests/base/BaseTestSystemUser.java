package testng.tests.base;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import pages.pageComponents.MainMenuPageComponent;

public class BaseTestSystemUser extends BaseTestWithLogin{
    @BeforeMethod
    public void selectUserManagerPage(){
        var mainMenu = new MainMenuPageComponent(getDriver());
        mainMenu.clickMenu("Admin");
        getDriver().waitPageToLoad();
        Assert.assertEquals(getDriver().getBrowserUri(), "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
    }
}
