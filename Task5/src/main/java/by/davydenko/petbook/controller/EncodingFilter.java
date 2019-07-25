package by.davydenko.petbook.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * EncodingFilter реализует интерфейс Filter
 */
@WebFilter("/EncodingFilter")
public class EncodingFilter implements Filter {

    private static Logger logger = LogManager.getLogger(EncodingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
        }
        try {
            chain.doFilter(request, response);
        } catch (IOException | ServletException e) {
            logger.error(e);
        }
    }

    @Override
    public void destroy() {}
}
