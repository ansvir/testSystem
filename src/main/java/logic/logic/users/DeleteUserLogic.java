package logic.logic.users;

import dao.RoleUserDAO;
import dao.UserDAO;
import entity.User;
import org.apache.log4j.Logger;

public class DeleteUserLogic {

    private final static Logger log = Logger.getLogger(DeleteUserLogic.class);

    public static boolean deleteUser(User user) {
        log.debug("Enter DeleteUserLogic createUser method");
        return new RoleUserDAO().deleteByUserId(user.getId())
                && new UserDAO().delete(user);
    }
}
