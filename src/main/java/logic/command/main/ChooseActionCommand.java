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
            case "createtest": {
                page = ConfigurationManager.getProperty("path.page.tests.createTestAbout");
                log.debug("Redirect to " + page);
                return page;
            }
            case "tat": {
                page = ConfigurationManager.getProperty("path.page.users.takeTestAbout");
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
