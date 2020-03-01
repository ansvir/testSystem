package entity;

public class RoleUser {

    private Long roleId;
    private Long userId;

    public RoleUser(Long roleId, Long userId) {
        this.roleId = roleId;
        this.userId = userId;
    }

    public RoleUser() {
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RoleUser{" +
                "roleId=" + roleId +
                ", userId=" + userId +
                '}';
    }
}
