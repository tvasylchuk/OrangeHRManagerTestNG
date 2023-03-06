package framework.elements;

import framework.webDriverFactory.Driver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class Label extends BaseElementImp {
    public Label(WebElement element, Driver driver) {
        super(element, driver);
    }
}
