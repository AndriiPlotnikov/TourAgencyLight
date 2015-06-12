/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands;

import application.properties.InnerMapping;
import dao.interfaces.DAOFactory;
import entities.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author andre
 */
public class RegisterCommand implements Command {
        
    private static final String REDIRECT = "settingsRegisterUser";    
    private static final String ATTRIBUTE_NAME = "roles";
    private static final String PARAM_NAME = "login";
    private static final String PARAM_PASS = "password";
    private static final String PARAM_CONFIRM_PASS = "confirm";
    private static final String PARAM_ROLE = "role";
    private static final String PARAM_DISCOUNT = "discount";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String msg = null;
            if(validate(request, response)){
                String name     = request.getParameter(PARAM_NAME);
                String pass     = request.getParameter(PARAM_PASS);
                String role     = request.getParameter(PARAM_ROLE);
                int discountPercent = Integer.parseInt(request.getParameter(PARAM_DISCOUNT));
                
                if(!validateDiscount(discountPercent)){
                    discountPercent = 0;
                }
                
                request.setAttribute(PARAM_NAME, name);
                request.setAttribute(PARAM_PASS, pass);
                request.setAttribute(PARAM_ROLE, role);
                
                User user = new User(0,name, pass, role, discountPercent);
                DAOFactory.getFactory().getDAOUser().create(user);
                msg = "created user";
                
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("/confirmUserCreation.jsp").forward(request, response);
                
            } else {
                String jsp = InnerMapping.getInstance().getProperty(REDIRECT);
                request.getRequestDispatcher(jsp).forward(request, response);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).error(ex);
        }
    }

    private boolean validate(HttpServletRequest request, HttpServletResponse response) {
        if(request.getParameter(PARAM_NAME) == null ||
                request.getParameter(PARAM_PASS) == null ||
                request.getParameter(PARAM_CONFIRM_PASS) == null ||
                request.getParameter(PARAM_ROLE) == null ||
                request.getParameter(PARAM_DISCOUNT) == null ||
                !request.getParameter(PARAM_PASS).equals(request.getParameter(PARAM_CONFIRM_PASS)) ){
            return false;
        }
        return true;
    }

    private boolean validateDiscount(int discountPercent) {
        if(discountPercent < 0 && discountPercent > 100){
            return false;
        }
        return true;
    }
}
