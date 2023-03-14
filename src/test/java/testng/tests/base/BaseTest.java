package testng.tests.base;

import framework.PropertiesResourceManager;
import framework.webDriverFactory.BrowserType;
import framework.webDriverFactory.Driver;
import framework.webDriverFactory.TestRunMode;
import model.Credentials;
import model.user.Role;
import model.user.SystemUser;
import model.user.UserStatus;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.internal.collections.Pair;

import java.util.Objects;

public class BaseTest {

    protected final String SYSTEM_USER_FILE_NAME = "TestData\\PersonalInfo\\AdminUser.properties";
    private static final String SAUCE_LAB_CRED_FILE = "Driver.properties";
    private static final ThreadLocal<Driver> driver = new ThreadLocal<Driver>();
    private static Credentials superAdmin;
    private static SystemUser systemAdmin;

    @BeforeSuite
    public void initAdminUsers(){
        PropertiesResourceManager rm = new PropertiesResourceManager(SYSTEM_USER_FILE_NAME);

        superAdmin = Credentials.Create(rm.getPropertyValueByKey("SuperAdmin"),
                rm.getPropertyValueByKey("SuperAdminPassword"));

        systemAdmin = new SystemUser(Role.valueOf(rm.getPropertyValueByKey("SystemAdminRole")),
                UserStatus.valueOf(rm.getPropertyValueByKey("SystemAdminStatus")),
                rm.getPropertyValueByKey("SystemAdminName"),
                rm.getPropertyValueByKey("SystemAdmin"),
                rm.getPropertyValueByKey("SystemAdminPassword"));
    }

    public Credentials getSuperAdmin(){
        return superAdmin;
    }

    public SystemUser getSystemAdmin(){
        return systemAdmin;
    }

    @BeforeMethod
    @Parameters({"browser", "runMode"})
    public static void setUp(ITestContext context, String browserType, String runMode)
    {
        String name  = context.getName();
        driver.set(Driver.initDriver(BrowserType.valueOf(browserType), TestRunMode.valueOf(runMode), name));
        context.setAttribute("browser", driver.get());
        driver.get().navigate("https://opensource-demo.orangehrmlive.com");
        driver.get().waitPageToLoad();
        driver.get().maximise();
    }

    @AfterMethod
    public void tearDown()
    {
        driver.get().exit();
        driver.remove();
    }

    private static Pair<String, String> initSauceLabsCredPair() {
        PropertiesResourceManager rm = new PropertiesResourceManager(SAUCE_LAB_CRED_FILE);
        String sauceUser = rm.getPropertyValueByKey("User");
        String sauceKey = rm.getPropertyValueByKey("Key");
        return new Pair<>(sauceUser, sauceKey);
    }

    protected synchronized Driver getDriver()
    {
        return Objects.requireNonNull(driver.get());
    }
}
