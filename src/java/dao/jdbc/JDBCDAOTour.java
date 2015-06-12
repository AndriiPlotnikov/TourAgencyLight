/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.jdbc;

import dao.interfaces.DAOTour;
import entities.Tour;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * JDBC implementation of DAOTour
 * 
 * @author andre
 */
public class JDBCDAOTour implements DAOTour {
    private final static String FIND_ALL_TOURS = "select * from tour";
    private final static String FIND_ALL_TOURS_BY_TYPE = "select * from tour where type = ?";
    private final static String FIND_TOUR_BY_ID = "select * from tour where id = ?";
    private final static String CREATE_TOUR = "INSERT INTO `touragency`.`tour` "
            + "(`name`, `departure`, `arrival`, `type`, `places_left`, `price`, `min_price`, description) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    /**
     * create tour and assign new id to tour
     * @param tour to be created 
     */
    @Override
    public void create(Tour tour) {
        
        try( Connection con = JdbcConnection.getInstance().getConnection();
                PreparedStatement statement = con.prepareStatement(CREATE_TOUR, Statement.RETURN_GENERATED_KEYS)){
            
            statement.setString(1 , tour.getName());
            statement.setInt(2 , tour.getArrival().getId() );
            statement.setInt(3 , tour.getDeparture().getId());
            statement.setString(4 , tour.getType());
            statement.setInt(5, tour.getPlacesLeft());
            statement.setInt(6, tour.getPrice());
            statement.setInt(7, tour.getMinPrice());
            statement.setString(8 , tour.getDescription());
            statement.execute();
            
            try (ResultSet key = statement.getGeneratedKeys()) {
                int itemId;
                if( key.next() ){
                    itemId = key.getInt(1);
                    tour.setId(itemId);
                }
            }
            
        }catch( Exception ex ){
            Logger.getLogger(getClass().getName()).error(ex);
        }
    }

    @Override
    public void update(Tour tour) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Tour tour) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tour find(int id) {
        Tour tour = null;
        
        try( Connection con = JdbcConnection.getInstance().getConnection();
                PreparedStatement statement = con.prepareStatement(FIND_TOUR_BY_ID)){
            statement.setInt(1 , id);            
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                tour = new Tour(rs.getInt(1) ,
                        rs.getString(2), 
                        JDBCDAOFactory.getFactory().getDAOFlight().find(rs.getInt(3)),
                        JDBCDAOFactory.getFactory().getDAOFlight().find(rs.getInt(4)),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getBoolean(9),
                        rs.getString(10)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).error(ex);
        } 
        
        return tour;

    }

    @Override
    public List<Tour> findAll() {
        List<Tour> tours = new ArrayList<>();
        
        try (Connection con = JdbcConnection.getInstance().getConnection();
                Statement query = con.createStatement()) {
            ResultSet rs = query.executeQuery(FIND_ALL_TOURS);
            while (rs.next()) {
                tours.add(new Tour(rs.getInt(1) ,
                        rs.getString(2), 
                        JDBCDAOFactory.getFactory().getDAOFlight().find(rs.getInt(3)),
                        JDBCDAOFactory.getFactory().getDAOFlight().find(rs.getInt(4)),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getBoolean(9),
                        rs.getString(10)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).error(ex);
        } 
        
        return tours;

    }

    /**
     * find all tours with given type
     * @param tourType type of tour
     * @return all tours of type
     */
    @Override
    public List<Tour> findAllByType(String tourType) {
        List<Tour> tours = new ArrayList<>();
        
        try(Connection con = JdbcConnection.getInstance().getConnection();
                PreparedStatement statement = con.prepareStatement(FIND_ALL_TOURS_BY_TYPE)){
            statement.setString(1 , tourType);
            
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                tours.add(new Tour(rs.getInt(1) ,
                        rs.getString(2), 
                        JDBCDAOFactory.getFactory().getDAOFlight().find(rs.getInt(3)),
                        JDBCDAOFactory.getFactory().getDAOFlight().find(rs.getInt(4)),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getBoolean(9),
                        rs.getString(10)
                ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).error(ex);
        } 
        
        return tours;
    }
}
