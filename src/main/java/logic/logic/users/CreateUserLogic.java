package logic.logic.users;

import dao.RoleUserDAO;
import dao.UserDAO;
import entity.Role;
import entity.RoleUser;
import entity.User;
import org.apache.log4j.Logger;

public class CreateUserLogic {

    private final static Logger log = Logger.getLogger(CreateUserLogic.class);

    public static boolean createUser(User user, Long roleId) {
        log.debug("Enter CreateUserLogic createUser method");
        if (!new UserDAO().save(user)) {
            log.error("User wasn't saved");
            return false;
        };
        Long userId = new UserDAO().findByName(user.getUsername()).getId();
        RoleUser roleUser = new RoleUser();
        roleUser.setUserId(userId);
        roleUser.setRoleId(roleId);
        if (!new RoleUserDAO().save(roleUser)) {
            log.error("Assignment wasn't saved");
            return false;
        }

        return true;
    }
}
