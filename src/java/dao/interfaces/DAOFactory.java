/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import dao.jdbc.JDBCDAOFactory;import java.io.FileInputStream;
;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public abstract class DAOFactory {
    private static final String PROPERTY_NAME = "dao.factory";
    private static final String PATH = "D:\\javaProjects\\TestMyDAOinWeb\\src\\java\\application\\properties\\dao.properties";
    
    public abstract DAOTour getDAOTour();
    public abstract DAOTourPurchase getDAOTourPurchase();
    public abstract DAOUser getDAOUser();
    public abstract DAOEvent getDAOEvent();
    public abstract DAOHotel getDAOHotel();
    public abstract DAOFlight getDAOFlight();
    public abstract DAOHotelRoom getDAOHotelRoom();
    public abstract DAOHotelRoomReservation getDAOHotelRoomReservation();
    
    public static DAOFactory getFactory(){
        try {
            Properties config = new Properties();
            config.load( new FileInputStream(PATH));
            return (DAOFactory) Class.forName(config.getProperty(PROPERTY_NAME)).newInstance();
        } catch (Exception ex) {
            Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
