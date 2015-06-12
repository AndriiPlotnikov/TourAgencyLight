/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands;

import application.properties.InnerMapping;
import dao.interfaces.DAOFactory;
import entities.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.facade.ModelFacade;

/**
 *
 * @author andre
 */
public class EditUserInfo implements Command{

    private static final String USER_INNER_MAPPING_PROPERTY = "SettingsEditUserInfo";
    private static final String USER_ATTRIBUTE = "user";

    private static final String REQUEST_PARAMETER = "id";
    
    private static final String REQUEST_DISCOUNT_PARAMETER = "discount";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ModelFacade facade = new ModelFacade();
            int id = 0;
            if(validateId(request.getParameter(REQUEST_PARAMETER))){
                id = Integer.parseInt(request.getParameter(REQUEST_PARAMETER));
            }
            
            if(request.getParameter(REQUEST_DISCOUNT_PARAMETER) != null && validateInput(request)){
                int discount = Integer.parseInt(request.getParameter(REQUEST_DISCOUNT_PARAMETER));
                User user = DAOFactory.getFactory().getDAOUser().find(id);
                user.setDiscount(discount);
                facade.updateUser(user);
            }
            
            request.setAttribute(USER_ATTRIBUTE, DAOFactory.getFactory().getDAOUser().find(id));
            
            String showTours = InnerMapping.getInstance().getProperty(USER_INNER_MAPPING_PROPERTY);
            request.getRequestDispatcher(showTours).forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ShowTours.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean validateId(String parameter) {
        try{
            int id = Integer.parseInt(parameter);
            if(id<0){
                return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(EditUserInfo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    private boolean validateInput(HttpServletRequest request) {
        int discount = Integer.parseInt(request.getParameter(REQUEST_DISCOUNT_PARAMETER));
        return discount >= 0 && discount <= 100;
    }
    
    
}
