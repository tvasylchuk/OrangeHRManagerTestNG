package framework.elements;

import framework.webDriverFactory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TableRow extends BaseElementImp{
    private final By allCellsLocator = By.xpath(".//div[@role='cell']");
    private By cellsLocator(Integer i){
        return By.xpath(String.format(".//div[@role='cell'][%s]", i));
    }
    private final By checkBoxLocator = By.xpath(".//input[@type='checkbox']/..//i");
    private final By textLocator = By.xpath("./div");
    private final By edit = By.xpath(".//i[contains(@class, 'bi-pencil-fill')]/..");
    private final By remove = By.xpath(".//i[contains(@class, 'bi-trash')]/..");
    private CheckBox checkCell;
    private HashMap<String, TextBox> cells;
    private Button editRow;
    private Button removeRow;

    private TableRow(WebElement element, Driver driver) {
        super(element, driver);
    }

    public static TableRow initTableRow(WebElement element, Driver driver){
        TableRow tableRow = new TableRow(element, driver);
        var cellsCount = tableRow.getAllCellsInRow().size();

        tableRow.checkCell = new CheckBox(tableRow.getCellInRow(1).findElement(tableRow.checkBoxLocator), tableRow.driver);
        tableRow.editRow = new Button(tableRow.getCellInRow(cellsCount).findElement(tableRow.edit), tableRow.driver);
        tableRow.removeRow = new Button(tableRow.getCellInRow(cellsCount).findElement(tableRow.remove), tableRow.driver);

        var result = new HashMap<String, TextBox>();
        result.put("Username", new TextBox(tableRow.getCellInRow(2).findElement(tableRow.textLocator), tableRow.driver));
        result.put("Role", new TextBox(tableRow.getCellInRow(3).findElement(tableRow.textLocator), tableRow.driver));
        result.put("Employee Name", new TextBox(tableRow.getCellInRow(4).findElement(tableRow.textLocator), tableRow.driver));
        result.put("Status", new TextBox(tableRow.getCellInRow(5).findElement(tableRow.textLocator), tableRow.driver));
        tableRow.cells = result;

        return tableRow;
    }

    private List<WebElement> getAllCellsInRow(){
        return(element.findElements(allCellsLocator));
    }

    private WebElement getCellInRow(Integer i){
        return element.findElement(cellsLocator(i));
    }

    public void selectRow(Boolean toSelect){
        if (toSelect) {
            if (!checkCell.isSelected()){
                checkCell.click();
            }
        }
        else{
            if (checkCell.isSelected()){
                checkCell.click();
            }
        }
    }

    public HashMap<String, TextBox> getRowTextData(){
        return cells;
    }

    public void editRow(){
        editRow.click();
    }

    public void removeRow(){
        removeRow.click();
    }
}
