package model;

import org.checkerframework.checker.units.qual.C;

public class Credentials {

    private final String userName;
    private final String password;

    private Credentials(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
    }

    public static Credentials Create(String userName, String password){
        return new Credentials(userName, password);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}

