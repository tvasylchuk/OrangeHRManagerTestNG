package framework.elements;

import framework.webDriverFactory.Driver;
import org.openqa.selenium.WebElement;

public class TextBox extends BaseElementImp{
    public TextBox(WebElement element, Driver driver) {
        super(element, driver);
    }
}
