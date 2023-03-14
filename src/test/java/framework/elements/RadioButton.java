package framework.elements;

import framework.Logger;
import framework.webDriverFactory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RadioButton extends BaseElementImp implements RadiButtonSelectable{

    By radioButtonLocator = By.xpath("./preceding-sibling::input");
    By radioButtonTextLocator = By.xpath("..");
    public RadioButton(WebElement element, Driver driver) {
        super(element, driver);
    }

    public boolean setGender(String gender){
        scrollTillVisible();
        var text  = element.findElement(radioButtonTextLocator).getText();
        if (gender.equals(text)){
            Logger.getInstance().info(String.format("framework.elements.radiobutton.gender.%s.selected", gender.toLowerCase()));
            element.click();
            return true;
        }

        Logger.getInstance().warn(String.format("framework.elements.radiobutton.gender.%s.not found", gender.toLowerCase()));
        return false;
    }

    @Override
    public boolean isSelected(){
        WebElement el = element.findElement(radioButtonLocator);
        return el.isSelected();
    }

    @Override
    public String getText(){
        return element.findElement(radioButtonTextLocator).getText();
    }
}
