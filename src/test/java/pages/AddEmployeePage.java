package pages;

import framework.Logger;
import framework.elements.Button;
import framework.elements.TextBox;
import framework.webDriverFactory.Driver;
import model.EmployeeFullName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.w3c.dom.Text;
import pages.pageComponents.EmployeeFormPage;

public class AddEmployeePage extends EmployeeFormPage {

    @FindBy(xpath = "//input[@name='firstName']")
    WebElement firstName;

    @FindBy(xpath = "//input[@name='middleName']")
    WebElement middleName;

    @FindBy(xpath = "//input[@name='lastName']")
    WebElement lastName;

    @FindBy(xpath="//label[text()='Employee Id']/../following-sibling::div//input")
    WebElement employeeId;

    @FindBy(xpath = "//button[@type='submit' and text()=' Save ']")
    WebElement save;

    public AddEmployeePage(Driver browser) {
        super(browser);
        assert browser.getBrowserUri().contains("https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee");
        waitTillFormLoaded();
    }

    public void createEmployee(EmployeeFullName emp){
        TextBox txtFirstName = new TextBox(firstName, browser);
        TextBox txtMiddleName = new TextBox(middleName, browser);
        TextBox txtLastName = new TextBox(lastName, browser);
        TextBox txtEmployeeId = new TextBox(employeeId, browser);
        Button btnSave = new Button(save, browser);

        txtFirstName.sendKeys(emp.getFirstName());
        Logger.getInstance().info(String.format("pages.AddEmployeePage.createEmployee.setFirstName.%s.set", emp.getFirstName().toLowerCase()));

        txtMiddleName.sendKeys(emp.getMiddleName());
        Logger.getInstance().info(String.format("pages.AddEmployeePage.createEmployee.setMiddleName.%s.set", emp.getMiddleName().toLowerCase()));

        txtLastName.sendKeys(emp.getLastName());
        Logger.getInstance().info(String.format("pages.AddEmployeePage.createEmployee.setLastName.%s.set", emp.getLastName().toLowerCase()));

        if (emp.getEmployeeId() != null){
            txtEmployeeId.sendKeys(emp.getEmployeeId().toString());
            Logger.getInstance().info(String.format("pages.AddEmployeePage.createEmployee.setEmployeeId.%s.set", emp.getEmployeeId().toString().toLowerCase()));
        }

        btnSave.click();
    }
}
