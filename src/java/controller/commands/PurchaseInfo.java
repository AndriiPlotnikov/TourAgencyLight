/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands;

import application.properties.InnerMapping;
import dao.interfaces.DAOFactory;
import entities.TourPurchase;
import org.apache.log4j.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.facade.ModelFacade;

/**
 *
 * @author andre
 */
public class PurchaseInfo implements Command{
    private static final String PURCHASE_INNER_MAPPING    = "PurchaseInfo";
    private static final String PURCHASE_ATTRIBUTE   = "Purchase";
    private static final String REQUEST_PROPERTY = "id";
    private static final String STATUS_PROPERTY = "newStatus";
    
    

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter(REQUEST_PROPERTY));
            String status = request.getParameter(STATUS_PROPERTY);
            ModelFacade facade = new ModelFacade();
            TourPurchase purchase = facade.findPurchaseById(id);

            if(status != null){
                purchase.setStatus("confirmed");
                DAOFactory.getFactory().getDAOTourPurchase().update(purchase);
            }

            request.setAttribute(PURCHASE_ATTRIBUTE, purchase);

            String showTours = InnerMapping.getInstance().getProperty(PURCHASE_INNER_MAPPING);
            request.getRequestDispatcher(showTours).forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(PurchaseInfo.class.getName()).error(ex) ;
        }
    }
   
}
