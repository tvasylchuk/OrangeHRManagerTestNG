package framework.webDriverFactory;

import framework.Constants;
import framework.PropertiesResourceManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverFactory {
    private final static String FILE_NAME = "Driver.properties";
    private static String browserLanguage;
    private static String gridUri;
    private static String platform;
    private static String version;
    private static String sauceLabsUrl;
    private static String sauceUser;
    private static String sauceKey;

    private  static HashMap<String, Object> initPrefs()
    {
        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 2);
        prefs.put("download.default_directory", Constants.DOWNLOAD_FOLDER);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.extensions_to_open", "application/xml");
        prefs.put("safebrowsing.enabled", true);
        return prefs;
    }
    public static WebDriver setChromeDriver(Mode mode, String testName)
    {
        initBrowserArguments();
        ChromeOptions options = new ChromeOptions();

        options.setExperimentalOption("prefs", initPrefs());
        options.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));
        options.addArguments(String.format("--lang=%s", browserLanguage));
        options.addArguments("--safebrowsing-disable-download-protection");
        options.addArguments("safebrowsing-disable-extension-blacklist");

        switch (mode)
        {
            case Local -> {
                return new ChromeDriver(options);
            }
            case Grid -> {
                try {
                    return new RemoteWebDriver(new URL(gridUri), options);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
            case SauceLabs -> {
                initSauceLabsArguments();
                options.setCapability("browserVersion", version);
                options.setCapability("platformName", platform);

                Map<String, Object> sauceOptions = new HashMap<>();
                sauceOptions.put("username", sauceUser);
                sauceOptions.put("accessKey", sauceKey);
                sauceOptions.put("name", testName);
                options.setCapability("sauce:options", sauceOptions);

                try {
                    return new RemoteWebDriver(new URL(sauceLabsUrl), options);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
            default -> {
                throw new RuntimeException(mode+" is invalid test run mode.");
            }
        }
    }

    public static WebDriver setFirefoxDriver(Mode mode, String testName){
        initBrowserArguments();

        FirefoxProfile profile = new FirefoxProfile();
        FirefoxOptions options = new FirefoxOptions();

        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", Constants.DOWNLOAD_FOLDER);
        profile.setPreference("fission.webContentIsolationStrategy", 0);
        profile.setPreference("fission.bfcacheInParent", false);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/java-archive");
        profile.setPreference("intl.accept_languages",browserLanguage);
        options.setProfile(profile);

        switch (mode){
            case Local ->{
                return new FirefoxDriver(options);
            }
            case Grid -> {
                try {
                    return new RemoteWebDriver(new URL(gridUri), options);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
            case SauceLabs -> {
                initSauceLabsArguments();
                MutableCapabilities sauceOptions = new MutableCapabilities();
                sauceOptions.setCapability("username", sauceUser);
                sauceOptions.setCapability("accessKey", sauceKey);

                MutableCapabilities capabilities = new MutableCapabilities();
                capabilities.setCapability("browserName", "firefox");
                sauceOptions.setCapability("name", testName);
                capabilities.setCapability("browserVersion", version);
                capabilities.setCapability("platformName", platform);
                capabilities.setCapability("sauce:options", sauceOptions);

                try {
                    return new RemoteWebDriver(new URL(sauceLabsUrl), capabilities);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
            default -> throw new RuntimeException(mode+" is invalid test run mode.");
        }
    }

    public static WebDriver setEdgeDriver(Mode mode, String testName){
        initBrowserArguments();
        EdgeOptions options = new EdgeOptions();
        options.setExperimentalOption("prefs", initPrefs());
        options.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));

        options.addArguments(String.format("--lang=%s", browserLanguage));
        options.addArguments("--safebrowsing-disable-download-protection");
        options.addArguments("safebrowsing-disable-extension-blacklist");

        switch (mode){
            case Local -> {
                return new EdgeDriver(options);
            }
            case Grid -> {
                try {
                    return new RemoteWebDriver(new URL(gridUri), options);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
            case SauceLabs -> {
                initSauceLabsArguments();
                options.setCapability("browserVersion", version);
                options.setCapability("platformName", platform);

                Map<String, Object> sauceOptions = new HashMap<>();
                sauceOptions.put("username", sauceUser);
                sauceOptions.put("accessKey", sauceKey);
                sauceOptions.put("name", testName);
                options.setCapability("sauce:options", sauceOptions);

                try {
                    return new RemoteWebDriver(new URL(sauceLabsUrl), options);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
            default -> throw new RuntimeException(mode+" is invalid test run mode.");
        }
    }

    private static void initBrowserArguments() {
        PropertiesResourceManager rm = new PropertiesResourceManager(FILE_NAME);
        browserLanguage = rm.getPropertyValueByKey("browserLanguage");
        gridUri = rm.getPropertyValueByKey("Server");
    }

    private static void initSauceLabsArguments() {
        PropertiesResourceManager rm = new PropertiesResourceManager(FILE_NAME);
        sauceLabsUrl = rm.getPropertyValueByKey("URL");
        sauceUser = rm.getPropertyValueByKey("User");
        sauceKey = rm.getPropertyValueByKey("Key");
        platform = rm.getPropertyValueByKey("Platform");
        version = rm.getPropertyValueByKey("Version");
    }
}
