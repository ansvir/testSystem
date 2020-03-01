package logic.command.users;

import dao.UserDAO;
import logic.logic.users.DeleteUserLogic;
import logic.command.Command;
import org.apache.log4j.Logger;
import resource.ConfigurationManager;
import resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteUserCommand implements Command {

    private final static Logger log = Logger.getLogger(DeleteUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpSession session) {
        log.debug("Enter DeleteUserCommand execute method");
        String page;
        Long userId = Long.parseLong(request.getParameter("deleteUserId"));
        log.debug("Trying to delete user with id " + userId);
        if (DeleteUserLogic.deleteUser(new UserDAO().findById(userId))) {
            request.setAttribute("msg", MessageManager.getProperty("message.userdeletedsuccessfully"));
            request.setAttribute("operationSuccess", true);
        } else {
            log.debug("user wasn't deleted");
            request.setAttribute("msg", MessageManager.getProperty("message.errordeleteuser"));
            request.setAttribute("operationSuccess", false);
        }
        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }

}
