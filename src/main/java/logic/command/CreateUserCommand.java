package logic.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CreateUserCommand implements Command {

    private final static Logger log = Logger.getLogger(CreateUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
