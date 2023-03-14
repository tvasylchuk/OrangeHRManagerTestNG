package pages;

import framework.Logger;
import framework.elements.*;
import framework.webDriverFactory.Driver;
import model.user.Role;
import model.user.SystemUser;
import model.user.UserStatus;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.pageComponents.EmployeeFormPage;
import pages.pageComponents.ToolBarMenuPageComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserManagementPage extends EmployeeFormPage {
    @FindBy(xpath = "//div[@class='orangehrm-header-container']//button")
    WebElement addUser;
    @FindBy(xpath = "//div[@class='oxd-table-filter-area']//label[text()='Username']/../following-sibling::div//input")
    WebElement userName;
    @FindBy(xpath = "//div[@class='oxd-table-filter-area']//label[text()='User Role']/../following-sibling::div//div[@class='oxd-select-text-input']")
    WebElement userRole;
    @FindBy(xpath = "//div[@class='oxd-table-filter-area']//label[text()='Employee Name']/../following-sibling::div//input")
    WebElement employeeName;
    @FindBy(xpath = "//div[@class='oxd-table-filter-area']//label[text()='Status']/../following-sibling::div//div[@class='oxd-select-text-input']")
    WebElement userStatus;
    @FindBy(xpath = "//div[@class='oxd-table-filter-area']//button[@type='submit']")
    WebElement search;
    @FindBy(xpath = "//div[@class='oxd-table-filter-area']//button[@type='button']")
    WebElement reset;
    @FindBy(xpath="//div[@class='oxd-table']")
    WebElement systemUserTable;
    private ToolBarMenuPageComponent toolBar;

    public UserManagementPage(Driver browser){
        super(browser);
        toolBar = new ToolBarMenuPageComponent(this.browser);
        assert browser.getBrowserUri().equals("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
    }

    public void addNewUser(SystemUser user){
        Button btnAddUser = new Button(addUser, browser);
        btnAddUser.click();
        Logger.getInstance().info("pages.UserManagementPage.addNewUser.addUser.button.click");
        browser.waitPageToLoad();

        var addUserPage = new AddUserPage(browser);
        addUserPage.setUserRole(user.getRole());
        addUserPage.setUserStatus(user.getStatus());
        addUserPage.setEmployeeName(user.getName());
        addUserPage.setUsername(user.getUserName());
        addUserPage.setPassword(user.getPassword());

        addUserPage.save();
    }

    public void setUsernameSearchParameters(String user){
        TextBox txtUsername = new TextBox(userName, browser);
        txtUsername.sendKeys(user);
        Logger.getInstance().info("pages.UserManagementPage.setUsernameSearchParameters.set.username.parameter: "+user);
    }

    public void setRoleSearchParameters(Role role){
        ComboBox cbRole = new ComboBox(userRole, browser);
        cbRole.setValue(role.toString());
        Logger.getInstance().info("pages.UserManagementPage.setRoleSearchParameters.set.role.parameter: "+role);
    }

    public void setStatusSearchParameters(UserStatus status){
        ComboBox cbStatus = new ComboBox(userStatus, browser);
        cbStatus.setValue(status.toString());
        Logger.getInstance().info("pages.UserManagementPage.setStatusSearchParameters.set.status.parameter: "+status);
    }

    public void setEmployeeNameSearchParameters(String name){
        ComboBox cbStatus = new ComboBox(employeeName, browser);
        cbStatus.sendKeys(name);
        Logger.getInstance().info("pages.UserManagementPage.setEmployeeNameSearchParameters.set.name.parameter: "+name);
    }

    public void searchUser(){
        Button btnSearch = new Button(search, browser);
        btnSearch.click();
        Logger.getInstance().info("pages.UserManagementPage.searchUser.search.button.click");
    }

    public void resetSearch(){
        Button btnReset = new Button(reset, browser);
        btnReset.click();
        Logger.getInstance().info("pages.UserManagementPage.resetSearch.reset.button.click");
    }

    public List<SystemUser> getUsers(){
        try{
            var table = Table.initTable(systemUserTable, browser);
            var rows = table.getRows();
            List<SystemUser> result = new ArrayList<>();
            for ( TableRow row:rows) {
                var data = row.getRowTextData();
                result.add(new SystemUser(Role.valueOf(data.get("Role").getText()),
                        UserStatus.valueOf(data.get("Status").getText()),
                        data.get("Employee Name").getText(),
                        data.get("Username").getText(), ""));
            }

            return result;
        }
        catch(Exception e){
            Logger.getInstance().error("pages.UserManagementPage.getUsers.failed");
            Logger.getInstance().error(e.getMessage());
            Logger.getInstance().error(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }
    }

    public void removeUser(SystemUser user, boolean confirmAction){
        var table = Table.initTable(systemUserTable, browser);
        var row = table.getRowByUserData(user);
        row.selectRow(true);
        Logger.getInstance().info("pages.UserManagementPage.removeUser.checkbox.selected");
        row.removeRow();
        Logger.getInstance().info("pages.UserManagementPage.removeUser.remove.button.click");
        if(confirmAction){
            SimpleDialogConfirmRemoval();
        }
        else{
            simpleDialogCancel();
        }
    }
}
