package logic;

import dao.UserDAO;
import entity.User;

public class LoginLogic {
    public static boolean checkLogin(String username, String password) {
        UserDAO dao = new UserDAO();
        User user = dao.findByUsername(username);
        if (user == null) {
            return false;
        }

        return user.getPassword().equals(password);
    }
}
