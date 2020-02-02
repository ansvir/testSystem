package logic.command;

import logic.LoginLogic;
import org.apache.log4j.Logger;
import resource.ConfigurationManager;
import resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

    private final static Logger log = Logger.getLogger(LoginCommand.class);

    public String execute(HttpServletRequest request) {
        String page;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (LoginLogic.checkLogin(username, password)) {
            log.debug("credentials are correct");
            request.setAttribute("username", username);
            page = ConfigurationManager.getProperty("path.page.main");
            request.setAttribute("passedAuth", true);
        } else {
            log.debug("credentials are incorrect");
            request.setAttribute("passedAuth", false);
            request.setAttribute("msg", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
