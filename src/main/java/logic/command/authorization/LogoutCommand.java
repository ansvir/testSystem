package logic.command.authorization;

import logic.command.Command;
import org.apache.log4j.Logger;
import resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    private final static Logger log = Logger.getLogger(LoginCommand.class);

    public String execute(HttpServletRequest request, HttpSession session) {
        log.debug("Enter LogoutCommand execute method");
        String page = ConfigurationManager.getProperty("path.page.index");
        request.getSession().invalidate();
        return page;
    }
}
