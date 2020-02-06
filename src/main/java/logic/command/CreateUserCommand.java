package logic.command;

import dao.PermissionDAO;
import dao.RoleDAO;
import entity.Permission;
import entity.Role;
import entity.User;
import logic.CreateUserLogic;
import logic.Define;
import logic.LoginLogic;
import org.apache.log4j.Logger;
import resource.ConfigurationManager;
import resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateUserCommand implements Command {

    private final static Logger log = Logger.getLogger(CreateUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpSession session) {
        log.debug("Enter CreateUserCommand execute method");
        String page;
        Long roleId = Long.parseLong(request.getParameter("createUserRoleId"));
        String username = request.getParameter("createUserUsername");
        String password = request.getParameter("createUserPassword");
        User user = new User(new RoleDAO().findById(roleId).getId(), username, password);
        if (CreateUserLogic.createUser(user)) {
            request.setAttribute("msg", MessageManager.getProperty("message.usercreatedsuccessfully"));
        } else {
            log.debug("user wasn't created");
            request.setAttribute("msg", MessageManager.getProperty("message.errorcreateuser"));
        }
        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}
