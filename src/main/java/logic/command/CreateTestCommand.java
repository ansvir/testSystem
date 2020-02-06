package logic.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateTestCommand implements Command {
    public String execute(HttpServletRequest request, HttpSession session) {
        return "";
    }
}
