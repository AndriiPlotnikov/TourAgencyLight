/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author andre
 */
public class Test {
    
    public static void main(String[] args) throws NamingException, SQLException {
        
        InitialContext context = new InitialContext();
            DataSource ds =  (DataSource) context.lookup("jdbc/touristpooldb");
            ds.getConnection();
    }
    
}
