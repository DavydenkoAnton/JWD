package by.davydenko.petbook.controller.command.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class URLCreator {

    private URLCreator() {
    }

    public static String create(HttpServletRequest request) {
        String url = "";

        Enumeration<String> paramNames = request.getParameterNames();

        String paramName;
        String paramValue;

        while(paramNames.hasMoreElements()) {
            paramName = paramNames.nextElement();

            paramValue = request.getParameter(paramName);
            url = url + paramName + "=" + paramValue + "&";
        }

        url = request.getRequestURL() + "?" + url;

        return url;

    }
}
