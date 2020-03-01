package logic.command.tests;

import entity.User;
import logic.logic.users.CreateUserLogic;
import logic.command.Command;
import org.apache.log4j.Logger;
import resource.ConfigurationManager;
import resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddQuestionCommand implements Command {

    private final static Logger log = Logger.getLogger(AddQuestionCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpSession session) {
        log.debug("Enter AddQuestionCommand execute method");
        String page;
        if (request.getParameter("createTestSubjectId") != null) {
            Long subjectId = Long.parseLong(request.getParameter("createTestSubjectId"));
        }

        Long roleId = Long.parseLong(request.getParameter("createUserRoleId"));
        String username = request.getParameter("createUserUsername");
        String password = request.getParameter("createUserPassword");
        User user = new User(username, password);
        if (CreateUserLogic.createUser(user, roleId)) {
            request.setAttribute("msg", MessageManager.getProperty("message.usercreatedsuccessfully"));
        } else {
            log.debug("user wasn't created");
            request.setAttribute("msg", MessageManager.getProperty("message.errorcreateuser"));
        }
        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}
