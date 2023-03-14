package framework.elements;

import framework.webDriverFactory.Driver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Menus {
    List<WebElement> menuItems;
    public Menus(List<WebElement> elements) {
        this.menuItems = elements;
    }
}
