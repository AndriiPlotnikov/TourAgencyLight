/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands;

import application.properties.InnerMapping;
import java.io.IOException;
import javax.servlet.ServletException;
import org.apache.log4j.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.facade.ModelFacade;

/**
 * display all tours on a page
 * 
 * @author andre
 */
public class ShowTours implements Command{
    
    private static final String TOURS_PROPERTY = "ShowTours";
    private static final String TOURS_ATTRIBUTE = "Tours";
    private static final String REQUEST_TOUR_PROPERTY = "tourType";
    private static final String ANY_TOUR_PROPERTY = "any";
    private static final String TOUR_TYPE = "tourType";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ModelFacade facade = new ModelFacade();
            String showTours = InnerMapping.getInstance().getProperty(TOURS_PROPERTY);
            String tourType = request.getParameter(REQUEST_TOUR_PROPERTY);
            if( tourType != null && !tourType.equals(ANY_TOUR_PROPERTY) ){
                request.setAttribute(TOUR_TYPE, tourType);
                request.setAttribute(TOURS_ATTRIBUTE, facade.findAllAvailableToursByType(tourType));
            } else {
                request.setAttribute(TOURS_ATTRIBUTE, facade.findAllAvailableTours());
            }
            request.getRequestDispatcher(showTours).forward(request, response);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(ShowTours.class.getName()).error(ex);
        }
    }
    
}
