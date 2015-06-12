/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.jdbc;

import dao.interfaces.DAOEvent;
import dao.interfaces.DAOFactory;
import dao.interfaces.DAOFlight;
import dao.interfaces.DAOHotel;
import dao.interfaces.DAOHotelRoom;
import dao.interfaces.DAOHotelRoomReservation;
import dao.interfaces.DAOTour;
import dao.interfaces.DAOTourPurchase;
import dao.interfaces.DAOUser;

/**
 * jdbc dao factory
 * @author andre
 */
public class JDBCDAOFactory extends DAOFactory{

    @Override
    public DAOUser getDAOUser(){
       return new JDBCDAOUser();
    }

    @Override
    public DAOHotel getDAOHotel(){
       return new JDBCDAOHotel();
    }

    @Override
    public DAOHotelRoom getDAOHotelRoom() {
        return new JDBCDAOHotelRoom();
    }

    @Override
    public DAOHotelRoomReservation getDAOHotelRoomReservation() {
        return new JDBCDAOHotelRoomReservation();
    }

    @Override
    public DAOFlight getDAOFlight() {
        return new JDBCDAOFlight();
    }

    @Override
    public DAOEvent getDAOEvent() {
        return new JDBCDAOEvent();
    }

    @Override
    public DAOTour getDAOTour() {
        return new JDBCDAOTour();
    }

    @Override
    public DAOTourPurchase getDAOTourPurchase() {
        return new JDBCDAOTourPurchase();
    }

    
}
