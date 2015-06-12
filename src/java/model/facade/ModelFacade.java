/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.facade;

import dao.interfaces.DAOFactory;
import entities.Event;
import entities.Flight;
import entities.HotelRoom;
import entities.Tour;
import entities.TourPurchase;
import entities.User;
import java.util.LinkedList;
import java.util.List;

/**
 * facade implementation for whole model part
 * 
 * @author andre
 */
public class ModelFacade {

    /**
     * finds all flights that are available at current time
     * @return List of available flights 
     */
    public List<Flight> findAllAvailableFlights() {
        return DAOFactory.getFactory().getDAOFlight().findAllAvailable();
    }

    /**
     * find all flights available after chosen flight
     * 
     * @return available comeback flights
     */
    public List<Flight> findAllAvailableComebackFlights() {
        return DAOFactory.getFactory().getDAOFlight().findAllAvailable();
    }

    /**
     * find all rooms within specific time period
     * 
     * @param from - looks when tour begans
     * @param comeback - looks when it ends
     * @return list of rooms available in designed period
     */
    public List<HotelRoom> findAllAvailableRooms(Flight from, Flight comeback) {
        return DAOFactory.getFactory().getDAOHotelRoom().findAll();
    }
    
    /**
     * find all rooms within specific time period and return total number of such
     * 
     * @param from
     * @param comeback
     * @return total number of rooms available
     */
    public int findNumberOfAllAvailableRooms(Flight from, Flight comeback) {
        return DAOFactory.getFactory().getDAOHotelRoom().findAll().size();
    }

    /**
     * searches for all events available
     * @return all events available
     */
    public List<Event> findAllAvailableEvents() {
        return DAOFactory.getFactory().getDAOEvent().findAll();
    }

    /**
     * find specific flight
     * 
     * @param parameter - id, taken from request
     * @return flight found by id
     */
    public Flight findFlightById(String parameter) {
        int id = Integer.parseInt(parameter);
        return DAOFactory.getFactory().getDAOFlight().find(id);
        
    }

    /**
     * 
     * @param parameter
     * @return 
     */
    public HotelRoom findRoomByID(String parameter) {
        int id = Integer.parseInt(parameter);
        return DAOFactory.getFactory().getDAOHotelRoom().find(id);
        
    }

    public Event findEventById(int id) {
        return DAOFactory.getFactory().getDAOEvent().find(id);
    }

    public void putTourIntoDAO(Flight from, Flight comeback, int numberOfRooms, List<Event> events) {
        Tour tour = new Tour("empty",from, comeback, "notype", 1000, 750, numberOfRooms, true, null);
        DAOFactory.getFactory().getDAOTour().create(tour);
    }

    public void updateUser(User user) {
        DAOFactory.getFactory().getDAOUser().update(user);
    }

    public List<Tour> findAllAvailableTours() {
        List<Tour> tours = DAOFactory.getFactory().getDAOTour().findAll();
        tours = removeUnavailableFromList(tours);
        return tours;
    }
    
    
    public List<Tour> findAllAvailableToursByType(String tourType) {
        List<Tour> tours = DAOFactory.getFactory().getDAOTour().findAllByType(tourType);
        tours = removeUnavailableFromList(tours);
        return tours;
    }

    /**
     * repeateable action of removing unavailable tours
     * @param tours list of tours
     * @return list of tours that are available
     */
    private List<Tour> removeUnavailableFromList(List<Tour> tours) {
        List<Tour> temp = new LinkedList();
        for(Tour tour : tours){
            if(tour.isAvailable() == true && tour.getPlacesLeft() > 0 ){
                temp.add(tour);
            }
        }
        return temp;
    }

    /**
     * search for specific tour
     * 
     * @param id
     * @return tour found
     */
    public Tour findTourById(int id) {
        return DAOFactory.getFactory().getDAOTour().find(id);
    }
    
    /**
     * search for specific user
     * 
     * @param id
     * @return user found
     */
    public User findUserById(int id) {
        return DAOFactory.getFactory().getDAOUser().find(id);
    }

    /*public void addNewPurchase() {
        TourPurchase tourbuy = new TourPurchase(findUserById(4),0,0,"aquired");
        DAOFactory.getFactory().getDAOTourPurchase().create(tourbuy);
    }*/

    /**
     * place tour order!
     * @param tourId
     * @param user
     */
    public void addNewPurchase(String tourId, User user) {
        Tour tour = DAOFactory.getFactory().getDAOTour().find(Integer.parseInt(tourId));
        int price = tour.getPrice();
        int tempPrice = (int)((double)price-(double)price*(double)user.getDiscount()/100.0);
        int finalPrice = (tempPrice >tour.getMinPrice()) ? tempPrice : tour.getMinPrice();
        TourPurchase tourbuy = new TourPurchase(user,tour,tour.getPrice(),finalPrice,"aquired");
        DAOFactory.getFactory().getDAOTourPurchase().create(tourbuy);
    }

    public TourPurchase findPurchaseById(int id) {
        return DAOFactory.getFactory().getDAOTourPurchase().find(id);
    }

    public List<TourPurchase> findAllPurchases() {
        return DAOFactory.getFactory().getDAOTourPurchase().findAll();
    }

    
}
