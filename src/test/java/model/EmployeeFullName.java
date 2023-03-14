package model;

public class EmployeeFullName {

    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickname;

    public EmployeeFullName(String firstName, String middleName, String lastName, String nickname){
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickname = nickname;
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
