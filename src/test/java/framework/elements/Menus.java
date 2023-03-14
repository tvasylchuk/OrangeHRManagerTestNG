package framework.elements;

import framework.Logger;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Menus {
    List<WebElement> menuItems;
    public Menus(List<WebElement> elements) {
        this.menuItems = elements;
    }

    public void clickMenu(String menuText){
        var menuItem = getMenuItem(menuText);
        if (menuItem!=null){
            menuItem.click();
            return;
        }

        var str = String.format("framework.elements.menus.clickMenu.%s.not.found", menuText);
        Logger.getInstance().error(str);
        throw new RuntimeException(str);
    }

    private WebElement getMenuItem(String menuText){
        for (WebElement item:menuItems) {
            var text = item.getText();
            if (text.equals(menuText))
            {
                return item;
            }
        }
        return null;
    }
}
