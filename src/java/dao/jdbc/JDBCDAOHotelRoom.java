/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.jdbc;

import dao.interfaces.DAOHotelRoom;
import entities.HotelRoom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class JDBCDAOHotelRoom implements DAOHotelRoom{

    private final static String FIND_ROOM_BY_HOTEL_ID = "select * from hotel_room where hotel = ";
    private final static String FIND_ROOM_BY_ID = "select * from hotel_room where id = ";
    private final static String FIND_ROOMS = "select * from hotel_room";
    private Connection con;
    
    @Override
    public void create(HotelRoom room) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(HotelRoom room) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(HotelRoom room) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HotelRoom find(int id) {
        HotelRoom room = null;
        con = JdbcConnection.getInstance().getConnection();
        try (Statement query = con.createStatement()) {
            ResultSet rs = query.executeQuery(FIND_ROOM_BY_ID + id);
            if (rs.next()) {
                room = new HotelRoom(rs.getInt(1) , rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(JDBCDAOHotel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return room;

    }

    @Override
    public List<HotelRoom> findAll() {
        List<HotelRoom> hotels = new LinkedList<>();
        con = JdbcConnection.getInstance().getConnection();
        try (Statement query = con.createStatement()) {
            ResultSet rs = query.executeQuery(FIND_ROOMS);
            while (rs.next()) {
                hotels.add(new HotelRoom(rs.getInt(1) , rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(JDBCDAOHotel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return hotels;
    }

    @Override
    public List<HotelRoom> findAllbyHotel(int hotelId) {
        List<HotelRoom> hotels = new LinkedList<>();
        con = JdbcConnection.getInstance().getConnection();
        try (Statement query = con.createStatement()) {
            ResultSet rs = query.executeQuery(FIND_ROOM_BY_HOTEL_ID + hotelId);
            while (rs.next()) {
                hotels.add(new HotelRoom(rs.getInt(1) , rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(JDBCDAOHotel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return hotels;

    }
    
}
