package pages;

import framework.Logger;
import framework.elements.*;
import framework.webDriverFactory.BrowserType;
import framework.webDriverFactory.Driver;
import io.cucumber.java.an.E;
import model.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.pageComponents.EmployeeFormPage;
import pages.pageComponents.PersonalInfoTabPageComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PersonalInfoPage extends EmployeeFormPage {
    @FindBy(name = "firstName")
    private WebElement firstName;

    @FindBy(name = "middleName")
    private WebElement middleName;

    @FindBy(name = "lastName")
    private WebElement lastName;

    @FindBy(xpath = "//label[text()='Nickname']/../following-sibling::div//input")
    private WebElement nickname;

    @FindBy(xpath = "//label[text()='Nationality']/../following-sibling::div//div[@class='oxd-select-wrapper']//div[@class='oxd-select-text-input']")
    private WebElement nationality;

    @FindBy(xpath = "//label[text()='Nationality']/../following-sibling::div//div[@class='oxd-select-wrapper']/div[@role='listbox']")
    private WebElement nationalitiesList;

    @FindBy(xpath="//label[text()='Date of Birth']/../following-sibling::div//input")
    private WebElement dateOfBirth;

    @FindBy(xpath = "//label[text()='Gender']/../following-sibling::div//input[@type='radio']/following-sibling::span")
    private List<WebElement> gender;

    @FindBy(xpath = "//label[text()='Smoker']/../following-sibling::div//input[@type='checkbox']")
    private WebElement smoker;

    @FindBy(xpath = "//div[@class='orangehrm-edit-employee-content']/div[contains(@class, 'orangehrm-horizontal-padding')]//button[@type='submit']")
    private WebElement save;

    public PersonalInfoTabPageComponent piTab;

    public PersonalInfoPage(Driver browser){
        super(browser);
        assert browser.getBrowserUri().contains("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails");
        piTab = new PersonalInfoTabPageComponent(browser);
        waitTillFormLoaded();
    }

    public void setFullName(EmployeeFullName employee){
        TextBox txtFirstName = new TextBox(firstName, browser);
        TextBox txtMiddleName = new TextBox(middleName, browser);
        TextBox txtLastName = new TextBox(lastName, browser);
        TextBox txtNickName = new TextBox(nickname, browser);

        txtFirstName.sendKeys(employee.getFirstName());
        Logger.getInstance().info(String.format("pages.PersonalInfoPage.setFullName.setFirstName.%s.set", employee.getFirstName().toLowerCase()));

        txtMiddleName.sendKeys(employee.getMiddleName());
        Logger.getInstance().info(String.format("pages.PersonalInfoPage.setFullName.setMiddleName.%s.set", employee.getMiddleName().toLowerCase()));

        txtLastName.sendKeys(employee.getLastName());
        Logger.getInstance().info(String.format("pages.PersonalInfoPage.setFullName.setLastName.%s.set", employee.getLastName().toLowerCase()));

        txtNickName.sendKeys(employee.getNickname());
        Logger.getInstance().info(String.format("pages.PersonalInfoPage.setFullName.setNickName.%s.set", employee.getNickname().toLowerCase()));
    }

    public void selectNationality(Nationality nationalityItem){
        ComboBox txtNationality = new ComboBox(nationality, browser);
        txtNationality.click();
        if(browser.getCurrentBrowser() == BrowserType.Firefox){
            txtNationality.setValueFromKeyboard(nationalityItem.toString());
        }
        else{
            ListBox lsNationality = new ListBox(nationalitiesList, browser);
            lsNationality.setValue(nationalityItem.toString());
            Logger.getInstance().info(String.format("pages.PersonalInfoPage.selectNationality.%s.set", nationalityItem.toString().toLowerCase()));
        }
    }

    public void setDateOfBirth(Date date){
        TextBox txtDateOfBirth =  new TextBox(dateOfBirth, browser);
        txtDateOfBirth.sendKeys(date.toString());
        Logger.getInstance().info(String.format("pages.PersonalInfoPage.setDateOfBirth.%s.set", date.toString().toLowerCase()));
    }

    public void setGender(Gender item){
        for (WebElement element:gender) {
            RadioButton rbtGender =  new RadioButton(element, browser);
            if (rbtGender.setGender(item.toString()))
            {
                Logger.getInstance().info(String.format("pages.PersonalInfoPage.setGender.%s.set", item.toString().toLowerCase()));
                return;
            }
        }
        var str = String.format("pages.PersonalInfoPage.setGender.%s.not.set", item.toString().toLowerCase());
        Logger.getInstance().error(str);
        throw new RuntimeException(str);
    }

    public void setHabit(Smoker habit){
        CheckBox chbSmoker = new CheckBox(smoker, browser);
        switch (habit) {
            case False -> {
                if (chbSmoker.isSelected()) {
                    Logger.getInstance().info("pages.PersonalInfoPage.setHabit.set");
                    chbSmoker.click();
                }
            }
            case True -> {
                if (!chbSmoker.isSelected()) {
                    Logger.getInstance().info("pages.PersonalInfoPage.setHabit.unset");
                    chbSmoker.click();
                }
            }
            default -> {
                Logger.getInstance().error(String.format("pages.PersonalInfoPage.setHabit.value.invalid: %s", habit));
                throw new RuntimeException("The smoker status is not valid: " + habit);
            }
        }
    }

    public EmployeeFullName getEmployeeFullName(){
        try{
            TextBox txtFirstName = new TextBox(firstName, browser);
            TextBox txtMiddleName = new TextBox(middleName, browser);
            TextBox txtLastName = new TextBox(lastName, browser);
            TextBox txtNickName = new TextBox(nickname, browser);

            EmployeeFullName employee = new EmployeeFullName(txtFirstName.getValue(),
                    txtMiddleName.getValue(),
                    txtLastName.getValue(),
                    txtNickName.getValue());
            return employee;
        }
        catch (Exception e){
            Logger.getInstance().error("pages.PersonalInfoPage.getEmployeeFullName.failed");
            Logger.getInstance().error(e.getMessage());
            Logger.getInstance().error(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }

    }

    public Gender getGender(){
        for (WebElement element:gender) {
            RadioButton rbtGender =  new RadioButton(element, browser);
            if (rbtGender.isSelected())
            {
                return Gender.valueOf(rbtGender.getText());
            }
        }
        var str = "pages.PersonalInfoPage.getGender.error";
        Logger.getInstance().error(str);
        throw new RuntimeException(str);
    }

    public Smoker getHabit(){
        try{
            CheckBox chbSmoker = new CheckBox(smoker, browser);
            return chbSmoker.isSelected() ? Smoker.True : Smoker.False;
        }
        catch (Exception e){
            Logger.getInstance().error("pages.PersonalInfoPage.getHabit.failed");
            Logger.getInstance().error(e.getMessage());
            Logger.getInstance().error(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }
    }

    public String getBirthOfDate(){
        try{
            TextBox txtDateOfBirth =  new TextBox(dateOfBirth, browser);
            return txtDateOfBirth.getValue();
        }
        catch (Exception e){
            Logger.getInstance().error("pages.PersonalInfoPage.getBirthOfDate.failed");
            Logger.getInstance().error(e.getMessage());
            Logger.getInstance().error(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }
    }

    public Nationality getNationality(){
        try{
            TextBox txtNationality = new TextBox(nationality, browser);
            return Nationality.valueOf(txtNationality.getText());
        }
        catch (Exception e){
            Logger.getInstance().error("pages.PersonalInfoPage.getNationality.failed");
            Logger.getInstance().error(e.getMessage());
            Logger.getInstance().error(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }
    }

    public void savePersonalDataChanges(){
        Button btnSave = new Button(save, browser);
        btnSave.scrollTillVisible();
        btnSave.click();
        Logger.getInstance().info("pages.PersonalInfoPage.savePersonalDataChanges.save");
        waitActionSuccessUpdateComplete();
        assert browser.getBrowserUri().contains("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails");
    }
}
