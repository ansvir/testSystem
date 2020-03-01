package logic.command.redirect;

import logic.command.Command;
import logic.command.users.DeleteUserCommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EmptyCommand implements Command {

    private final static Logger log = Logger.getLogger(EmptyCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpSession session) {
        return null;
    }

}
