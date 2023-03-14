package pages;

import framework.Logger;
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
        assert browser.getBrowserUri().equals("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    public void login(Credentials credentials)
    {
        TextBox txtUserNameWrapped = new TextBox(txtUserName, browser);
        TextBox txtPasswordWrapped = new TextBox(txtPassword, browser);
        Button btnSubmitWrapped = new Button(btnSubmit, browser);

        txtUserNameWrapped.sendKeys(credentials.getUserName());
        Logger.getInstance().info("pages.loginPage.UserName.set.text: "+credentials.getUserName());
        txtPasswordWrapped.sendKeys(credentials.getPassword());
        Logger.getInstance().info("pages.loginPage.Password.set.text: "+credentials.getPassword());
        btnSubmitWrapped.click();
        Logger.getInstance().info("pages.loginPage.Submit.click");
    }

    public boolean isCredInvalid() {
        browser.getWait().until(ExpectedConditions.visibilityOf(lblInvalidCred));
        Label lblInvalidCredWrapper = new Label(lblInvalidCred, browser);
        Logger.getInstance().info("pages.loginPage.isCredInvalid.invalidCredentialsError.displayed."+lblInvalidCredWrapper.isDisplayed());
        return lblInvalidCredWrapper.isDisplayed();
    }

    public  boolean isUserNameInvalid(){
        TextBox txtUserNameWrapped = new TextBox(txtUserName, browser);
        var className = txtUserNameWrapped.getAttribute("class");
        Logger.getInstance().info("pages.loginPage.isUserNameInvalid.cssClass.contains.error."+className.contains("oxd-input--error"));
        return className.contains("oxd-input--error");
    }

    public  boolean isPasswordInvalid(){
        TextBox txtPasswordWrapped = new TextBox(txtPassword, browser);
        var className = txtPasswordWrapped.getAttribute("class");
        Logger.getInstance().info("pages.loginPage.isPasswordInvalid.cssClass.contains.error."+className.contains("oxd-input--error"));
        return className.contains("oxd-input--error");
    }
}
