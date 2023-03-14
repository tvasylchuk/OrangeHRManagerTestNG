package model;

import java.util.Date;

public class EmployeeDetails {
    private final Nationality nationality;
    private final Date dateOfBirth;
    private final Gender gender;
    private final Smoker isSmoked;

    public EmployeeDetails(Nationality nationality, Date dateOfBirth, Gender gender, Smoker isSmoked){
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.isSmoked = isSmoked;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public Smoker getIsSmoked() {
        return isSmoked;
    }
}
