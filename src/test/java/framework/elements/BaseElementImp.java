package framework.elements;

import framework.webDriverFactory.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BaseElementImp implements Element{

    private final WebElement element;
    private final Driver driver;

    public BaseElementImp(final WebElement element, final Driver driver) {
        this.element = element;
        this.driver = driver;
    }

    @Override
    public void click() {
        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        js.executeScript("arguments[0].style.border='3px solid red'", element);
        element.click();
    }

    public void waitUntilVisible(WebElement element) {
        driver.getWait().until(ExpectedConditions.visibilityOf(element));
    }

    @Override
    public void submit() {
        element.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
        js.executeScript("arguments[0].style.border='3px solid red'", element);
        element.clear();
        element.sendKeys(keysToSend);
    }

    @Override
    public void clear() { element.clear();  }

    @Override
    public String getTagName() {
        return element.getTagName();
    }

    @Override
    public String getAttribute(String name) {

        return element.getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return element.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return element.isEnabled();
    }

    @Override
    public String getText() {
        return element.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return element.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return element.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return element.getLocation();
    }

    @Override
    public Dimension getSize() {
        return element.getSize();
    }

    @Override
    public Rectangle getRect() {
        return null;
    }

    @Override
    public String getCssValue(String propertyName) {
        return element.getCssValue(propertyName);
    }

    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return element.getScreenshotAs(target);
    }

    @Override
    public WebElement getWrappedElement() {
        return element;
    }
}
