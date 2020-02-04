package logic;

import dao.UserDAO;
import entity.User;
import org.apache.log4j.Logger;

public class CreateUserLogic {

    private final static Logger log = Logger.getLogger(CreateUserLogic.class);

    public static boolean createUser(User user) {
        log.debug("Enter CreateUserLogic createUser method");
        return new UserDAO().save(user);
    }
}
