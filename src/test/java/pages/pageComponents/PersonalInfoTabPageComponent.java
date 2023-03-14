package pages;

import framework.elements.Label;
import framework.webDriverFactory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalInfoTabPageComponent {

    private final Driver browser;

    private By TabLocator = By.xpath("//div[@role='tab']");

    @FindBy(xpath="//div[@role='tablist']")
    private WebElement personalInfoTab;

    @FindBy(xpath = "//div[contains(@class, 'orangehrm-horizontal-padding')]/child::h6")
    private WebElement title;

    @FindBy(xpath = "//div[@class='orangehrm-edit-employee']//div[@class='orangehrm-edit-employee-image']/img")
    private WebElement profilePicture;

    public PersonalInfoTabPageComponent(Driver browser){
        this.browser = browser;
        PageFactory.initElements(this.browser.getWebDriver(), this);
    }

    public Label getPITab(String tabName){
        var elements = personalInfoTab.findElements(TabLocator);
        for (WebElement el:elements) {
            if (el.getText().equals(tabName))
            {
                return new Label(el, browser);
            }
        }

        return null;
    }

    public void selectPictureTab(){
        Label lbPicture = new Label(profilePicture, browser);
        lbPicture.click();
    }

    public void selectTab(String tabName){
        var tab = getPITab(tabName);
        tab.click();
    }

    public String getTitle(){
        Label lbTitle = new Label(title, browser);
        lbTitle.waitUntilVisible();
        return lbTitle.getText();
    }
}
