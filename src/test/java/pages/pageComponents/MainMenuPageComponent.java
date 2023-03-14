package pages.pageComponents;

import framework.Logger;
import framework.elements.Menus;
import framework.webDriverFactory.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainMenuPageComponent extends EmployeeFormPage{

    @FindBy(xpath="//ul[@class='oxd-main-menu']//span")
    List<WebElement> menu;

    public MainMenuPageComponent(Driver browser) {
        super(browser);
    }

    public void clickMenu(String menuText){
        Menus menuItem = new Menus(menu);
        menuItem.clickMenu(menuText);
        Logger.getInstance().info(String.format("pages.pageComponents.MainMenuPageComponent.clickMenu.%s.select", menuText));
        waitTillElementsRefreshed();
    }
}
