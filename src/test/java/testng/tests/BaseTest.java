package tests;

import framework.PropertiesResourceManager;
import framework.webDriverFactory.BrowserType;
import framework.webDriverFactory.Driver;
import framework.webDriverFactory.TestRunMode;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.internal.collections.Pair;
import pages.BasePage;

import java.util.Objects;

public class BaseTest {
    private static final String SAUCE_LAB_CRED_FILE = "Driver.properties";

    private static final ThreadLocal<Driver> driver = new ThreadLocal<Driver>();

    @BeforeMethod
    @Parameters({"browser", "runMode"})
    public static void setUp(ITestContext context, String browserType, String runMode)
    {
        String name  = context.getName();
        driver.set(Driver.initDriver(BrowserType.valueOf(browserType), TestRunMode.valueOf(runMode), name));
        context.setAttribute("browser", driver);
        driver.get().navigate("https://opensource-demo.orangehrmlive.com");
        driver.get().waitPageToLoad();
        driver.get().maximise();
    }
    //@Test
    public void mockTest()
    {
        var page = new BasePage(driver.get());
        page.waitTillPageLoaded();
        Assert.assertEquals(driver.get().getBrowserUri(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @AfterMethod
    public void tearDown()
    {
        driver.get().exit();
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
