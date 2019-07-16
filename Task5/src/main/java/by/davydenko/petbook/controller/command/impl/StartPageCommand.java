package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.util.URLCreator;
import by.davydenko.petbook.dao.DaoMySqlException;
import by.davydenko.petbook.entity.User;
import by.davydenko.petbook.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class StartPageCommand implements Command {

    private static final String PARAMETER_MOVIES_LIST = "moviesList";
    private static final String PARAMETER_FIRST_ROW = "allMoviesFirstRow";
    private static final String PARAMETER_LAST_ROW = "allMoviesLastRow";
    private static final String PARAMETER_NUMBER_OF_PAGES = "allMoviesNumberOfPages";
    private static final String PARAMETER_CURRENT_PAGE_NUMBER = "allMoviesCurrentPageNumber";
    private static final String PARAMETER_PREVIOUS_REQUEST = "prev_request";
    private static final Integer ERROR_NUMBER_500 = 500;

    private static final String REDIRECT_PAGE_URL = "http://localhost:8080/petBook/Servlet?command=goToStartPage";

    public static final int rowsByPage = 5;

    public static final int firstPageNumber = 1;

    public static final int defaultNumberOfPages = 1;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        String url = URLCreator.create(request);

        int firstIndex = 0;
        int lastIndex = firstIndex + rowsByPage;


        List<User> users = null;
        UserServiceImpl userService = new UserServiceImpl();

        try {
            users = userService.getUsers();
        } catch (DaoMySqlException e) {
            try {
                response.sendError(ERROR_NUMBER_500);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return;
        }

        session.setAttribute(PARAMETER_FIRST_ROW, firstIndex);
        session.setAttribute(PARAMETER_LAST_ROW, lastIndex);
        session.setAttribute("users", users);

//        int numbOfPages = (int) Math.ceil(moviesList.size() / (double)rowsByPage);
//        if(numbOfPages == 0) {
//            numbOfPages = defaultNumberOfPages;
//        }

//        session.setAttribute(PARAMETER_MOVIES_LIST, moviesList);
//        session.setAttribute(PARAMETER_NUMBER_OF_PAGES, numbOfPages);
        session.setAttribute(PARAMETER_CURRENT_PAGE_NUMBER, firstPageNumber);

        session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);

        try {
            response.sendRedirect(REDIRECT_PAGE_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
