package pages;

import framework.elements.Label;
import framework.elements.Menus;
import framework.webDriverFactory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainMenuPageComponent {

    private final Driver browser;

    @FindBy(xpath="//ul[@class='oxd-main-menu']//span")
    List<WebElement> menu;

    public MainMenuPageComponent(Driver browser) {
        this.browser = browser;
        PageFactory.initElements(this.browser.getWebDriver(), this);
    }

    public void clickMenu(String menuText){
        Menus menuItem = new Menus(menu);
        menuItem.clickMenu(menuText);
    }
}
