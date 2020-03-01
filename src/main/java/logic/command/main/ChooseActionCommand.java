package logic.command.main;

import dao.PermissionDAO;
import logic.command.Command;
import org.apache.log4j.Logger;
import resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChooseActionCommand implements Command {

    private final static Logger log = Logger.getLogger(ChooseActionCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpSession session) {
        log.debug("Enter ChooseActionCommand execute method");
        String page;
        String actionCode = request.getParameter("actionCode");
        switch (actionCode) {
            case "cu": {
                page = ConfigurationManager.getProperty("path.page.users.createUser");
                log.debug("Redirect to " + page);
                return page;
            }
            case "du": {
                page = ConfigurationManager.getProperty("path.page.users.deleteUser");
                log.debug("Redirect to " + page);
                return page;
            }
            case "dat": {
                page = ConfigurationManager.getProperty("path.page.users.createUser");
                log.debug("Redirect to " + page);
                return page;
            }
            case "t": {
                page = ConfigurationManager.getProperty("path.page.users.createTestAbout");
                log.debug("Redirect to " + page);
                return page;
            }
            case "d": {
                page = ConfigurationManager.getProperty("path.page.users.createUser");
                log.debug("Redirect to " + page);
                return page;
            }
            case "take": {
                page = ConfigurationManager.getProperty("path.page.tests.take.chooseCriteria");
                log.debug("Redirect to " + page);
                return page;
            }
            case "st": {
                page = ConfigurationManager.getProperty("path.page.users.createUser");
                log.debug("Redirect to " + page);
                return page;
            }
            case "st2": {
                page = ConfigurationManager.getProperty("path.page.users.createUser");
                log.debug("Redirect to " + page);
                return page;
            }
            case "vt": {
                page = ConfigurationManager.getProperty("path.page.test.view");
                log.debug("Redirect to " + page);
                return page;
            }
            case "aq": {
                page = ConfigurationManager.getProperty("path.page.tests.addQuestion");
                log.debug("Redirect to " + page);
                return page;
            }
            case "vu": {
                page = ConfigurationManager.getProperty("path.page.users.view");
                log.debug("Redirect to " + page);
                return page;
            }
            default: {
                log.debug("Page not found");
                return ConfigurationManager.getProperty("path.page.error");
            }
        }
    }
}
