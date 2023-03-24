package testng.tests;

import framework.Constants;
import framework.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.pageComponents.MainMenuPageComponent;
import pages.PersonalInfoPage;
import pages.ProfilePicturePage;
import testng.tests.base.BaseTestWithLogin;


public class ChangeProfilePictureTest extends BaseTestWithLogin {

    private final String FILE_NAME = Constants.PICTURES_DIR+"kitty_picture.jpg";

    public void changeProfilePictureTest(){
        try{
            var menu = new MainMenuPageComponent(getDriver());
            menu.clickMenu("My Info");
            var myInfoPage = new PersonalInfoPage(getDriver());
            myInfoPage.piTab.selectPictureTab();
            var picturePage = new ProfilePicturePage(getDriver());
            picturePage.uploadPicture(FILE_NAME);
            getDriver().waitPageToLoad();
            Assert.assertFalse(picturePage.isProfilePictureDefault());
            picturePage.savePersonalDataChanges();
            Assert.assertTrue(picturePage.isProfilePictureDefault());
        }
        catch(Exception e)
        {
            Logger.getInstance().error(e.getMessage());
            Logger.getInstance().error(e.getStackTrace().toString());
            throw new RuntimeException(e);
        }
    }
}
