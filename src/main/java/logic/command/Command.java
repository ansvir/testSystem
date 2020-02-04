package logic.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface Command {
    String execute(HttpServletRequest request, HttpSession session);
}
