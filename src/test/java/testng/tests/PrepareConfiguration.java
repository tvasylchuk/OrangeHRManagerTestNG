package testng.tests;

import framework.Logger;
import model.EmployeeFullName;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.EmployeeListPage;
import pages.LoginPage;
import pages.UserManagementPage;
import pages.pageComponents.MainMenuPageComponent;
import testng.tests.base.BaseTest;

import java.util.Random;

public class PrepareConfiguration extends BaseTest {

    @Test
    public void setupUsers(){
        try{
        var basePage = new BasePage(getDriver());
            basePage.waitTillPageLoaded();

            var loginPage = new LoginPage(getDriver());
            loginPage.login(getSuperAdmin());

            var mainMenu = new MainMenuPageComponent(getDriver());
            mainMenu.clickMenu("PIM");
            getDriver().waitPageToLoad();

            Random employeeId = new Random();
            EmployeeListPage emPage = new EmployeeListPage(getDriver());
            emPage.createEmployee(convertToEmployeeFullName(getSystemAdmin().getName())
                .addEmployeeId(employeeId.nextInt(100000)));

            mainMenu = new MainMenuPageComponent(getDriver());
            mainMenu.clickMenu("Admin");
            getDriver().waitPageToLoad();

            var umPage = new UserManagementPage(getDriver());
            umPage.addNewUser(getSystemAdmin());
        }
        catch(Exception e)
        {
            Logger.getInstance().error(e.getMessage());
            Logger.getInstance().error(e.getStackTrace().toString());
            throw new RuntimeException(e);
        }
    }

    private EmployeeFullName convertToEmployeeFullName(String name){
        var fullName = name.split(" ");
        if (fullName.length == 2)
        {
            return new EmployeeFullName(fullName[0], "", fullName[1]);
        }
        else if (fullName.length == 3) {
            return new EmployeeFullName(fullName[0], fullName[1], fullName[2]);
        }

        Logger.getInstance().error("Invalid user name: "+name);
        throw new RuntimeException("Invalid user name: "+name);
    }
}


