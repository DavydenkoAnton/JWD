package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class ProfilePageCommand implements Command {
    private static Logger logger = LogManager.getLogger(ProfilePageCommand.class);
    private static final String USER_PROFILE_PAGE = "/WEB-INF/jsp/user/profile.jsp";
    private static final String ADMIN_PROFILE_PAGE = "/WEB-INF/jsp/admin/profile.jsp";
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute(Attribute.ROLE).equals(Role.USER)) {
            forwardToUserProfilePage(request, response);
        } else if (request.getSession().getAttribute(Attribute.ROLE).equals(Role.ADMIN)) {
            forwardToAdminProfilePage(request, response);
        }else{
            forwardToErrorPage(request,response);
        }
    }

    private void forwardToUserProfilePage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(USER_PROFILE_PAGE);
            dispatcher.forward(request, response);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        } catch (ServletException e) {
            logger.error("ServletException (not redirected)", e);
        }
    }

    private void forwardToAdminProfilePage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(ADMIN_PROFILE_PAGE);
            dispatcher.forward(request, response);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        } catch (ServletException e) {
            logger.error("ServletException (not redirected)", e);
        }
    }

    private void forwardToErrorPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(ERROR_PAGE);
            dispatcher.forward(request, response);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        } catch (ServletException e) {
            logger.error("ServletException (not redirected)", e);
        }
    }

}