package logic.command;

import dao.PermissionDAO;
import dao.RoleDAO;
import entity.Permission;
import logic.Define;
import logic.LoginLogic;
import org.apache.log4j.Logger;
import resource.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChooseActionCommand implements Command{

    private final static Logger log = Logger.getLogger(ChooseActionCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpSession session) {
        log.debug("Enter ChooseActionCommand execute method");
        String page;
        Long actionId = Long.parseLong(request.getParameter("actionId"));
        if (actionId.equals(1L)) {
            page = ConfigurationManager.getProperty("path.page.createUser");
            log.debug("Redirect to " + page);
            return page;
        } else if (actionId.equals(2L)) {
            page = ConfigurationManager.getProperty("path.page.deleteUser");
            log.debug("Redirect to " + page);
            return page;
        } else if (actionId.equals(3L)) {
            page = ConfigurationManager.getProperty("path.page.createUser");
            log.debug("Redirect to " + page);
            return page;
        } else if (actionId.equals(4L)) {
            page = ConfigurationManager.getProperty("path.page.createUser");
            log.debug("Redirect to " + page);
            return page;
        } else if (actionId.equals(5L)) {
            page = ConfigurationManager.getProperty("path.page.createUser");
            log.debug("Redirect to " + page);
            return page;
        } else {
            page = ConfigurationManager.getProperty("path.page.createUser");
            log.debug("Redirect to " + page);
            return page;
        }
    }
}
