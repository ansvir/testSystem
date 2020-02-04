package logic;

import dao.PermissionDAO;
import dao.UserDAO;
import entity.Permission;
import java.util.List;

public class Define {
    public static List<Permission> definePermissionsByRoleId(Long id) {
        return new PermissionDAO().findPermissionsByRoleId(id);
    }

    public static Long defineRoleIdByUsername(String username) {
        return new UserDAO().findByName(username).getRoleId();
    }
}
