package pl.CarRally.carrally.User;

public enum UserRoleType {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    final String name;
    UserRoleType(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
