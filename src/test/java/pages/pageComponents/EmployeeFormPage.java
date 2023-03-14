package pages.pageComponents;

import framework.Logger;
import framework.elements.Button;
import framework.elements.Label;
import framework.webDriverFactory.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmployeeFormPage {

    protected final Driver browser;

    @FindBy(xpath = "//div[@id='oxd-toaster_1']//p[text()='Success']")
    private WebElement successMsg;

    @FindBy(xpath = "//div[@id='oxd-toaster_1']//p[text()='Successfully Updated']")
    private WebElement updateSuccessMsg;

    @FindBy(xpath = "//div[@id='oxd-toaster_1']//p[text()='Successfully Saved']")
    private WebElement savedSuccessMsg;

    @FindBy(xpath = "//div[@id='oxd-toaster_1']//p[text()='Successfully Deleted']")
    private WebElement deletedSuccessMsg;

    @FindBy(xpath = "//div[@class='oxd-loading-spinner']")
    private WebElement loadSpinner;

    @FindBy(xpath="//div[@class='oxd-form-loader']")
    private WebElement formLoader;

    @FindBy(xpath = "//div[@class='oxd-dialog-container-default']//button[text()=' No, Cancel ']")
    private WebElement cancel;

    @FindBy(xpath = "//div[@class='oxd-dialog-container-default']//button[text()=' Yes, Delete ']")
    private WebElement delete;

    public EmployeeFormPage(Driver browser){
        this.browser = browser;
        PageFactory.initElements(this.browser.getWebDriver(), this);
    }

    public void waitActionSuccessUpdateComplete(){
        Label txtSaveSuccess = new Label(successMsg, browser);
        Label txtUpdateSuccess = new Label(updateSuccessMsg, browser);

        txtSaveSuccess.waitUntilVisible();
        assert txtSaveSuccess.isDisplayed();
        Logger.getInstance().info("pages.pageComponents.EmployeeFormPage.waitActionSuccessUpdateComplete.Success.msg.displayed");
        assert txtUpdateSuccess.isDisplayed();
        Logger.getInstance().info("pages.pageComponents.EmployeeFormPage.waitActionSuccessUpdateComplete.UpdateSuccess.msg.displayed");

        browser.getWait().until(ExpectedConditions.invisibilityOf(successMsg));
        Logger.getInstance().info("pages.pageComponents.EmployeeFormPage.waitActionSuccessUpdateComplete.Success.msg.hidden");
        browser.getWait().until(ExpectedConditions.invisibilityOf(updateSuccessMsg));
        Logger.getInstance().info("pages.pageComponents.EmployeeFormPage.waitActionSuccessUpdateComplete.UpdateSuccess.msg.hidden");
    }

    public void waitActionSuccessSavedComplete(){
        Label txtSaveSuccess = new Label(successMsg, browser);
        Label txtUpdateSuccess = new Label(savedSuccessMsg, browser);

        txtSaveSuccess.waitUntilVisible();
        assert txtSaveSuccess.isDisplayed();
        Logger.getInstance().info("pages.pageComponents.EmployeeFormPage.waitActionSuccessSavedComplete.Success.msg.displayed");
        assert txtUpdateSuccess.isDisplayed();
        Logger.getInstance().info("pages.pageComponents.EmployeeFormPage.waitActionSuccessSavedComplete.SavedSuccess.msg.displayed");

        browser.getWait().until(ExpectedConditions.invisibilityOf(successMsg));
        Logger.getInstance().info("pages.pageComponents.EmployeeFormPage.waitActionSuccessSavedComplete.Success.msg.hidden");
        browser.getWait().until(ExpectedConditions.invisibilityOf(savedSuccessMsg));
        Logger.getInstance().info("pages.pageComponents.EmployeeFormPage.waitActionSuccessSavedComplete.SavedSuccess.msg.hidden");
    }

    public void waitTillElementsRefreshed(){
        Label lbLoadSpinner =  new Label(loadSpinner, browser);
        lbLoadSpinner.waitUntilVisible();
        assert lbLoadSpinner.isDisplayed();
        Logger.getInstance().info("pages.pageComponents.EmployeeFormPage.waitTillElementsLoaded.spinner.displayed");

        lbLoadSpinner.waitUntilInvisible();
        Logger.getInstance().info("pages.pageComponents.EmployeeFormPage.waitTillElementsLoaded.spinner.hidden");
    }

    public void waitTillFormLoaded(){
        Label lbLoader =  new Label(formLoader, browser);
        lbLoader.waitUntilInvisible();
        Logger.getInstance().info("pages.pageComponents.EmployeeFormPage.waitTillElementsLoaded.spinner.hidden");
    }

    public void waitTillElementsDeleted(){
        Label txtSaveSuccess = new Label(successMsg, browser);
        Label txtDeletedSuccess = new Label(deletedSuccessMsg, browser);

        txtSaveSuccess.waitUntilVisible();
        assert txtSaveSuccess.isDisplayed();
        Logger.getInstance().info("pages.pageComponents.EmployeeFormPage.waitTillElementsDeleted.Success.msg.displayed");
        assert txtDeletedSuccess.isDisplayed();
        Logger.getInstance().info("pages.pageComponents.EmployeeFormPage.waitTillElementsDeleted.DeletedSuccess.msg.displayed");

        browser.getWait().until(ExpectedConditions.invisibilityOf(successMsg));
        Logger.getInstance().info("pages.pageComponents.EmployeeFormPage.waitTillElementsDeleted.Success.msg.hidden");
        browser.getWait().until(ExpectedConditions.invisibilityOf(deletedSuccessMsg));
        Logger.getInstance().info("pages.pageComponents.EmployeeFormPage.waitTillElementsDeleted.DeletedSuccess.msg.hidden");
    }

    public void simpleDialogCancel(){
        Button btnCancel = new Button(cancel, browser);
        btnCancel.click();
        Logger.getInstance().info("pages.pageComponents.EmployeeFormPage.simpleDialogCancel.cancel.button.click");
    }

    public void SimpleDialogConfirmRemoval(){
        Button btnDelete = new Button(delete, browser);
        btnDelete.click();
        Logger.getInstance().info("pages.pageComponents.EmployeeFormPage.SimpleDialogConfirmRemoval.delete.button.click");
    }
}
