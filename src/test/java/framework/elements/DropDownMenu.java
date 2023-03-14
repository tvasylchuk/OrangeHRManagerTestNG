package framework.elements;

import framework.webDriverFactory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DropDownMenu extends BaseElementImp{
    By subMenuLocator = By.xpath("./following-sibling::ul//a[@role='menuitem']");

    public DropDownMenu(WebElement element, Driver driver) {
        super(element, driver);
    }

    public void setValue(String value) {
        var menus = element.findElements(subMenuLocator);
        driver.getWait().until(ExpectedConditions.visibilityOfAllElements(menus));
        Menus menuItems = new Menus(menus);
        menuItems.clickMenu(value);
    }
}
