package framework.webDriverFactory;

import framework.PropertiesResourceManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.time.Duration;

public class Driver {
    private final static String BROWSER_FILE_NAME = "Browser.properties";
    private WebDriver _driver;
    private WebDriverWait _wait;
    private BrowserType _currentBrowser;
    private TestRunMode _mode;
    private Duration _pageLoadTimeout;
    private Duration implicitWait;
    private Duration explicitWait;

    private Driver() {
        initBrowserProperties();
    }

    public static Driver initDriver(BrowserType type, TestRunMode mode, @Nullable String testName){
        var driver = new Driver();

        driver._currentBrowser = type;
        driver._mode = mode;

        switch (type) {
            case Chrome -> driver._driver = DriverFactory.setChromeDriver(driver._mode, testName);
            case Firefox -> driver._driver = DriverFactory.setFirefoxDriver(driver._mode, testName);
            case Edge -> driver._driver = DriverFactory.setEdgeDriver(driver._mode, testName);
        }

        driver._driver.manage().timeouts().implicitlyWait(driver.implicitWait);
        driver._wait = new WebDriverWait(driver._driver, driver.explicitWait);
        return driver;
    }

    public WebDriverWait getWait() {
        return _wait;
    }

    public WebDriver getWebDriver() {
        return _driver;
    }

    public BrowserType getCurrentBrowser() {
        return _currentBrowser;
    }

    public Duration getPageLoadTimeout() {
        return _pageLoadTimeout;
    }

    public TestRunMode getTestRunMode() {
        return _mode;
    }

    public void navigate(String url) {
        _driver.navigate().to(url);
    }

    public void exit() {
        _driver.quit();
    }

    public String getBrowserUri() {
        return _driver.getCurrentUrl();
    }

    public void maximise() {
        _driver.manage().window().maximize();
    }

    public void waitPageToLoad()
    {
        WebDriverWait wait = new WebDriverWait(_driver, _pageLoadTimeout);
        try
        {
            wait.until((ExpectedCondition<Boolean>) input -> {
                if (!(input instanceof JavascriptExecutor)){
                    return true;
                }
                else {
                    Object result = (((JavascriptExecutor) input)
                            .executeScript("return document['readyState'] == 'complete' ? true : false"));
                    return result instanceof Boolean && (Boolean) result;
                }
            });
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private void initBrowserProperties()
    {
        PropertiesResourceManager rm = new PropertiesResourceManager(BROWSER_FILE_NAME);
        _pageLoadTimeout = Duration.ofSeconds(Long.parseLong(rm.getPropertyValueByKey("PageLoadTimeout", "20")));
        implicitWait = Duration.ofSeconds(Long.parseLong(rm.getPropertyValueByKey("ImplicitWait", "5")));
        explicitWait = Duration.ofSeconds(Long.parseLong(rm.getPropertyValueByKey("ExplicitWait", "5")));
    }
}
