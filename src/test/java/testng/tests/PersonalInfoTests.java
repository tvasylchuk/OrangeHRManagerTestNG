package testng.tests;

import framework.PropertiesResourceManager;
import model.EmployeeFullName;
import model.Gender;
import model.Nationality;
import model.Smoker;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.pageComponents.MainMenuPageComponent;
import pages.PersonalInfoPage;
import testng.testDataModel.PITestData;
import testng.tests.base.BaseTestWithLogin;

import java.sql.Date;

public class PersonalInfoTests extends BaseTestWithLogin {

    private final String FILE_NAME = "TestData\\PersonalInfo\\PlTest.properties";

    private PITestData testData;

    @BeforeClass
    public void initTestData(){
        PropertiesResourceManager rm= new PropertiesResourceManager(FILE_NAME);
        testData = new PITestData(
                rm.getPropertyValueByKey("Menu"), rm.getPropertyValueByKey("Tab"),  rm.getPropertyValueByKey("EmpFirstName"),
                rm.getPropertyValueByKey("EmpMiddleName"), rm.getPropertyValueByKey("EmpLastName"),  rm.getPropertyValueByKey("EmpNickName"),
                rm.getPropertyValueByKey("Gender"), rm.getPropertyValueByKey("Smoker"),  rm.getPropertyValueByKey("Nationality"),
                rm.getPropertyValueByKey("DateOfBirth"));
    }

    @Test
    public void setPersonalInfoTabTest() {
        var menuMenu = new MainMenuPageComponent(getDriver());
        menuMenu.clickMenu(testData.getMenu());
        getDriver().waitPageToLoad();

        var page = new PersonalInfoPage(getDriver());
        page.piTab.selectTab(testData.getTab());
        getDriver().waitPageToLoad();
        Assert.assertEquals(page.piTab.getTitle(), testData.getTab());
        Assert.assertTrue(getDriver().getBrowserUri().contains("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails"));

        var emp = new EmployeeFullName(testData.getEmpFirstName(), testData.getEmpMiddleName(), testData.getEmpLastName(), testData.getEmpNickName());
        page.setFullName(emp);
        page.setGender(Gender.valueOf(testData.getGender()));
        page.setHabit(Smoker.valueOf(testData.getSmoker()));
        page.setDateOfBirth(Date.valueOf(testData.getDateOfBirth()));

        page.selectNationality(Nationality.valueOf(testData.getNationality()));
        page.savePersonalDataChanges();

        Assert.assertEquals(page.getGender().toString(), testData.getGender());
        Assert.assertEquals(page.getBirthOfDate(), testData.getDateOfBirth());
        Assert.assertEquals(page.getHabit().toString(), testData.getSmoker());
        Assert.assertEquals(page.getNationality().toString(),testData.getNationality());
        Assert.assertTrue(page.getEmployeeFullName().equals(emp));
    }
}
