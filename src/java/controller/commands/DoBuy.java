/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands;

import entities.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.facade.ModelFacade;
import org.apache.log4j.Logger;

/**
 * produce goods into orders
 * 
 * @author andre
 */
public class DoBuy implements Command{
    private static final String ROLE_GUEST = "guest";
    private static final String USER_ATTRIBUTE = "user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String tourId = request.getParameter("HiddenTourId");
            HttpSession session = request.getSession();
            User user;
            synchronized(session){
                user = (User)session.getAttribute(USER_ATTRIBUTE);
            }
            if( tourId != null && user != null && !user.getRole().equals(ROLE_GUEST) ){
                ModelFacade facade = new ModelFacade();
                facade.addNewPurchase(tourId, user);
            }
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).error(ex);
        }
    }
    
}
