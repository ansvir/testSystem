package servlet;

import dao.RoleDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import dao.UserDAO;
import factory.ActionFactory;
import logic.command.Command;
import org.apache.log4j.Logger;
import resource.ConfigurationManager;
import resource.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TestSystemController extends HttpServlet {

    private final static Logger log = Logger.getLogger(TestSystemController.class);

    @Override
    public void init() {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("Enter Controller processRequest method");
        HttpSession session = request.getSession();
        session.setAttribute("roles", new RoleDAO().findAll());
        session.setAttribute("users", new UserDAO().findAll());
        session.setAttribute("subjects", new SubjectDAO().findAll());
        session.setAttribute("tests", new TestDAO().findAll());
        request.setAttribute("operationSuccess", null);
        session.setAttribute("viewUsers", new UserDAO().findAllUsersAndRoles());

        ActionFactory client = new ActionFactory();
        Command command = client.defineCommand(request);
        String page = command.execute(request, session);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}
