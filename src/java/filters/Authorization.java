/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import application.properties.AccessRights;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author andre
 */
@WebFilter(filterName = "CheckAccess", urlPatterns = {"/admin/*"})
public class Authorization implements Filter {
    
    private static final String ERROR_PAGE = "/errors/404.jsp";
    private static final boolean debug = false;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public Authorization() {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        
        HttpServletRequest httpreq = (HttpServletRequest) request;
        String relativeURL = httpreq.getRequestURI().toLowerCase().substring(httpreq.getContextPath().length());
        HttpSession session = httpreq.getSession();
        User user = (User)(session.getAttribute("user"));
        String role;
        
        if (user == null) {
            role = "guest";
        } else {
            role = user.getRole();
        }
        
        String urls = AccessRights.getInstance().getProperty(role);
        String[] allowedTo = urls.split(",");
        boolean allowed = false;
        for (String url : allowedTo) {
            if (url.equals(relativeURL)) {
                allowed = true;
            }
        }
        session.setAttribute("urls", role);
        session.setAttribute("relurls", allowedTo[1]);
        if (!allowed) {
            ((HttpServletResponse) response).sendRedirect(httpreq.getContextPath() + ERROR_PAGE);
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

    @Override
    public void init(FilterConfig fc) throws ServletException {
        // nothing
    }

    @Override
    public void destroy() {
        // nothing
    }
    
}
