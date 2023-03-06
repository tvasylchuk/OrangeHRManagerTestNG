package pages;

import framework.elements.TextBox;
import framework.webDriverFactory.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class BasePage {
    protected Driver browser;
    @FindBy(xpath="//div[@id='app']")
    WebElement appForm;

    public BasePage(Driver browser) {
        this.browser = browser;
        PageFactory.initElements(browser.getWebDriver(), this);
    }

    public void waitTillPageLoaded()
    {
        try
        {
            browser.getWait().until((ExpectedCondition<Boolean>) input ->
                (Boolean)appForm.isEnabled()
            );
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
