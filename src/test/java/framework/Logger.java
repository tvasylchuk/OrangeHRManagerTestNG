package framework;

import framework.webDriverFactory.Driver;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;

public class Logger {
    public static Logger loggerInstance = null;
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Logger.class);

    private Logger()  {    }

    public static Logger getInstance()
    {
        if (loggerInstance == null)
        {
            loggerInstance = new Logger();
        }
        return loggerInstance;
    }

    public void error(final String message) {
        logger.error(message);
        Reporter.log("<div class=\"failedConfig\">"+message+"</div>");
    }

    public void debug(final String message) {
        logger.debug(message);
        Reporter.log("<div class=\"failedConfig\">"+message+"</div>");
    }

    public void fatal(final String message)
    {
        logger.fatal(message);
        Reporter.log("<div class=\"failedConfig\">"+message+"</div>");
        Assert.assertTrue(false);
    }

    public void warn(final String message) {
        logger.warn(message);
        Reporter.log("<div class=\"skipped\">"+message+"</div>");
    }

    public void info(final String message) {
        logger.info(message);
        Reporter.log(message);
    }

    public void logStep(final int step)
    {
        info(String.format("--------------- Step %1$s ---------------", step));
    }

    public void logTestName(final String testName)
    {
        info(String.format("--------------- test.case.%1$s ---------------", testName));
    }

    public void logClassInitialization(final String className)
    {
        info(String.format("--------------- class.%1$s has been initialized ---------------", className));
    }

    public void logScreenshot(File screenShot) {
        logger.info("Screenshot: "+screenShot.getAbsolutePath());
        Reporter.log("</br><font color='#73a9d0'>***************Screen Of the action****************</font>");
        Reporter.log("</br><img name=\"ClickResult\" src=\"" + screenShot.getAbsolutePath() + "\" style=\"width:600px\"/>");
    }
}
