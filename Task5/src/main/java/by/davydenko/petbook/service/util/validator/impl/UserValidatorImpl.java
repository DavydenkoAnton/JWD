package by.davydenko.petbook.service.util.validator.impl;

import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.util.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserValidatorImpl implements UserValidator {

    private static final Logger logger = LogManager.getLogger(UserValidatorImpl.class);

    @Override
    public boolean valid(User obj) {
        return false;
    }

    @Override
    public boolean valid(HttpServletRequest request) {
        if (validLogin(request)){
            if(validPassword(request)){
                if(validName(request)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean validName(HttpServletRequest request) {
        String password = request.getParameter("name");
        if (password != null) {
            if (password.equals("")) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean validLogin(HttpServletRequest request) {
        String password = request.getParameter("login");
        if (password != null) {
            if (password.equals("")) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean validPassword(HttpServletRequest request) {
        String password = request.getParameter("password");
        if (password != null) {
            if (password.equals("")) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public String createPassword(HttpServletRequest request) {
        return request.getParameter("password");
    }

    @Override
    public boolean validId(HttpSession httpSession) {
        int id = (int) httpSession.getAttribute("id");
        if (id<0||id==0){
            return false;
        }
        return true;
    }

    @Override
    public boolean validUserCount(HttpSession session) throws ServiceException {
        String countBuff=(String)session.getAttribute("countUsers");
        try{
            int count=Integer.valueOf(countBuff);
            return true;
        }catch (NumberFormatException e){
            logger.error(getClass().getName()+" [NumberFormatException] ");
        }
    return false;
    }
}
