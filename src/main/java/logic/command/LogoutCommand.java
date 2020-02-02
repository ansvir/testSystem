package logic.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {

    private final static Logger log = Logger.getLogger(LoginCommand.class);

    public String execute(HttpServletRequest request) {
        return null;
    }
}
