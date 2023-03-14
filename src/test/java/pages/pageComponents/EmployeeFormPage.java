package pages;

import framework.webDriverFactory.Driver;

public class EmployeeFormPage {

    protected final Driver browser;

    protected final String saveLocator = "//div[@class='orangehrm-edit-employee-content']/div[contains(@class, 'orangehrm-horizontal-padding')]//button[@type='submit']";

    public final PersonalInfoTabPageComponent piTab;

    public EmployeeFormPage(Driver browser){
        this.browser = browser;
        piTab = new PersonalInfoTabPageComponent(browser);
    }
}
