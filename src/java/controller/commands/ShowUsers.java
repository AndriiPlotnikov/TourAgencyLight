/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands;

import application.properties.InnerMapping;
import dao.interfaces.DAOFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 * show all users
 * 
 * @author andre
 */
public class ShowUsers implements Command{

    private static final String USERS_PROPERTY = "ShowUsers";
    private static final String USERS_ATTRIBUTE = "users";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String showTours = InnerMapping.getInstance().getProperty(USERS_PROPERTY);
            request.setAttribute(USERS_ATTRIBUTE, DAOFactory.getFactory().getDAOUser().findAll());
            request.getRequestDispatcher(showTours).forward(request, response);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(getClass().getName()).error(ex);
        }
    }
}
