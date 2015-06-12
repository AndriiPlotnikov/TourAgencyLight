/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.jdbc;

import dao.interfaces.DAOFlight;
import entities.Flight;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author andre
 */
public class JDBCDAOFlight implements DAOFlight{
    private final static String FIND_ALL_FLIGHTS = "Select fly.id, company.name, fly.departure_date, " +
        " fly.arrival_date, fly.from, fly.destination, fly.departed " +
        "from flights as fly " +
        "inner join air_travel_company as company " +
        "on (fly.company_id = company.id)";
    private final static String FIND_ALL_FLIGHTS_AVAILABLE = "Select fly.id, company.name, fly.departure_date, " +
        " fly.arrival_date, fly.from, fly.destination, fly.departed " +
        "from flights as fly " +
        "inner join air_travel_company as company " +
        "on (fly.company_id = company.id) where fly.departed = 0";
    private final static String FIND_FLIGHT_BY_ID = "Select fly.id, company.name, fly.departure_date, " +
        " fly.arrival_date, fly.from, fly.destination, fly.departed " +
        "from flights as fly " +
        "inner join air_travel_company as company " +
        "on (fly.company_id = company.id) " +
        "where fly.id = ";
    private Connection con;

    
    
    @Override
    public void create(Flight flight) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Flight flight) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Flight flight) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Flight find(int id) {
        Flight flight = null;
        con = JdbcConnection.getInstance().getConnection();
        try (Statement query = con.createStatement()) {
            ResultSet rs = query.executeQuery(FIND_FLIGHT_BY_ID + id);
            if (rs.next()) {
                flight = new Flight(rs.getInt(1) , rs.getString(2),
                        rs.getDate(3), rs.getDate(4),rs.getString(5), 
                        rs.getString(6), new Boolean(rs.getString(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).error(ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(getClass().getName()).error(ex);
            }
        }
        return flight;

    }

    @Override
    public List<Flight> findAll() {
        List<Flight> flights = new LinkedList<>();
        con = JdbcConnection.getInstance().getConnection();
        try (Statement query = con.createStatement()) {
            ResultSet rs = query.executeQuery(FIND_ALL_FLIGHTS);
            while (rs.next()) {
                flights.add(new Flight(rs.getInt(1) , rs.getString(2),
                        rs.getDate(3), rs.getDate(4),rs.getString(5), 
                        rs.getString(6), new Boolean(rs.getString(7))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).error(ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(getClass().getName()).error(ex);
            }
        }
        return flights;
    }

    @Override
    public List<Flight> findAllAvailable() {
        List<Flight> flights = new LinkedList<>();
        con = JdbcConnection.getInstance().getConnection();
        try (Statement query = con.createStatement()) {
            ResultSet rs = query.executeQuery(FIND_ALL_FLIGHTS_AVAILABLE);
            while (rs.next()) {
                flights.add(new Flight(rs.getInt(1) , rs.getString(2),
                        rs.getDate(3), rs.getDate(4),rs.getString(5), 
                        rs.getString(6), new Boolean(rs.getString(7))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).error(ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(getClass().getName()).error(ex);
            }
        }
        return flights;
    }
    
    
    
}
