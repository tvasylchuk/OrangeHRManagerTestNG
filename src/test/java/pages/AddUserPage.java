package pages;

import framework.Logger;
import framework.elements.Button;
import framework.elements.ListBox;
import framework.elements.TextBox;
import framework.webDriverFactory.Driver;
import model.user.Role;
import model.user.UserStatus;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.pageComponents.EmployeeFormPage;

public class AddUserPage extends EmployeeFormPage {
    @FindBy(xpath = "//label[text()='User Role']/../following-sibling::div//div[@class='oxd-select-text-input']")
    private WebElement userRole;

    @FindBy(xpath = "//label[text()='User Role']/../following-sibling::div//div[@role='listbox']")
    private WebElement userRoles;

    @FindBy(xpath = "//label[text()='Status']/../following-sibling::div//div[@class='oxd-select-text-input']")
    WebElement userStatus;

    @FindBy(xpath = "//label[text()='Status']/../following-sibling::div//div[@role='listbox']")
    WebElement userStatuses;

    @FindBy(xpath = "//label[text()='Employee Name']/../following-sibling::div//input")
    WebElement employeeName;

    @FindBy(xpath = "//label[text()='Employee Name']/../following-sibling::div//div[@role='listbox']")
    WebElement employeeNames;

    @FindBy(xpath="//label[text()='Username']/../following-sibling::div//input")
    WebElement username;

    @FindBy(xpath = "//label[text()='Username']/../following-sibling::span")
    WebElement usernameError;

    @FindBy(xpath = "//label[text()='Password']/../following-sibling::div//input")
    WebElement password;

    @FindBy(xpath = "//label[text()='Confirm Password']/../following-sibling::div//input")
    WebElement confirmPassword;

    @FindBy(xpath = "//div[@class='oxd-form-actions']//button[@type='button']")
    WebElement cancel;

    @FindBy(xpath = "//div[@class='oxd-form-actions']//button[@type='submit']")
    WebElement save;

    public AddUserPage(Driver browser){
        super(browser);
        assert browser.getBrowserUri().equals("https://opensource-demo.orangehrmlive.com/web/index.php/admin/saveSystemUser");
    }

    public void setUserRole(Role role){
        TextBox txtUserRole = new TextBox(userRole, browser);
        txtUserRole.click();

        ListBox lsUserRole = new ListBox(userRoles, browser);
        lsUserRole.setValue(role.toString());
        Logger.getInstance().info(String.format("pages.AddUserPage.setUserRole.role.%s.set", role.toString()));
    }

    public void setUserStatus(UserStatus status){
        TextBox txtUserStatus = new TextBox(userStatus, browser);
        txtUserStatus.click();

        ListBox lsUserStatus = new ListBox(userStatuses, browser);
        lsUserStatus.setValue(status.toString());
        Logger.getInstance().info(String.format("pages.AddUserPage.setUserStatus.status.%s.set", status.toString()));
    }

    public void setEmployeeName(String name){
        TextBox txtUserStatus = new TextBox(employeeName, browser);
        txtUserStatus.sendKeys(name);

        ListBox lsUserStatus = new ListBox(employeeNames, browser);
        lsUserStatus.setValue(name);
        Logger.getInstance().info(String.format("pages.AddUserPage.setEmployeeName.employee.name.%s.set", name));
    }

    public void setUsername(String name){
        TextBox txtUsername = new TextBox(username, browser);
        txtUsername.sendKeys(name);
        browser.getWait().until(ExpectedConditions.invisibilityOf(usernameError));
        Logger.getInstance().info(String.format("pages.AddUserPage.setUsername.username.%s.set", name));
    }

    public void setPassword(String pwd){
        TextBox txtPassword = new TextBox(password, browser);
        TextBox txtConfirmPassword = new TextBox(confirmPassword, browser);
        txtPassword.sendKeys(pwd);
        txtConfirmPassword.sendKeys(pwd);
        Logger.getInstance().info(String.format("pages.AddUserPage.setPassword.password.%s.set", pwd));
    }

    public void save(){
        Button btnSave = new Button(save, browser);
        btnSave.click();
        waitActionSuccessSavedComplete();
        Logger.getInstance().info("pages.PersonalInfoPage.savePersonalDataChanges.save");
        assert browser.getBrowserUri().equals("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
    }
}
