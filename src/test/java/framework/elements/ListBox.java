package framework.elements;

import framework.webDriverFactory.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class ListBox extends BaseElementImp implements ItemSelectable {
    private By optionLocator = By.xpath("./div[@role='option']/span");

    public ListBox(WebElement element, Driver driver) {
        super(element, driver);
    }

    public boolean setValue(String value){
        scrollTillVisible();
        var options = element.findElements(optionLocator);
        for (var item : options) {
            if (item.getText().equals(value)){
                Actions action = new Actions(driver.getWebDriver());
                action.moveToElement(element).scrollToElement(item).pause(200).click(item).pause(200).perform();
                return true;
            }
        }

        return false;
    }
}
