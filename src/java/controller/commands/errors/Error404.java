/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.errors;

import controller.commands.Command;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andre
 */
public class Error404 implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            //response.sendError(404);
            response.sendRedirect(request.getContextPath()+"/errors/404.jsp");
        } catch (IOException ex) {
            Logger.getLogger(Error404.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
