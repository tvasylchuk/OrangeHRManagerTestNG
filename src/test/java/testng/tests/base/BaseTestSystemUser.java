package testng.tests.base;

import framework.PropertiesResourceManager;
import model.user.Role;
import model.user.SystemUser;
import model.user.UserStatus;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.pageComponents.MainMenuPageComponent;

import java.util.Random;

public class BaseTestSystemUser extends BaseTestWithLogin{

    private final String FILE_NAME = "TestData\\PersonalInfo\\NewSystemUser.properties";
    private static ThreadLocal<SystemUser> testData = new ThreadLocal<>();

    @BeforeMethod
    public void selectUserManagerPage(){
        var mainMenu = new MainMenuPageComponent(getDriver());
        mainMenu.clickMenu("Admin");
        getDriver().waitPageToLoad();
        Assert.assertEquals(getDriver().getBrowserUri(), "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
    }

    @BeforeTest
    public void initTestData(){
        Random range = new Random();

        PropertiesResourceManager rm= new PropertiesResourceManager(FILE_NAME);
        testData.set(new SystemUser( Role.valueOf(rm.getPropertyValueByKey("UserRole")),
                UserStatus.valueOf(rm.getPropertyValueByKey("UserStatus")),
                getSystemAdmin().getName(),
                rm.getPropertyValueByKey("UserName")+range.nextInt(1000),
                rm.getPropertyValueByKey("Password")));
    }

    public SystemUser getTestData(){
        return testData.get();
    }
}
