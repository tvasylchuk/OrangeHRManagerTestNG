package testng.testDataModel;

public class PITestData {
    private final String menu;
    private final String tab;
    private final String empFirstName;
    private final String empMiddleName;
    private final String empLastName;
    private final String empNickName;
    private final String gender;
    private final String smoker;
    private final String nationality;
    private final String dateOfBirth;

    public PITestData(String menu, String tab, String empFirstName,
                      String empMiddleName, String empLastName, String empNickName,
                      String gender, String smoker, String nationality, String dateOfBirth) {
        this.menu = menu;
        this.tab = tab;
        this.empFirstName = empFirstName;
        this.empMiddleName = empMiddleName;
        this.empLastName = empLastName;
        this.empNickName = empNickName;
        this.gender = gender;
        this.smoker = smoker;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
    }

    public String getMenu() {
        return menu;
    }

    public String getTab() {
        return tab;
    }

    public String getEmpFirstName() {
        return empFirstName;
    }

    public String getEmpMiddleName() {
        return empMiddleName;
    }

    public String getEmpLastName() {
        return empLastName;
    }

    public String getEmpNickName() {
        return empNickName;
    }

    public String getGender() {
        return gender;
    }

    public String getSmoker() {
        return smoker;
    }

    public String getNationality() {
        return nationality;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

}
