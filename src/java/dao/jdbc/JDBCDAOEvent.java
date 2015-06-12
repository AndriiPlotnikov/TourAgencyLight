/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.jdbc;

import dao.interfaces.DAOEvent;
import entities.Event;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
/**
 * JDBC implementation of DAOEvent
 * 
 * @author andre
 */
public class JDBCDAOEvent implements DAOEvent{
    private final static String FIND_ALL_AVAILABLE_EVENTS = "select * from events";
    private final static String FIND_EVENT_BY_ID = "select * from events where id = ? ";
     
    private Connection con;
    
    @Override
    public void create(Event event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Event event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Event event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * find by id
     * @param id of event
     * @return event found
     */
    @Override
    public Event find(int id) {
        Event event = null;
        con = JdbcConnection.getInstance().getConnection();
        try(PreparedStatement statement = con.prepareStatement(FIND_EVENT_BY_ID)){
            statement.setInt(1 , id);            
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                event = new Event(rs.getInt(1) , rs.getString(2),
                rs.getString(3),rs.getInt(4),rs.getString(5));
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
        return event;
    }

    /**
     * find all events
     * @return all events
     */
    @Override
    public List<Event> findAll() {
        List<Event> events = new LinkedList<>();
        con = JdbcConnection.getInstance().getConnection();
        try (Statement query = con.createStatement()) {
            ResultSet rs = query.executeQuery(FIND_ALL_AVAILABLE_EVENTS);
            while (rs.next()) {
                events.add(new Event(rs.getInt(1) , rs.getString(2),
                rs.getString(3),rs.getInt(4),rs.getString(5)));
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
        return events;
    }
    
}
