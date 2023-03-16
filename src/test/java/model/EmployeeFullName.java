package model;

public class EmployeeFullName {

    private final String firstName;
    private final String middleName;
    private final String lastName;
    private String nickname;
    private Integer employeeId;

    public EmployeeFullName(String firstName, String middleName, String lastName){
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public EmployeeFullName addNickname(String nickname){
        this.nickname = nickname;
        return this;
    }

    public EmployeeFullName addEmployeeId(Integer id){
        this.employeeId = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeFullName that = (EmployeeFullName) o;
        return firstName.equals(that.firstName) &&
                middleName.equals(that.middleName) &&
                lastName.equals(that.lastName) &&
                nickname.equals(that.nickname);
    }
}
