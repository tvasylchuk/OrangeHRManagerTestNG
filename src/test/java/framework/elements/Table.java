package framework.elements;

import framework.webDriverFactory.Driver;
import model.user.SystemUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Table extends BaseElementImp{
    private final By recordLocator = By.xpath(".//div[@class='oxd-table-card']");
    private List<TableRow> rows;

    private Table(WebElement element, Driver driver) {
        super(element, driver);
    }

    public static Table initTable(WebElement element, Driver driver){
        Table table = new Table(element, driver);
        var elements = table.element.findElements(table.recordLocator);
        table.rows = new ArrayList<>();
        for (WebElement el: elements) {
            table.rows.add(TableRow.initTableRow(el, table.driver));
        }

        return table;
    }

    public Integer getRecordCount(){
        return rows.size();
    }

    public List<TableRow> getRows(){
        return rows;
    }

    public TableRow getRowByUserData(SystemUser user){
        for (TableRow row: rows) {
            var tmp = row.getRowTextData();
            if (row.getRowTextData().get("Username").getText().equals(user.getUserName()) &&
                  //  row.getRowTextData().get("Employee Name").getText().equals(user.getName()) &&
                    row.getRowTextData().get("Role").getText().equals(user.getRole().toString()) &&
                    row.getRowTextData().get("Status").getText().equals(user.getStatus().toString())){
                return row;
            }
        }
        return null;
    }
}
