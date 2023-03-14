package framework.elements;

import framework.webDriverFactory.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class TextBox extends BaseElementImp{
    public TextBox(WebElement element, Driver driver) {
        super(element, driver);
    }

    public String getValue(){
        JavascriptExecutor jse = (JavascriptExecutor)driver.getWebDriver();
        String str = (String) jse.executeScript("return arguments[0].value", element);
        return str;
    }
}
