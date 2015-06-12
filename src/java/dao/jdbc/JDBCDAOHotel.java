/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.jdbc;

import dao.interfaces.DAOHotel;
import entities.Hotel;
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
public class JDBCDAOHotel implements DAOHotel {
    private final static String FIND_ALL_HOTELS = "SELECT * FROM hotel ";
    private Connection con;


    @Override
    public void create(Hotel hotel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Hotel hotel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Hotel hotel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Hotel find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Hotel> findAll() {
        List<Hotel> hotels = new LinkedList<>();
        con = JdbcConnection.getInstance().getConnection();
        try (Statement query = con.createStatement()) {
            ResultSet rs = query.executeQuery(FIND_ALL_HOTELS);
            while (rs.next()) {
                hotels.add(new Hotel(rs.getInt(1) , rs.getString(2), rs.getInt(3), null));
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
        return hotels;

    }
    
}
