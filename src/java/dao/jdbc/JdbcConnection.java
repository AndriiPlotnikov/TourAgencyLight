/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Admin
 */
public class JdbcConnection {
    private JdbcConnection(){
    }
    
    private static JdbcConnection instance;
    public static JdbcConnection getInstance(){
        if( instance == null ){
            instance = new JdbcConnection();
        }
        return instance;
    }
    
    public Connection getConnection(){
        try {
            InitialContext context = new InitialContext();
            DataSource ds =  (DataSource) context.lookup("jdbc/touristpooldb");
            return ds.getConnection();
        } catch (NamingException ex) {
            Logger.getLogger(JdbcConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch( SQLException exx){
            exx.printStackTrace();
        }
        return null;
    }
    
}
