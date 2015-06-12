/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands;

import application.properties.InnerMapping;
import entities.Tour;
import entities.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.facade.ModelFacade;
import org.apache.log4j.Logger;

/**
 * all info about one single tour
 * 
 * @author andre
 */
public class TourInfo implements Command{
    private static final String TOUR_PROPERTY    = "TourInfo";
    private static final String TOUR_ATTRIBUTE   = "Tour";
    private static final String CALCULATED_PRICE_ATTRIBUTE   = "TourPriceCalculated";
    private static final String REQUEST_PROPERTY = "id";
    private static final String REQUEST_BUY_PROPERTY = "BuyTour";
    
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        
            if(request.getParameter(REQUEST_BUY_PROPERTY) != null){
                (new DoBuy()).execute(request, response);
            }
            try {
                ModelFacade facade = new ModelFacade();
                HttpSession session = request.getSession();
                int id = Integer.parseInt(request.getParameter(REQUEST_PROPERTY));
                Tour tour = facade.findTourById(id);
                int price = tour.getPrice();
                User user;
                synchronized(session){
                    user = (User)session.getAttribute("user");
                }
                
                if(user != null){
                    //heavy math
                    int tempPrice = (int)((double)price-(double)price*(double)user.getDiscount()/100.0);
                    price = (tempPrice >tour.getMinPrice()) ? tempPrice : tour.getMinPrice();

                }

                String showTours = InnerMapping.getInstance().getProperty(TOUR_PROPERTY);
                request.setAttribute(TOUR_ATTRIBUTE, tour);
                request.setAttribute(CALCULATED_PRICE_ATTRIBUTE, price);
                request.getRequestDispatcher(showTours).forward(request, response);
            } catch (NumberFormatException | IOException | ServletException ex) {
                Logger.getLogger(getClass().getName()).error(ex);
            }
    
    }
}
