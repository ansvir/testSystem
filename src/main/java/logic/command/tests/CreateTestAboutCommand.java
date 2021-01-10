package logic.command.tests;

import dao.PermissionDAO;
import dao.RoleDAO;
import dao.RoleUserDAO;
import dao.SubjectDAO;
import entity.*;
import logic.command.Command;
import logic.logic.authorization.LoginLogic;
import logic.logic.tests.CreateTestLogic;
import logic.logic.users.CreateUserLogic;
import org.apache.log4j.Logger;
import resource.ConfigurationManager;
import resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CreateTestAboutCommand implements Command {

    private final static Logger log = Logger.getLogger(CreateTestAboutCommand.class);

    public String execute(HttpServletRequest request, HttpSession session) {
        log.debug("Enter CreateTestAboutCommand execute method");
        Long subjectId = Long.parseLong(request.getParameter("createTestSubjectId"));
        String testName = request.getParameter("createTestName");
        String createTestDescription = request.getParameter("createTestDescription");
        ArrayList<Question> createTestQuestions = new ArrayList<Question>();

        ArrayList<Choice> createTestAnswers = new ArrayList<Choice>();
        ArrayList<QuestionChoice> questionChoices = new ArrayList<>();
        ArrayList<QuestionAnswer> questionAnswers = new ArrayList<>();
        String page = ConfigurationManager.getProperty("path.page.main");
        return page;

//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        if (LoginLogic.checkLogin(username, password)) {
//            log.debug("credentials are correct");
//            session.setAttribute("username", username);
//            page = ConfigurationManager.getProperty("path.page.main");
//            request.setAttribute("passedAuth", true);
//            List<Permission> permissions = new PermissionDAO().findPermissionsByUsername(username);
//            session.setAttribute("currentPermissions", permissions);
//        } else {
//            log.debug("credentials are incorrect");
//            request.setAttribute("passedAuth", false);
//            request.setAttribute("msg", MessageManager.getProperty("message.loginerror"));
//            page = ConfigurationManager.getProperty("path.page.login");
//        }
//        return page;
    }
}
