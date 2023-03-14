package testng.tests;

import framework.Logger;
import framework.Constants;
import framework.webDriverFactory.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        Logger.getInstance().error("***** Error "+result.getName().trim()+" test has failed *****");
        ITestContext context = result.getTestContext();
        Driver driver = (Driver)context.getAttribute("browser");

        var file = takeScreenshot(driver.getWebDriver());
        Logger.loggerInstance.logScreenshot(file);
    }

    public void onFinish(ITestContext context) {}

    public void onTestStart(ITestResult result) {   }

    public void onTestSuccess(ITestResult result) {   }

    public void onTestSkipped(ITestResult result) {   }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {   }

    public void onStart(ITestContext context) {   }

    private File takeScreenshot(WebDriver driver) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss.SSS").format(Calendar.getInstance().getTime());
        File screenShotFile = new File(Constants.SCREENSHOT_DIR+driver.getTitle()+"_"+timeStamp+".png");

        try {
            screenShotFile.getParentFile().mkdirs();
            screenShotFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try{
            byte[] scrImage = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(screenShotFile));
            outputStream.write(scrImage);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        return screenShotFile;
    }
}
