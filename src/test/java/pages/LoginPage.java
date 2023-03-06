package pages;

import framework.elements.Button;
import framework.elements.Label;
import framework.elements.TextBox;
import framework.webDriverFactory.Driver;
import model.Credentials;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {
    @FindBy(xpath = "//input[@name='username']")
    private WebElement txtUserName;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement txtPassword;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnSubmit;
    @FindBy(xpath = "//div[@class='orangehrm-login-form']//p[text()='Invalid credentials']")
    private WebElement lblInvalidCred;

    private final Driver browser;

    public LoginPage(Driver browser) {
        this.browser = browser;
        PageFactory.initElements(this.browser.getWebDriver(), this);
    }

    public void login(Credentials credentials)
    {
        TextBox txtUserNameWrapped = new TextBox(txtUserName, browser);
        TextBox txtPasswordWrapped = new TextBox(txtPassword, browser);
        Button btnSubmitWrapped = new Button(btnSubmit, browser);

        txtUserNameWrapped.sendKeys(credentials.getUserName());
        txtPasswordWrapped.sendKeys(credentials.getPassword());
        btnSubmitWrapped.click();
    }

    public boolean isCredInvalid() {
        browser.getWait().until(ExpectedConditions.visibilityOf(lblInvalidCred));
        Label lblInvalidCredWrapper = new Label(lblInvalidCred, browser);
        return lblInvalidCredWrapper.isDisplayed();
    }

    public  boolean isUserNameInvalid(){
        TextBox txtUserNameWrapped = new TextBox(txtUserName, browser);
        var className = txtUserNameWrapped.getAttribute("class");
        return className.contains("oxd-input--error");
    }

    public  boolean isPasswordInvalid(){
        TextBox txtPasswordWrapped = new TextBox(txtPassword, browser);
        var className = txtPasswordWrapped.getAttribute("class");
        return className.contains("oxd-input--error");
    }
}
