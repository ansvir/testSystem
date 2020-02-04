package logic.command;

import entity.Permission;
import logic.Define;
import logic.LoginLogic;
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
            Long roleId = Define.defineRoleIdByUsername(username);
            List<Permission> definedPermissions = Define.definePermissionsByRoleId(roleId);
            session.setAttribute("currentPermissions", definedPermissions);
        } else {
            log.debug("credentials are incorrect");
            request.setAttribute("passedAuth", false);
            request.setAttribute("msg", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
