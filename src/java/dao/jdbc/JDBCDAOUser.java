/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.jdbc;

import dao.interfaces.DAOUser;
import entities.User;
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
public class JDBCDAOUser implements DAOUser{
    private final static String FIND_ALL_USERS = "select * from users";
    private final static String FIND_USER_BY_ID = "select * from users where id = ?";
    private final static String AUTHORIZE_FIND_ID = "select * from users where name = ? and password=?";
    private final static String UPDATE_USER_BY_ID = "UPDATE touragency.users SET name=? , password=?, role=? , discount=? WHERE  id = ? ";
    //private final static String CREATE_USER = "INSERT INTO `touragency`.`users` (`name`, `password`, `role`) VALUES ( ? ,?, ? );";
    private Connection con;

    @Override
    public User find(int id) {
        User user = null;
        con = JdbcConnection.getInstance().getConnection();
        try(PreparedStatement statement = con.prepareStatement(FIND_USER_BY_ID)){
            statement.setInt(1 , id);            
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(1) ,
                        rs.getString(2), 
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5));
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
        return user;

    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        con = JdbcConnection.getInstance().getConnection();
        try (Statement query = con.createStatement()) {
            ResultSet rs = query.executeQuery(FIND_ALL_USERS);
            while (rs.next()) {
                users.add(new User(rs.getInt(1) ,
                        rs.getString(2), 
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5)));
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
        return users;
    }

    @Override
    public void update(User user) {
        con = JdbcConnection.getInstance().getConnection();
        try(PreparedStatement statement = con.prepareStatement(UPDATE_USER_BY_ID)){
            
            statement.setString(1 , user.getName());
            statement.setString(2 , user.getPassword() );
            statement.setString(3 , user.getRole());
            statement.setInt(4 , user.getDiscount());
            statement.setInt(5 , user.getId());
            statement.executeUpdate();
            
        }catch( Exception ex ){
            Logger.getLogger(getClass().getName()).error(ex);
        }
    } 

    @Override
    public void create(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /*con = JdbcConnection.getInstance().getConnection();
        try(PreparedStatement statement = con.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS)){
            
            statement.setString(1 , user.getName());
            statement.setInt(2 , user.getPassword() );
            statement.setInt(3 , user.getRoleId());
            statement.executeUpdate();
            ResultSet key = statement.getGeneratedKeys();
            int itemId = 0;
            if( key.next() ){
                itemId = key.getInt(1);
                user.setId(itemId);
            }
            
        }catch( Exception ex ){
            Logger.getLogger(JDBCDAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    @Override
    public User authorize(String login, String password) {
        con = JdbcConnection.getInstance().getConnection();
        User user = null;
        try(PreparedStatement statement = con.prepareStatement(AUTHORIZE_FIND_ID)){
            statement.setString(1 , login);
            statement.setString(2 , password );
            
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                user = new User(rs.getInt(1) ,
                        rs.getString(2), 
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5));
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
        return user;
    }
    
}
