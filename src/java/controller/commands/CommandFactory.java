/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands;

import application.properties.Mapping;
import controller.commands.errors.Error404;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * Factory for commands
 * 
 * @author andre
 */
public class CommandFactory {
    // singleton instance
    protected static CommandFactory instance = new CommandFactory();
    
    //cache of objects
    Map<String, Command> cache = new HashMap<>();
    
    // protected constructor prevents creating object outside
    protected CommandFactory(){    
    }
    
    // return signleton instance of factory
    public static CommandFactory getInstance(){
        return instance;
    }
    
    /**
     * create and return appropriate command
     * 
     * return class instance that can execute user request
     * @param URL relative URL for chosen web-page
     * @return 
     */
    public Command createCommand(String URL){
        try {
            Command com = cache.get(URL);
            if(com == null){
                Command temp = (Command) Class.forName(Mapping.getInstance().getProperty(URL)).newInstance();
                if(temp != null){
                    cache.put(URL, temp);
                    com = cache.get(URL);
                }
            }
            return com;
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(getClass().getName()).error(ex);
            return new Error404();
        }
    }
    
    
}
