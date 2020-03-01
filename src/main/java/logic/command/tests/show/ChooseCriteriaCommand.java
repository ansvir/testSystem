package logic.command.tests.show;

import dao.SubjectDAO;
import dao.UserDAO;
import entity.Subject;
import logic.command.Command;
import org.apache.log4j.Logger;
import resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ChooseCriteriaCommand implements Command {

    private final static Logger log = Logger.getLogger(ChooseCriteriaCommand.class);

    public String execute(HttpServletRequest request, HttpSession session) {
        log.debug("Enter ChooseCriteriaCommand execute method");
        Long subjectId = Long.parseLong(request.getParameter("subjectId"));
        Subject subject = new SubjectDAO().findById(subjectId);
        request.setAttribute("subject", subject);
        return ConfigurationManager.getProperty("path.page.tests.take.view");
    }
}
