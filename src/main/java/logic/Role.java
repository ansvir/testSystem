package logic;

public enum Role {
    ADMINISTRATOR("Administrator"), TUTOR("Tutor"), USER("User");

    private String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
