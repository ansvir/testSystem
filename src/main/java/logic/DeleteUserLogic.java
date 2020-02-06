package logic;

import dao.UserDAO;
import entity.User;
import org.apache.log4j.Logger;

public class DeleteUserLogic {

    private final static Logger log = Logger.getLogger(DeleteUserLogic.class);

    public static boolean deleteUser(User user) {
        log.debug("Enter DeleteUserLogic createUser method");
        return new UserDAO().delete(user);
    }
}
