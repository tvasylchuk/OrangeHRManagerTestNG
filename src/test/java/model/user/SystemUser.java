package model.user;

public class SystemUser {
    private final Role role;
    private final UserStatus status;
    private final String name;
    private final String userName;
    private final String password;

    public SystemUser(Role role, UserStatus status, String name, String userName, String password) {
        this.role = role;
        this.status = status;
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
