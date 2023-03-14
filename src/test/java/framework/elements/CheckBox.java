package framework.elements;

import framework.webDriverFactory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckBox extends BaseElementImp{

    private final By clickReceiverLocator = By.xpath("..//i");
    public CheckBox(WebElement element, Driver driver) {
        super(element, driver);
    }

    @Override
    public void click(){
        WebElement clickReceiver = element.findElement(clickReceiverLocator);
        clickReceiver.click();
    }
}
