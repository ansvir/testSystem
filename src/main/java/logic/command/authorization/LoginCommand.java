package logic.command.authorization;

import dao.PermissionDAO;
import entity.Permission;
import logic.logic.authorization.LoginLogic;
import logic.command.Command;
import org.apache.log4j.Logger;
import resource.ConfigurationManager;
import resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginCommand implements Command {

    private final static Logger log = Logger.getLogger(LoginCommand.class);

    public String execute(HttpServletRequest request, HttpSession session) {
        String page;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (LoginLogic.checkLogin(username, password)) {
            log.debug("credentials are correct");
            session.setAttribute("username", username);
            page = ConfigurationManager.getProperty("path.page.main");
            request.setAttribute("passedAuth", true);
            List<Permission> permissions = new PermissionDAO().findPermissionsByUsername(username);
            session.setAttribute("currentPermissions", permissions);
        } else {
            log.debug("credentials are incorrect");
            request.setAttribute("passedAuth", false);
            request.setAttribute("msg", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
