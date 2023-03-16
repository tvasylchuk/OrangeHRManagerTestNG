package pages;

import framework.Logger;
import framework.webDriverFactory.Driver;
import model.EmployeeFullName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.pageComponents.EmployeeFormPage;
import pages.pageComponents.ToolBarMenuPageComponent;

public class EmployeeListPage extends EmployeeFormPage {

    @FindBy(xpath="//label[text()='Employee Name']/../following-sibling::div//input")
    WebElement employeeName;

    @FindBy(xpath = "//button[@type='submit' and text()=' Search ']")
    WebElement search;

    @FindBy(xpath="//div[@role='table']")
    WebElement employeeTable;

    private ToolBarMenuPageComponent toolBar;

    public EmployeeListPage(Driver browser) {
        super(browser);
        assert browser.getBrowserUri().contains("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
        waitTillFormLoaded();
        toolBar = new ToolBarMenuPageComponent(this.browser);
    }

    public void createEmployee(EmployeeFullName emp){
        toolBar.clickMenu("Add Employee");
        Logger.getInstance().info("pages.EmployeeListPage.createEmployee.Add Employee.menu.click");
        waitTillElementsRefreshed();

        AddEmployeePage addEmployeePage = new AddEmployeePage(browser);
        addEmployeePage.createEmployee(emp);
        waitTillElementsRefreshed();
    }

}
