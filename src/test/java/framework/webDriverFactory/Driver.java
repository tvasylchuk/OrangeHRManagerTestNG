package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Driver {
    private final static String PROJECT_FOLDER = System.getProperty("user.dir");
    private final static String BROWSER_FILE_NAME = "Browser.properties";
    private final static String DEFAULT_BROWSER = "Chrome";
    private final static String DOWNLOAD_FOLDER = PROJECT_FOLDER+"Downloads/";
    private final static String TESTDATA_FOLDER = PROJECT_FOLDER+"TestData/";
    private WebDriver _driver;
    private WebDriverWait _wait;
    private final BrowserType _currentBrowser;
    private final TestRunMode _mode;
    private Duration _browserTimeout;
    private Duration _pageLoadTimeout;
    private Duration implicitWait;
    private Duration explicitWait;
    private String download_directory;

    public Driver() {
        this._currentBrowser = BrowserType.Chrome;
        this._mode = TestRunMode.Local;
    }

    public Driver(BrowserType type) {
        this._currentBrowser = type;
        this._mode = TestRunMode.Local;
    }

    public Driver(BrowserType type, TestRunMode mode) {
        this._currentBrowser = type;
        this._mode = mode;
    }

    public WebDriverWait getWait() {
        return _wait;
    }

    public BrowserType getCurrentBrowser() {
        return _currentBrowser;
    }

    public Duration getBrowserTimeout() {
        return _browserTimeout;
    }

    public Duration getPageLoadTimeout() {
        return _pageLoadTimeout;
    }

    public TestRunMode getTestRunMode() {
        return _mode;
    }

    public String getDownload_directory() {
        return download_directory;
    }
}
