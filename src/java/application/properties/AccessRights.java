/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * wrapper for AccessRights properties.
 * 
 * @author andre
 */
public class AccessRights {
    // path to file holding reference for access rights file.
    private final static String PATHS = "application\\properties\\Paths.properties";
//private final static String PATHS = "D:\\javaProjects\\TestMyDAOinWeb\\src\\java\\application\\properties\\Paths.properties";
        
    // key to filepath
    private final static String PATHTOACCESSRIGHTS = "AccessRights";
    // singleton instance of properties
    private static Properties instance;
    
    /**
     * gives instance object that holds properties
     * 
     * @return instance of AccessRight properties holding object
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Properties getInstance() throws FileNotFoundException, IOException{
        if( instance == null ){
            instance = new Properties();
            updateInstance();
        }
        return instance;
    }
    
    /**
     * searches for file needed and updates instance of propertie file
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void updateInstance() throws FileNotFoundException, IOException{
            //InputStream istream = getClass().getClassLoader().getResourceAsStream(PATHS);
            InputStream istream = AccessRights.class.getClassLoader().getResourceAsStream(PATHS);
            Properties prop = new Properties();
            prop.load(istream); 
            String filename = prop.getProperty(PATHTOACCESSRIGHTS);
            instance.load(new FileInputStream(filename));
    }
}
