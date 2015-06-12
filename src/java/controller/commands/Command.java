/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * shared interface for classes that execute user requests
 * 
 * @author Admin
 */
public interface Command{
    /**
     * executes class-specific action on user request and sends out a repsonse
     * 
     * @param request 
     * @param response 
     */
    void execute(HttpServletRequest request , HttpServletResponse response);
}