/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import dao.interfaces.DAOFactory;
import entities.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author andre
 */
@WebFilter(filterName = "Authorize", urlPatterns = {"/*"})
public class Autentication implements Filter {
    private static final String PARAM_LOGIN = "authorizationLogin";
    private static final String PARAM_LOGOUT = "authorizationLogout";
    private static final String PARAM_PASSWORD = "authorizationPassword";
    private static final String ATTRIBUTE_USER = "user";
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public Autentication() {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        HttpServletRequest httpreq = (HttpServletRequest) request;
        
        String login = null;
        String logout = null;
        String password = null;
        User user = null; 
        
        login = request.getParameter(PARAM_LOGIN);
        password = request.getParameter(PARAM_PASSWORD);
        logout = request.getParameter(PARAM_LOGOUT);
        
        if(logout != null){
            HttpSession session = httpreq.getSession();
            session.invalidate();
        }
        
        
        
        if( login != null && password != null){
            user = DAOFactory.getFactory().getDAOUser().authorize(login, password);
            if(user != null){
                HttpSession session = httpreq.getSession();
                session.setAttribute(ATTRIBUTE_USER, user);
            }
        }
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        doBeforeProcessing(request, response);
        
        chain.doFilter(request, response);
        
        doAfterProcessing(request, response);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        // do nothing
    }
    
}
