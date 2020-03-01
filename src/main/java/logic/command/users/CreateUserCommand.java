package logic.command.users;

import dao.RoleDAO;
import entity.RoleUser;
import entity.User;
import logic.logic.users.CreateUserLogic;
import logic.command.Command;
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
        User user = new User(username, password);
        if (CreateUserLogic.createUser(user, roleId)) {
            request.setAttribute("msg", MessageManager.getProperty("message.usercreatedsuccessfully"));
            request.setAttribute("operationSuccess", true);
        } else {
            log.debug("user wasn't created");
            request.setAttribute("msg", MessageManager.getProperty("message.errorcreateuser"));
            request.setAttribute("operationSuccess", false);
        }
        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}
