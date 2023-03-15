package testng.tests;

import framework.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.pageComponents.MainMenuPageComponent;
import pages.PersonalInfoPage;
import pages.ProfilePicturePage;
import testng.tests.base.BaseTestWithLogin;


public class ChangeProfilePictureTest extends BaseTestWithLogin {

    private final String FILE_NAME = Constants.PICTURES_DIR+"kitty_picture.jpg";

    @Test
    public void changeProfilePictureTest(){
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
}
