package framework.elements;

import framework.webDriverFactory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;

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
}
