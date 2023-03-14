package pages.pageComponents;

import framework.Logger;
import framework.elements.Label;
import framework.webDriverFactory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalInfoTabPageComponent extends EmployeeFormPage {

    private final By TabLocator = By.xpath("//div[@role='tab']");

    @FindBy(xpath="//div[@role='tablist']")
    private WebElement personalInfoTab;

    @FindBy(xpath = "//div[contains(@class, 'orangehrm-horizontal-padding')]/child::h6")
    private WebElement title;

    @FindBy(xpath = "//div[@class='orangehrm-edit-employee']//div[@class='orangehrm-edit-employee-image']/img")
    private WebElement profilePicture;

    public PersonalInfoTabPageComponent(Driver browser){
        super(browser);
    }

    public Label getPITab(String tabName){
        var elements = personalInfoTab.findElements(TabLocator);
        for (WebElement el:elements) {
            if (el.getText().equals(tabName))
            {
                Logger.getInstance().info(String.format("pages.pageComponents.PersonalInfoTabPageComponent.getPITab.tab.%s.found", tabName));
                return new Label(el, browser);
            }
        }
        Logger.getInstance().warn(String.format("pages.pageComponents.PersonalInfoTabPageComponent.getPITab.tab.%s.not.found", tabName));
        return null;
    }

    public void selectPictureTab(){
        Label lbPicture = new Label(profilePicture, browser);
        lbPicture.click();
        Logger.getInstance().info("pages.pageComponents.PersonalInfoTabPageComponent.selectPictureTab.ChangeProfilePicture.click");
    }

    public void selectTab(String tabName){
        var tab = getPITab(tabName);
        tab.click();
        Logger.getInstance().info(String.format("pages.pageComponents.PersonalInfoTabPageComponent.selectTab.%s.click", tabName));
        waitTillElementsRefreshed();
    }

    public String getTitle(){
        Label lbTitle = new Label(title, browser);
        lbTitle.waitUntilVisible();
        Logger.getInstance().info(String.format("pages.pageComponents.PersonalInfoTabPageComponent.getTitle.tab.%s.active", lbTitle.getText()));
        return lbTitle.getText();
    }
}
