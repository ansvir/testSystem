package entity;

public class User {

    private Long id;
    private Long roleId;
    private String username;
    private String password;

    public User(Long id, Long roleId, String username, String password) {
        this.id = id;
        this.roleId = roleId;
        this.username = username;
        this.password = password;
    }

    public User(Long roleId, String username, String password) {
        this.roleId = roleId;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
