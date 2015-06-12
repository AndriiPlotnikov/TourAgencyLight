/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.jdbc;

import dao.interfaces.DAOFactory;
import dao.interfaces.DAOTourPurchase;
import entities.TourPurchase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author andre
 */
public class JDBCDAOTourPurchase implements DAOTourPurchase{
    private final static String FIND_ALL_TOUR_PURCHASES = "select * from tour_buying_history";
    private final static String FIND_PURCHASE_BY_ID = "select * from tour_buying_history where id = ?";    
    private final static String CREATE_PURCHASE = "INSERT INTO `touragency`.`tour_buying_history` (client, tour, price, discount, status) VALUES  ( ? , ? , ?, ?, ? );";
    private final static String UPDATE_PURCHASE_BY_ID = "UPDATE touragency.tour_buying_history SET client=?, tour=?, price=?, discount=?, status=? WHERE id=?";
    private Connection con;
    
    @Override
    public void create(TourPurchase tourPurchase) {
        con = JdbcConnection.getInstance().getConnection();
        try(PreparedStatement statement = con.prepareStatement(CREATE_PURCHASE, Statement.RETURN_GENERATED_KEYS)){
            
            statement.setInt(1 , tourPurchase.getClient().getId());
            statement.setInt(2 , tourPurchase.getTour().getId());
            statement.setInt(3 , tourPurchase.getPrice() );
            statement.setInt(4 , tourPurchase.getDiscount());
            statement.setString(5 , tourPurchase.getStatus());
            statement.executeUpdate();
            ResultSet key = statement.getGeneratedKeys();
            int itemId = 0;
            if( key.next() ){
                itemId = key.getInt(1);
                tourPurchase.setId(itemId);
            }
            
        }catch( Exception ex ){
            Logger.getLogger(getClass().getName()).error(ex);
        }
    }

    @Override
    public void update(TourPurchase tourPurchase) {
        con = JdbcConnection.getInstance().getConnection();
        try(PreparedStatement statement = con.prepareStatement(UPDATE_PURCHASE_BY_ID)){
            
            statement.setInt(1 , tourPurchase.getClient().getId());
            statement.setInt(2 , tourPurchase.getTour().getId());
            statement.setInt(3 , tourPurchase.getPrice());
            statement.setInt(4 , tourPurchase.getDiscount());
            statement.setString(5 , tourPurchase.getStatus());
            statement.setInt(6 , tourPurchase.getId());
            
            statement.executeUpdate();
            
        }catch( Exception ex ){
            Logger.getLogger(getClass().getName()).error(ex);
        }
    }

    @Override
    public void delete(TourPurchase tourPurchase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TourPurchase find(int id) {
        con = JdbcConnection.getInstance().getConnection();
        TourPurchase purchase=null;
        try(PreparedStatement statement = con.prepareStatement(FIND_PURCHASE_BY_ID)){
            statement.setInt(1 , id);
            
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                purchase = new TourPurchase(rs.getInt(1),
                        DAOFactory.getFactory().getDAOUser().find(rs.getInt(2)),
                        DAOFactory.getFactory().getDAOTour().find(rs.getInt(3)),
                        rs.getInt(4),
                        rs.getInt(5), 
                        rs.getString(6)
                );
            }
        }catch( Exception ex ){
            Logger.getLogger(getClass().getName()).error(ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(getClass().getName()).error(ex);
            }
        }
        return purchase;
    }

    @Override
    public List<TourPurchase> findAll() {
        List<TourPurchase> toursPurchases = new ArrayList<>();
        con = JdbcConnection.getInstance().getConnection();
        try (Statement query = con.createStatement()) {
            ResultSet rs = query.executeQuery(FIND_ALL_TOUR_PURCHASES);
            while (rs.next()) {
                toursPurchases.add(new TourPurchase(rs.getInt(1),
                        DAOFactory.getFactory().getDAOUser().find(rs.getInt(2)),
                        DAOFactory.getFactory().getDAOTour().find(rs.getInt(3)),
                        rs.getInt(4),
                        rs.getInt(5), 
                        rs.getString(6))
                );
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
        return toursPurchases;
    }
    
}
