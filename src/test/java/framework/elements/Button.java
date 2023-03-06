package framework.elements;

import framework.webDriverFactory.Driver;
import org.openqa.selenium.WebElement;

import java.util.Date;

public class Button extends BaseElementImp{
    public Button(WebElement element, Driver driver) {
        super(element, driver);
    }
}
