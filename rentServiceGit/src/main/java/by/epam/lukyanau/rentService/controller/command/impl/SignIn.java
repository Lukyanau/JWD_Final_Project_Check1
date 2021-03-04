package by.epam.lukyanau.rentService.controller.command.impl;

import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.entity.User;
import by.epam.lukyanau.rentService.exception.IncorrectSignInParametersException;
import by.epam.lukyanau.rentService.exception.ServiceException;
import by.epam.lukyanau.rentService.service.impl.UserServiceImpl;
import com.mysql.cj.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignIn implements Command {
    public UserServiceImpl userService = UserServiceImpl.getInstance();

    //private final static Logger LOGGER = LogManager.getLogger();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            String login = request.getParameter("username");
            String password = request.getParameter("password");
            User currentUser = userService.signInUser(login, password);
            if (currentUser != null) {
                session.setAttribute("sessionRole",currentUser.getRole());
                session.setAttribute("sessionUser",currentUser);
                response.sendRedirect("Controller?command=gotohomepage&message=You successfully sign in");
            }
        } catch (ServiceException | IncorrectSignInParametersException exp) {
            //LOGGER.error(exp.getMessage());
            exp.printStackTrace();
        }
    }
}
