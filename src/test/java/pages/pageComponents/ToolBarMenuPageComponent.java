package pages.pageComponents;

import framework.Logger;
import framework.elements.DropDownMenu;
import framework.webDriverFactory.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ToolBarMenuPageComponent {

    private final Driver browser;

    @FindBy(xpath = "//nav[@aria-label='Topbar Menu']//descendant::li/child::*")
    List<WebElement> menu;

    public ToolBarMenuPageComponent(Driver browser){
        this.browser = browser;
        PageFactory.initElements(this.browser.getWebDriver(), this);
    }

    public void clickSubMenu(String txtMenu, String txtSubMenu){
        for (WebElement item: menu) {
            if(item.getText().equals(txtMenu)){
                item.click();
                Logger.getInstance().info(String.format("pages.pageComponents.ToolBarMenuPageComponent.clickSubMenu.%s.click", txtMenu));

                DropDownMenu subMenu = new DropDownMenu(item, browser);
                subMenu.setValue(txtSubMenu);
                subMenu.waitUntilInvisible();
                Logger.getInstance().info(String.format("pages.pageComponents.ToolBarMenuPageComponent.menu.subMenu.%s.click", txtSubMenu));
                return;
            }
        }

        var str = String.format("pages.pageComponents.clickSubMenu.%s.%s.not.found", txtMenu, txtSubMenu);
        Logger.getInstance().error(str);
        throw new RuntimeException(str);
    }

    public void clickMenu(String txtMenu){
        for (WebElement item: menu) {
            if(item.getText().equals(txtMenu)){
                item.click();
                Logger.getInstance().info(String.format("pages.pageComponents.ToolBarMenuPageComponent.clickMenu.%s.click", txtMenu));
                return;
            }
        }

        var str = String.format("pages.pageComponents.clickMenu.%s.not.found", txtMenu);
        Logger.getInstance().error(str);
        throw new RuntimeException(str);
    }
}
