package pages;

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
        PageFactory.initElements(this.browser.getWebDriver(), ToolBarMenuPageComponent.class);
    }
}
