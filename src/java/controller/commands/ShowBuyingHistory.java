/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands;

import application.properties.InnerMapping;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.facade.ModelFacade;
import org.apache.log4j.Logger;

/**
 * all purchases made 
 * 
 * @author andre
 */
public class ShowBuyingHistory implements Command{

    private static final String BUYING_HISTORY_PROPERTY = "ShowBuyingHistory";
    private static final String BUYING_ATTRIBUTE = "BuyingAttribute";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ModelFacade facade = new ModelFacade();
            String showTours = InnerMapping.getInstance().getProperty(BUYING_HISTORY_PROPERTY);
            request.setAttribute(BUYING_ATTRIBUTE, facade.findAllPurchases());
            request.getRequestDispatcher(showTours).forward(request, response);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(ShowBuyingHistory.class.getName()).error(ex);
        }
    }
    
    
}
