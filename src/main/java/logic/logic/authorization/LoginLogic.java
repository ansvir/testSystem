package logic.logic.authorization;

import dao.UserDAO;
import entity.Permission;
import entity.User;
import org.apache.log4j.Logger;

public class LoginLogic {

    private final static Logger log = Logger.getLogger(LoginLogic.class);

    public static boolean checkLogin(String username, String password) {
        log.debug("Enter LoginLogic checkLogin method");
        UserDAO dao = new UserDAO();
        User user = dao.findByName(username);
        if (user == null) {
            log.warn("User is null");
            return false;
        }

        return user.getPassword().equals(password);
    }
}
