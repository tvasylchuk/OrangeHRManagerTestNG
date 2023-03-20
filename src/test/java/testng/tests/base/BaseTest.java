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
import org.testng.annotations.*;
import org.testng.internal.collections.Pair;

import java.util.Objects;
import java.util.Random;

public class BaseTest {

    protected final String SYSTEM_USER_FILE_NAME = "TestData\\PersonalInfo\\AdminUser.properties";
    private static final String SAUCE_LAB_CRED_FILE = "Driver.properties";
    private static final ThreadLocal<Driver> driver = new ThreadLocal<Driver>();
    private static Credentials superAdmin;
    private static ThreadLocal<SystemUser> systemAdmin = new ThreadLocal<>();

    @BeforeSuite
    public void initSuperAdminUsers(){
        PropertiesResourceManager rm = new PropertiesResourceManager(SYSTEM_USER_FILE_NAME);

        superAdmin = Credentials.Create(rm.getPropertyValueByKey("SuperAdmin"),
                rm.getPropertyValueByKey("SuperAdminPassword"));
    }

    @BeforeTest
    public void initSystemAdminUsers(){
        PropertiesResourceManager rm = new PropertiesResourceManager(SYSTEM_USER_FILE_NAME);
        Random rand = new Random();

        systemAdmin.set(new SystemUser(Role.valueOf(rm.getPropertyValueByKey("SystemAdminRole")),
                UserStatus.valueOf(rm.getPropertyValueByKey("SystemAdminStatus")),
                String.format("%s %s %s", rm.getPropertyValueByKey("SystemAdminFirstName"),
                        rm.getPropertyValueByKey("SystemAdminMiddleName")+rand.nextInt(1000),
                        rm.getPropertyValueByKey("SystemAdminLastName")),
                rm.getPropertyValueByKey("SystemAdmin")+rand.nextInt(1000),
                rm.getPropertyValueByKey("SystemAdminPassword")));
    }

    public Credentials getSuperAdmin(){
        return superAdmin;
    }

    public SystemUser getSystemAdmin(){
        return systemAdmin.get();
    }

    @BeforeMethod
    @Parameters({"browser", "runMode"})
    public void setUp(ITestContext context, String browserType, String runMode)
    {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
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
