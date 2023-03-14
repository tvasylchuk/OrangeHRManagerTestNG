package pages;

import framework.Logger;
import framework.elements.Button;
import framework.elements.Label;
import framework.webDriverFactory.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.pageComponents.EmployeeFormPage;
import pages.pageComponents.PersonalInfoTabPageComponent;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class ProfilePicturePage extends EmployeeFormPage {

    @FindBy(xpath = "//div[@class='orangehrm-employee-picture']//img[@class='employee-image']")
    WebElement picture;

    @FindBy(xpath="//div[@class='orangehrm-employee-picture']//button")
    WebElement addPicture;

    @FindBy(xpath = "//div[@class='orangehrm-edit-employee-content']/div[contains(@class, 'orangehrm-horizontal-padding')]//button[@type='submit']")
    private WebElement save;

    public final PersonalInfoTabPageComponent piTab;

    public ProfilePicturePage(Driver browser){
        super(browser);
        piTab = new PersonalInfoTabPageComponent(browser);
        assert browser.getBrowserUri().contains("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPhotograph");
    }

    public void uploadPicture(String path){
        Button btnAddPicture = new Button(addPicture, browser);
        btnAddPicture.click();
        Logger.getInstance().info("pages.ProfilePicturePage.uploadPicture.addPicture.button.click");

        uploadFileWithRobot (path);
        Logger.getInstance().info("pages.ProfilePicturePage.uploadPicture.addPicture.file.added: "+path);
        Label lbPicture = new Label(picture, browser);
        lbPicture.waitUntilVisible();
        Logger.getInstance().info("pages.ProfilePicturePage.uploadPicture.addPicture.file.uploading.completed");
    }

    public boolean isProfilePictureDefault(){
        try{
            Label lbPicture = new Label(picture, browser);
            lbPicture.waitUntilVisible();
            var source = lbPicture.getAttribute("src");
            return source.contains("user-default-400.png");
        }
        catch(Exception e){
            Logger.getInstance().error("pages.savePersonalDataChanges.isProfilePictureDefault.failed");
            Logger.getInstance().error(e.getMessage());
            Logger.getInstance().error(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }
    }

    public void savePersonalDataChanges(){
        Button btnSave = new Button(save, browser);
        btnSave.scrollTillVisible();
        btnSave.click();
        Logger.getInstance().info("pages.ProfilePicturePage.savePersonalDataChanges.save");
        waitActionSuccessUpdateComplete();
        assert browser.getBrowserUri().contains("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPhotograph");
    }

    private void uploadFileWithRobot (String imagePath) {
        try
        {
            StringSelection stringSelection = new StringSelection(imagePath);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            Robot robot = new Robot();
            robot.delay(250);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(150);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
        catch (Exception e) {
            Logger.getInstance().error(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }
    }
}
