package framework.elements;

import framework.webDriverFactory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComboBox extends BaseElementImp implements ItemSelectable {
    private final By listBoxLocator = By.xpath("./ancestor::div[@class='oxd-select-wrapper']//div[@role='listbox']");

    private final By autocompleteListLocator = By.xpath("./ancestor::div[@class='oxd-autocomplete-wrapper']//div[@role='listbox']");

    public ComboBox(WebElement element, Driver driver) {
        super(element, driver);
    }

    private ListBox initSimpleList(){
        return new ListBox(element.findElement(listBoxLocator), driver);
    }

    private ListBox initAutoCompleteList(){
        return new ListBox(element.findElement(autocompleteListLocator), driver);
    }

    @Override
    public boolean setValue(String value) {
        element.click();
        ListBox list = initSimpleList();
        return list.setValue(value);
    }

    @Override
     public void sendKeys(CharSequence... keysToSend){
        element.sendKeys(keysToSend);
        ListBox list = initAutoCompleteList();
        list.setValue(Arrays.toString(keysToSend));
    }

    public void setValueFromKeyboard(String value){
        var action = new Actions(driver.getWebDriver());
        action.moveToElement(element).perform();
        action.keyDown(value.substring(0,1)).pause(200).keyUp(value.substring(0,1)).perform();
        List<String> availableValues = new ArrayList<>();
        while(!element.getText().equals(value)){
            availableValues.add(element.getText());
            action.keyDown("u").pause(200).keyUp("u").perform();
            if (availableValues.contains(element.getText())){
                throw new RuntimeException("Invalid value: "+value);
            }
        }
    }
}
