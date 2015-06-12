/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands;

import application.properties.InnerMapping;
import entities.Event;
import entities.Flight;
import entities.HotelRoom;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.facade.ModelFacade;

/**
 *
 * @author andre
 */
public class CreateTour implements Command {
    //inner mapping
    private static final String REDIRECT_FROM_FLIGHTS = "showFromFlights";    
    private static final String REDIRECT_COMEBACK_FLIGHTS = "showComebackFlights";
    private static final String REDIRECT_AVAILABLE_ROOMS = "showAvailableRooms";    
    private static final String REDIRECT_AVAILABLE_EVENTS = "showAvailableEvents";    
    private static final String REDIRECT_SUCCESFULLY_ADDED = "showAddedTourSuccess";
    
    //param from jsp
    private static final String PARAM_FROM_FLIGHT = "paramFromFlight";
    private static final String PARAM_COMEBACK_FLIGHT = "paramComebackFlight";    
    private static final String PARAM_NUMBER_OF_ROOMS = "paramNumberOfRooms";    
    private static final String PARAM_CHOSEN_EVENTS = "paramChosenEvents";
    
    //attr to session
    private static final String ATTR_FROM_FLIGHT = "attrFromFlight";
    private static final String ATTR_COMEBACK_FLIGHT = "attrComebackFlights";    
    private static final String ATTR_NUM_OF_ROOMS = "attrNumberOfRooms";    
    private static final String ATTR_CHOSEN_EVENTS = "attrChosenEvents";
    
    //attr send to jsp
    private static final String REQUEST_AVAILABLE_FLIGHTS = "requestAvailableFlights";    
    private static final String REQUEST_COMEBACK_FLIGHTS = "requestComebackFlights";    
    private static final String REQUEST_AVAILABLE_ROOMS = "requestAvailableRooms";    
    private static final String REQUEST_AVAILABLE_EVENTS = "requestAvailableEvents";
            
    //private static final String ATTRIBUTE_NAME = "roles";
    //private static final String PARAM_NAME = "login";
    

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            setIncomingParameters(request);
            String redirect = getReddirectAddress(request, response);
            request.getRequestDispatcher(redirect).forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ShowTours.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setIncomingParameters(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelFacade facade = new ModelFacade();
        //step 1
        String parameter = request.getParameter(PARAM_FROM_FLIGHT);
        if(parameter != null) {
            session.setAttribute(ATTR_FROM_FLIGHT, facade.findFlightById(parameter));
        }
        
        
        parameter = request.getParameter(PARAM_COMEBACK_FLIGHT);
        if(parameter != null) {
            session.setAttribute(ATTR_COMEBACK_FLIGHT, facade.findFlightById(parameter));
        }
        
        //step 3
        parameter = request.getParameter(PARAM_NUMBER_OF_ROOMS);
        if(parameter != null) {
            session.setAttribute(ATTR_NUM_OF_ROOMS, Integer.parseInt(parameter));
        }
        
        //step 4
        List<Event> events = new LinkedList();
        Event temp;
        String[] parameters = request.getParameterValues(PARAM_CHOSEN_EVENTS);
        
        if(parameters != null) {
            for(String param : parameters){
                int id = Integer.parseInt(param);
                temp = facade.findEventById(id);
                events.add(temp);
            }
            session.setAttribute(ATTR_CHOSEN_EVENTS, events);
        }
        
        //final step
        
        if(request.getParameter("clear") != null){
            clearAttributes(session);
        }
        
        if(request.getParameter("accept") != null){
            facade.putTourIntoDAO(
                    (Flight)session.getAttribute(ATTR_FROM_FLIGHT),
                    (Flight)session.getAttribute(ATTR_COMEBACK_FLIGHT),
                    (int)session.getAttribute(ATTR_NUM_OF_ROOMS),
                    (List<Event>)session.getAttribute(ATTR_CHOSEN_EVENTS)
            );
            clearAttributes(session);
        }
        
    }


    private String getReddirectAddress(HttpServletRequest request, HttpServletResponse response) {
            HttpSession session = request.getSession();
            ModelFacade facade = new ModelFacade();
            try{
            //step 1
            if(session.getAttribute(ATTR_FROM_FLIGHT) == null){
                request.setAttribute(REQUEST_AVAILABLE_FLIGHTS, facade.findAllAvailableFlights());
                return InnerMapping.getInstance().getProperty(REDIRECT_FROM_FLIGHTS);
            }
            
            //step 2
            if(session.getAttribute(ATTR_COMEBACK_FLIGHT) == null){
                request.setAttribute(REQUEST_COMEBACK_FLIGHTS, 
                        facade.findAllAvailableComebackFlights());
                return InnerMapping.getInstance().getProperty(REDIRECT_COMEBACK_FLIGHTS);
            }
            
            //step 3
            if(session.getAttribute(ATTR_NUM_OF_ROOMS) == null){
                int numberOfRooms = facade.findNumberOfAllAvailableRooms(
                                (Flight)session.getAttribute(ATTR_FROM_FLIGHT),
                                (Flight)session.getAttribute(ATTR_COMEBACK_FLIGHT)
                        );
                request.setAttribute(REQUEST_AVAILABLE_ROOMS, numberOfRooms);
                return InnerMapping.getInstance().getProperty(REDIRECT_AVAILABLE_ROOMS);
            }
            
            //step 4
            if(session.getAttribute(ATTR_CHOSEN_EVENTS) == null){
                request.setAttribute(REQUEST_AVAILABLE_EVENTS, facade.findAllAvailableEvents());
                return InnerMapping.getInstance().getProperty(REDIRECT_AVAILABLE_EVENTS);
            }
            
            return InnerMapping.getInstance().getProperty(REDIRECT_SUCCESFULLY_ADDED);
            } catch(IOException ioe) {
                return null;
            }
    }

    private void clearAttributes(HttpSession session) {
        session.removeAttribute(ATTR_FROM_FLIGHT);
        session.removeAttribute(ATTR_COMEBACK_FLIGHT);
        session.removeAttribute(ATTR_NUM_OF_ROOMS);
        session.removeAttribute(ATTR_CHOSEN_EVENTS);
    }

    
}
